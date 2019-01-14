import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreateAdmin extends Setup {

    @BeforeSuite
    @BeforeTest
    public void setup(){
        Setup.getEnvironment();
    }

    @Test
    public void createAdminUser(){
        System.out.println("createAdminNewUser is running");
        RestAssured.baseURI = prop.getProperty("HOST");
        System.out.println(RestAssured.baseURI);
        Response res = given().auth().basic("","2223951387264968312").
                body(Resources.postAdminUserBody()).contentType(ContentType.JSON).header("Origin",prop.getProperty("HOST")).
                when().
                post(Resources.postNewUser()).
                then().
                extract().response();
        System.out.println(res.asString());
    }
}
