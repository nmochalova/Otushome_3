package users;

import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import dto.Course;
import dto.User;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import services.UserApi;
import stubs.CourseStub;
import stubs.ScoreStub;
import stubs.UserStub;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;

public class Homework_Test {
  {
    new ScoreStub();
    new UserStub();
    new CourseStub();
  }

  private WebDriver driver;

  @BeforeClass
  public static void startWireMock() {
    configureFor(9190);
  }

  @Before
  public void init() throws MalformedURLException {
    configureFor(9190);

    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("browserName","chrome");
    capabilities.setCapability("browserVersion","104.0");
    capabilities.setCapability("enableVNC",true);
    driver = new RemoteWebDriver(
            URI.create("http://127.0.0.1/wd/hub").toURL(),
            capabilities
    );
  }

  @After
  public void tearDown(){
    if(driver != null) {
      driver.close();
      driver.quit();
    }
  }

  @Test
  public void get_score_test() {
    //SOME STEPS...
    driver.get("https://otus.ru");

    //MOCK SERVICE
    UserApi userApi = new UserApi();
    Response response = userApi.getScore("2");   //запрашиваем у mock-сервиса оценку по id = 2 (работает только для 2)
    assertThat(response.statusCode()).isEqualTo(200);  //проверяем, что код 200

    String name = userApi.getIntFromJson(response,"name");    //извлекаем полученные данные
    String score = userApi.getIntFromJson(response,"score");

    assertThat(name).isEqualTo("Test user");
    assertThat(score).isEqualTo("78");

    //SOME STEPS...
  }

  @Test
  public void get_user_list_test() {
    //SOME STEPS...
    driver.get("https://otus.ru");

    //MOCK SERVICE
    UserApi userApi = new UserApi();
    List<User> users = userApi.getListUser().extract().jsonPath().getList("$",User.class);
    assertThat(users.size()).isEqualTo(1);
    users.forEach(System.out::println);

    //SOME STEPS...
  }

  @Test
  public void get_course_list_test() {
    //SOME STEPS...
    driver.get("https://otus.ru");

    //MOCK SERVICE
    UserApi userApi = new UserApi(); //запрашиваем у mock-сервиса список пользователей
    List<Course> courses = userApi.getListCourse().extract().jsonPath().getList("$",Course.class);
    assertThat(courses.size()).isEqualTo(2);
    courses.forEach(System.out::println);

    //SOME STEPS...
  }
}
