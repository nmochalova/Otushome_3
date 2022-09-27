package users;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import services.UserApi;

public class PositiveUserTest {
    /** TestCase 1: Создание пользователя
     * Шаги:
     * 1. Создаем пользователя
     * 2. Проверяем, что пользователь создан
     *
     * Ожидаемый результат:
     * Пользователь имеется в системе.
     */

    @Test
    public void createUserTest(){
        long id = Math.round(Math.random() * 100000);
        String name = "username"+id;

        UserApi userApi = new UserApi();

        userApi.createUser(id,name);                    //создаем пользователя
        int statusCode = userApi.getUserByName(name);   //запрашиваем пользователя по имени

        Assertions.assertEquals(200,statusCode,"The user was not created."); //проверяем, что пользователь имеется в системе
    }

    /** TestCase 2: Удаление пользователя
     * Шаги:
     * 1. Создаем пользователя
     * 2. Проверяем, что пользователь создан
     * 3. Удаляем пользователя
     * 4. Проверяем, что пользователь удален
     *
     * Ожидаемый результат:
     * Пользователь отсутствует в системе.
     */
    @Test
    public void DeleteUserTest(){
        long id = Math.round(Math.random() * 100000);
        String name = "username"+id;
        int statusCode;

        UserApi userApi = new UserApi();

        userApi.createUser(id,name);                                  //создаем пользователя
        statusCode = userApi.getUserByName(name);                     //запрашиваем пользователя по имени
        Assertions.assertEquals(200,statusCode,               //проверяем, что пользователь имеется в системе
                "The user was not created.");

        statusCode = userApi.deleteUser(name);                    //удаляем пользователя
        Assertions.assertEquals(200,statusCode,              //проверяем, что пользователь удален
                "The user was not delete.");

        statusCode = userApi.getUserByName(name);                    //запрашиваем пользователя по имени
        Assertions.assertEquals(404,statusCode,              //проверяем, что пользователь отсутствует в системе
                "The user was found, but should not have been.");
    }
}
