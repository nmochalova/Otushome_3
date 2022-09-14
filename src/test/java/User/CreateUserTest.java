package User;

import dto.UserNew;
import dto.UserOut;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import services.UserApi;

import static org.hamcrest.Matchers.equalTo;

public class CreateUserTest {
    /**
     * TestCase 1: Создание юзера со всеми параметрами. Валидация по схеме.
     *
     * Ожидаемый результат:
     * Code 200 successful operation
     * 1. Валидация по схеме: код ответа лежит в диапазон от 100 до 500
     * 2. В ответе присутствуют три обязательных поля:    "code",     "type",    "message"
     */
    @Test
    public void createUserTest1(){
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

        UserApi userApi = new UserApi();

        userApi.crateUser(user)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/CreateUser.json"));
    }

    /**
     * TestCase 2: Создаем юзера с заданным id и username. Валидация hamcrest.Matchers
     *
     * Ожидаемый результат:
     * Code 200 successful operation
     * 1. В поле message возвращен id юзера
     * 2. Поле type содержит слово "unknown"
     */
    @Test
    public void createUserTest2(){
        long id = 122L;

        UserNew user = UserNew.builder()
                .userStatus(11L)
                .email("test@tr.ru")
                .id(id)
                .firstName("firstName")
                .password("password")
                .password("password")
                .username("username")
                .phone("phone")
                .build();

        UserApi userApi = new UserApi();

        userApi.crateUser(user)
                .body("type",equalTo("unknown"))
                .body("message",equalTo(String.valueOf(id)));
    }

    /**
     * TestCase 3: Создаем юзера без задания id и username. Преобразование ответа в dto. Валидация Assertions.
     *
     * Ожидаемый результат:
     * Code 200 successful operation
     * В поле message возвращен 0.
     */
    @Test
    public void createUserTest3(){
        UserNew user = UserNew.builder()
                .userStatus(11L)
                .email("test@tr.ru")
                .firstName("firstName")
                .password("password")
                .password("password")
                .phone("phone")
                .build();

        UserApi userApi = new UserApi();

        UserOut userOut = userApi
                .crateUser(user)
                .extract()
                .body()
                .as(UserOut.class);

        Assertions.assertEquals("0",userOut.getMessage());
    }
}
