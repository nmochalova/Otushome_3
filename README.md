# OtusHomework_3
**Домашнее задание:** 
Необходимо подключить Selenoid и перенести существующие тесты на использование Selenoid.

**Инструкция выполнения домашнего задания:**
- подключение Selenoid
- тесты проверки Backend при помощи RestAssured
- Stub сервер для Backend

Перед запуском тестов необходимо стартануть [WireMock в docker](https://hub.docker.com/r/wiremock/wiremock):

````docker run -d -p 9190:8080 wiremock/wiremock --verbose````

Реализован Stub сервер на Wiremock со следующими endpoint'ами:
- /user/get/{id} для получение оценки пользователя
- /cource/get/all для получения списка курсов
- /user/get/all - для получения списка всех пользователей

К каждому stub написаны тесты на RestAssured.