package users;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import services.UserApi;

public class NegativeUserTest {
  /**
   * TestCase 1: Запросить юзера по имени, которого нет в системе
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
  public void getUserByFailedNameTest(){
    String username = "user1";

    UserApi userApi = new UserApi();

    int statusCode = userApi.getUserByName(username);
    Assertions.assertEquals(404,statusCode,"The user was not delete.");
  }

  /**
   * TestCase 2: Удалить пользователя, которого нет в системе
   *
   * Ожидаемый результат:
   * Code 404 User not found
   * Юзер не найден
   */
  @Test
  public void deleteUserByFailedNameTest(){
    long id = Math.round(Math.random() * 100000);
    String username = "username"+id;

    UserApi userApi = new UserApi();

    int statusCode = userApi.deleteUser(username);
    Assertions.assertEquals(404,statusCode, "The user was not delete.");
  }
}
