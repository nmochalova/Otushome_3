package User;

import org.junit.jupiter.api.Test;
import services.UserApi;

import static org.hamcrest.Matchers.equalTo;

public class GetUserByNameTest {
    /**
     * TestCase 1: Запросить юзера по существующему имени
     *
     * Ожидаемый результат:
     * Code 200 successful operation
     * Найден юзер по заданному username
     */
    @Test
    public void GetUserByNameTest1(){
        String username = "string";

        UserApi userApi = new UserApi();

        userApi.OLDgetUserByName(username)
                .statusCode(200)
                .body("username",equalTo(username));
    }

    /**
     * TestCase 2: Запросить юзера по имени, которого нет в системе
     *
     * Ожидаемый результат:
     * Code 404 User not found
     * Юзер не найден, ответ:
     * {
     *   "code": 1,
     *   "type": "error",
     *   "message": "User not found"
     * }
     */
    @Test
    public void GetUserByNameTest2(){
        String username = "user1";

        UserApi userApi = new UserApi();

        userApi.OLDgetUserByName(username)
                .statusCode(404)
                .body("code",equalTo(1))
                .body("type",equalTo("error"))
                .body("message",equalTo("User not found"));
    }
}
