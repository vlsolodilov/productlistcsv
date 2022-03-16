# Product list csv

###Задание:

Реализовать приложение на стеке технологий: Spring Boot 2, Java 8, PostgreSQL.
Таблицы должны создаваться автоматически в БД при первом старте приложения.
Структура таблиц в БД
1. Таблица «Товар». Хранит название товара. Колонки: id, name.
2. Таблица «Цена товара». Хранит цену на товар и дату, с которой цена актуальна. По каждому товару может быть несколько цен с разными датами. Колонки: id, price, datetime, product_id.
   Функционал приложения
3. Загрузка товаров и цен из csv-файла. Приложение должно уметь загружать данные из csv-файла. Путь директории с файлами настраивается в конфигурационном файле приложения. Пример формат данных в csv-файла: product_id;product_name;price_id;price;price_datetime.
   В логах должен быть отмечен факт старта обработки файла и результат обработки файла с количеством обработанных записей (товаров и цен). Загрузка файла стартует по расписанию, время настраивается в конфигурационном файле. После успешной обработки файл удалить.
4. Получение списка товаров и актуальных цен. Приложение должно предоставлять REST метод, возвращающий из БД список товаров с ценами, актуальными на указанный в запросе день. GET /products?date=yyyy-mm-dd.  Формат - список
5. Получение статистики. Приложение должно предоставлять REST метод, возвращающий статистику по загруженным товарам и ценам. GET /products/statistic. Параметры статистики:
   Количество товаров в БД. Формат - просто цифра.
   Как часто менялась цена товара. Группировка по товарам. Формат - список
   {"name": "Товар 1", "frequency": 2}
   Как часто менялась цена товара. Группировка по дням. Формат - список
   {"date": "yyyy-mm-dd", "frequency": 6}
   
###Описание запросов и примеры ответов в JSON.
Пример содержания csv файла:  
product_id,product_name,price_id,price,price_datetime  
1,Product 1,1,10,2022-03-15  
2,Product 2,2,11,2022-03-15  
3,Product 3,3,12,2022-03-15  
4,Product 4,4,13,2022-03-15  
10,Product 10,10,100,2022-03-16  
11,Product 11,11,101,2022-03-16  
12,Product 12,12,102,2022-03-16  
13,Product 13,13,103,2022-03-16  
1,Product 1,20,100,2022-03-16  
2,Product 2,21,101,2022-03-16  
3,Product 3,22,102,2022-03-16

Получение списка товаров и актуальных цен:  
Запрос: http://localhost:8080/api/products?date=2022-03-15  
Ответ: [{"name":"Product 3","price":12},{"name":"Product 4","price":13},{"name":"Product 2","price":11},{"name":"Product 1","price":10}]

Запрос: http://localhost:8080/api/products?date=2022-03-16  
Ответ: [{"name":"Product 11","price":101},{"name":"Product 2","price":101},{"name":"Product 1","price":100},{"name":"Product 4","price":13},{"name":"Product 12","price":102},{"name":"Product 13","price":103},{"name":"Product 10","price":100},{"name":"Product 3","price":102}]


Количество товаров в БД:  
Запрос: http://localhost:8080/api/products/statistic/amount  
Ответ: 8

Как часто менялась цена товара. Группировка по товарам:  
Запрос: http://localhost:8080/api/products/statistic/names  
Ответ: [{"name":"Product 1","frequency":2},{"name":"Product 2","frequency":2},{"name":"Product 3","frequency":2},{"name":"Product 4","frequency":1},{"name":"Product 11","frequency":1},{"name":"Product 10","frequency":1},{"name":"Product 13","frequency":1},{"name":"Product 12","frequency":1}]

Как часто менялась цена товара. Группировка по дням:  
Запрос: http://localhost:8080/api/products/statistic/dates  
Ответ: [{"date":"2022-03-15","frequency":4},{"date":"2022-03-16","frequency":7}]

###Настройки в конфигурационном файле
В файле application.yaml: 
- в поле dir можно указать директорию с файлами csv
- в поле cron можно указать расписание обработки файлов в формате cron