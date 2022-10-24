# OtusHomework_3
**Домашнее задание:** 
Необходимо подключить Selenoid и перенести существующие тесты на использование Selenoid.

Проект содержит следующие тесты:
- тесты проверки Backend при помощи RestAssured
- Stub сервер для Backend

Stub сервер реализован на Wiremock со следующими endpoint'ами:
- /user/get/{id} для получение оценки пользователя
- /cource/get/all для получения списка курсов
- /user/get/all - для получения списка всех пользователей

К каждому stub написаны тесты на RestAssured.

---
Перед запуском тестов необходимо стартануть
1. [WireMock в docker](https://hub.docker.com/r/wiremock/wiremock):

````docker run -d -p 9190:8080 wiremock/wiremock --verbose````

2. Selenoid
```` 
docker run -d --name selenoid_1 -p 4445:4444 --net=selenoid_1 -v //var/run/docker.sock:/var/run/docker.sock -v C:\Work\OTUS\Selenoid:/etc/selenoid:ro aerokube/selenoid -limit=12 -capture-driver-logs -max-timeout=0h30m0s -container-network=selenoid_1
````
Проверям
````
docker ps
docker logs selenoid_1
````
Команда для перезапуска Selenoid
````
docker restart selenoid_1
````

---
Перед запуском Selenoid необходимо произвести следующие настройки:
- создать сеть
````
- docker network create selenoid_1
````
````
network docker ls
````
- настроить **browser.json** (у меня файл лежит в директории C:\Work\OTUS\Selenoid)
````
{
    "chrome": {
        "default": "104.0",
        "versions": {
            "104.0": {
                "image": "selenoid/chrome:104.0",
                "port" : "4444",
				"path" : "/"
            },
			"103.0": {
                "image": "selenoid/chrome:103.0",
                "port" : "4444",
				"path" : "/"
            }
        }
    },
	"opera": {
        "default": "88.0",
        "versions": {
            "88.0": {
                "image": "selenoid/opera",
                "port" : "4444",
				"path" : "/"
            }
		}
	}
}
````
- сделать pull образов браузеров из файла browser.json, например
````
docker pull selenoid/chrome:104.0
docker pull selenoid/chrome:103.0
docker pull selenoid/opera
````