package users;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import services.UserApi;

public class PositiveUserTests {
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
}
