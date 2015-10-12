# CurrencyRate
Запуск проекта : 
1. Из исходников - из корня проекта mvn spring-boot:run
2. Из архива - из корня проекта java -jar resources/currency-rate-1.0.jar

По умолчанию сервис стартует на порту 9080, пример запроса сервиса
http://localhost:9080/api/rate/USD

Для изменения необходимо или поменять параметр в main/resources/application.properties
Или указать парметр mvn spring-boot:run -Dserver.port=8090
