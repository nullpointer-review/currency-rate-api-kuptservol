package ru.skuptsov.utils;

import org.junit.Test;
import org.springframework.util.Assert;
import ru.skuptsov.service.exception.DateParseException;

/**
 * Created by Сергей on 11.10.2015.
 */
public class UtilsTest {

    @Test
    public void getCalendarFromString() throws DateParseException {
        Assert.notNull(ru.skuptsov.service.utils.Utils.getCalendarFromString("2014-02-10"));

    }

    @Test(expected=DateParseException.class)
    public void getCalendarFromStringWithException() throws DateParseException {
        Assert.notNull(ru.skuptsov.service.utils.Utils.getCalendarFromString("20140210"));

    }
}
