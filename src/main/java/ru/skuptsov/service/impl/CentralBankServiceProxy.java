package ru.skuptsov.service.impl;

import com.sun.org.apache.xerces.internal.dom.ElementNSImpl;
import com.sun.org.apache.xerces.internal.dom.TextImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.cbr.web.*;
import ru.skuptsov.service.api.CentralBankService;
import ru.skuptsov.service.exception.CentralBankException;
import ru.skuptsov.service.exception.CurrencyRateException;
import ru.skuptsov.service.exception.InvalidCurrencyCodeException;
import ru.skuptsov.service.utils.Utils;

import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

/**
 * Прокси-класс для работы с веб-сервисом ЦБ
 * Created by Сергей on 11.10.2015.
 */
@Service
public class CentralBankServiceProxy implements CentralBankService {
    private static final Logger logger = LoggerFactory.getLogger(CentralBankServiceProxy.class);


    public BigDecimal getCurrencyRate(String code, Calendar date) throws CurrencyRateException, CentralBankException, InvalidCurrencyCodeException {

        logger.debug("Entering method getCurrencyRate");

        BigDecimal rate;
        try {
            DailyInfo service = new DailyInfo();
            DailyInfoSoap port = service.getDailyInfoSoap();

            XMLGregorianCalendar onDate = Utils.getXMLGregorianCalendarFromCalendar(date);

            GetCursOnDateXMLResponse.GetCursOnDateXMLResult result = port.getCursOnDateXML(onDate);

            rate = getCurrencyRateByCode(code, result);
        } catch (InvalidCurrencyCodeException e) {
            logger.error("GetCurrencyRate Exception",e,e);
            throw new InvalidCurrencyCodeException(e.getMessage());
        } catch (Exception e) {
            logger.error("GetCurrencyRate Exception",e);
            throw new CentralBankException(e.getMessage());
        }


        logger.debug("Leaving method getCurrencyRate");
        return rate;

    }

    private static BigDecimal getCurrencyRateByCode(String code, GetCursOnDateXMLResponse.GetCursOnDateXMLResult result) throws InvalidCurrencyCodeException {

        logger.debug("Entering method getCurrencyRateByCode");

        BigDecimal rate = new BigDecimal(0);
        List<Object> list = result.getContent();
        ElementNSImpl e = (ElementNSImpl) list.get(0);
        NodeList chCodeList = e.getElementsByTagName("VchCode");
        int length = chCodeList.getLength();

        boolean isFound = false;
        for (int i = 0; i < length; i++) {
            if (isFound) break;

            Node valuteChNode = chCodeList.item(i);
            TextImpl textimpl = (TextImpl) valuteChNode.getFirstChild();
            String chVal = textimpl.getData();

            if (chVal.equalsIgnoreCase(code)) {
                isFound = true;
                Node parent = valuteChNode.getParentNode();
                NodeList nodeList = parent.getChildNodes();
                int paramLength = nodeList.getLength();

                for (int j = 0; j < paramLength; j++) {
                    Node currentNode = nodeList.item(j);

                    String name = currentNode.getNodeName();
                    Node currentValue = currentNode.getFirstChild();
                    String value = currentValue.getNodeValue();
                    if (name.equalsIgnoreCase("Vcurs")) {
                        rate = new BigDecimal(value);
                    }
                }
            }
        }

        if(isFound==false)
            throw new InvalidCurrencyCodeException("Неверно задан код валюты");

        logger.debug("Leaving method getCurrencyRateByCode");
        return rate;

    }
}