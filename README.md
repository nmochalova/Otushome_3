# OtusHomework_3
**Домашнее задание:** Rest-assured и WireMock

**Цель:**
Написать автотесты с использованием Rest-assured и WireMock.

**Инструкция выполнения домашнего задания:**
Необходимо реализовать Stub сервер на Wiremock со следующими endpoint'ами:
- /user/get/{id} для получение оценки пользователя
- /cource/get/all для получения списка курсов
- /user/get/all - для получения списка всех пользователей

Перед запуском тестов необходимо стартануть [WireMock в docker](https://hub.docker.com/r/wiremock/wiremock):

````docker run -d -p 9190:8080 wiremock/wiremock --verbose````


**Контракты**

- Для user

{
"name":"Test user",
"cource":"QA",
"email":"test@test.test"
"age": 23
}


- Для оценки:

{
"name":"Test user",
"score": 78
}


- Для курсов:

[
{
"name":"QA java",
"price": 15000
},
{
"name":"Java",
"price": 12000
}
]


Подключить stub frontend, написать тесты для проверки json cхем.

Вышеуказанные задачи можно реализовать с помощью Citrus Framework.
По http запросам можно применить RestAssured, Retrofit или Citrus (на ваше усмотрение).
Для заглушек можно использовать Wiremock или Citrus Framework (на ваше усмотрение).
