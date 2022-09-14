package services;

import dto.UserNew;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;

public class UserApi {
    public static final String BASE_URL = "https://petstore.swagger.io/v2";
    public static final String USER_PATH = "/user";
    public static final String GET_USER_PATH = "/user/{username}";

    private RequestSpecification reqSpec;
    private ResponseSpecification respSpec;

    public UserApi() {
        reqSpec = given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON);
        respSpec = expect()
                .statusCode(200);
    }

    public ValidatableResponse crateUser(UserNew user) {
        return given(reqSpec)
                .basePath(USER_PATH)
                .body(user)
                .log().all()
                .expect()
                .spec(respSpec)
              .when()
                .post()
              .then()
                .log().all();
    }

    public ValidatableResponse getUserByName(String username) {
        return given(reqSpec)
                .log().all()
                .when()
                .get(GET_USER_PATH,username)
                .then()
                .log().all();
    }
}
