package User.createUser;

import dto.Book;
import dto.UserNew;
import dto.UserOut;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import services.UserApiNew;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;

public class CreateUserTest {
    //Вариант 1. Проверки через hamcrest.Matchers: https://hamcrest.org/JavaHamcrest/tutorial
    @Test
    public void checkUserV1(){
        UserNew user = UserNew.builder()
                .userStatus(11L)
                .email("test@tr.ru")
                .id(122L)
                .firstName("firstName")
                .password("password")
                .password("password")
                .username("username")
                .phone("phone")
                .build();

        UserApiNew userApi = new UserApiNew();

        userApi.crateUser(user)
                .statusCode(200)
                .body("type",equalTo("unknown"))
         //       .body("store.book[0].title",equalTo("unknown")) //проверка при помощи Groovy's GPath сложного json-ответа
                .body("message",equalTo("122"));
    }

    //Вариант 2. Преобразование ответа в объект dto
    @Test
    public void checkUserV2(){
        UserNew user = UserNew.builder()
                .userStatus(11L)
                .email("test@tr.ru")
                .id(122L)
                .firstName("firstName")
                .password("password")
                .password("password")
                .username("username")
                .phone("phone")
                .build();

        UserApiNew userApi = new UserApiNew();

        ValidatableResponse response = userApi.crateUser(user);
        UserOut userOut = userApi.crateUser(user).extract().body().as(UserOut.class);
        Assertions.assertEquals("122",userOut.getMessage());
    }


    //Вариант 3. Получение списка в случае вложенности json-ответа (Groovy's GPath)
    @Test
    public void checkUserV3(){
        UserNew user = UserNew.builder()
                .userStatus(11L)
                .email("test@tr.ru")
                .id(122L)
                .firstName("firstName")
                .password("password")
                .password("password")
                .username("username")
                .phone("phone")
                .build();

        UserApiNew userApi = new UserApiNew();

        ValidatableResponse validResp = userApi.crateUser(user);
        List<Book> books = validResp.extract().jsonPath().getList("store.book");
    }

    //Вариант 4. Валидация по схеме (CreateUser.json в resources создано онлайн-генератором Json schema from json)
    //Проверка структуры json, типы, диапазоны, обязательности полей (required)
    @Test
    public void checkUserV4(){
        UserNew user = UserNew.builder()
                .userStatus(11L)
                .email("test@tr.ru")
                .id(122L)
                .firstName("firstName")
                .password("password")
                .password("password")
                .username("username")
                .phone("phone")
                .build();

        UserApiNew userApi = new UserApiNew();

        userApi.crateUser(user)
                .statusCode(200)
                .body("type",equalTo("unknown"))
                .body("message",equalTo("122"))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/CreateUser.json"));
    }
}
