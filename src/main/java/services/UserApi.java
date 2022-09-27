package services;

import dto.UserNew;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UserApi {
    private String BASE_URL =  System.getProperty("base.url","https://petstore.swagger.io/v2");
    private String USER_PATH = "/user";
    private String GET_USER_PATH = "/user/{username}";

    private RequestSpecification reqSpec;
    private ResponseSpecification respSpec;

    public UserApi() {
        reqSpec = given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON);
        respSpec = expect()
                .statusCode(200);
    }

    public void createUser(Long id, String username) {
        UserNew user = UserNew.builder()
                .id(id)
                .username(username)
                .firstName("firstName"+id)
                .lastName("secondName"+id)
                .email("test"+id+"@tr.ru")
                .password("password")
                .phone("phone"+id)
                .userStatus(0L)
                .build();


        given(reqSpec)
                .basePath(USER_PATH)
                .body(user)
                .log().all()
                .expect()
                .spec(respSpec)
                .when()
                .post()
                .then()
                .body("type",equalTo("unknown"))
                .body("message",equalTo(String.valueOf(id)))
                .log().all();
    }

    public int getUserByName(String username) {
        Response response = RestAssured
                .given(reqSpec)
                .log().all()
                .when()
                .get(GET_USER_PATH,username)
                .andReturn();

        response.prettyPrint();

        return response.getStatusCode();
    }

    public int deleteUser(String name) {
        Response response = RestAssured
                .given(reqSpec)
                .log().all()
                .when()
                .delete(GET_USER_PATH,name)
                .andReturn();

        response.prettyPrint();

        return response.getStatusCode();
    }
}
