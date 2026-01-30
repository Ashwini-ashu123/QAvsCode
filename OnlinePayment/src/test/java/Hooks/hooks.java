package Hooks;
import utilities.*;



import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;

public class hooks {

    @Before("@UITesting")
    public void launchBrowser(){
        DriverFactory.setup();

    }

    @After("@UITesting")
    public void closeBrowser(){
        DriverFactory.tearDown();
    }

    @Before("@APITesting")
    public void AutomationApiSetup(){
         RestAssured.baseURI = "https://automationexercise.com/api";
    }

    @Before("@BookingAPITesting")
    public void BookingApiSetup(){
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    }

}
