package StepsFile;

import org.openqa.selenium.WebDriver;

import Pages.homePage;
import Pages.loginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import utilities.DriverFactory;

public class defnSteps {

    WebDriver driver;
    loginPage login;
    homePage home;

    public defnSteps() {
        driver = DriverFactory.getDriver();
        login = new loginPage(driver);
        home = new homePage(driver);
    }

    @Given("Navigate to the Url for e-commerce application")
    public void navigate_to_url() {
        System.out.println("browser launched");
    }

    @Then("Click on the sign in button and fill the username and password")
    public void fill_credentials() {
        login.signinProcess();
        System.out.println("Credentials filled");
    }

    @And("verify the user is logged in successfully")
    public void verifyLogin(){
        login.verifyLogin();
    }

    @Then("verify the home page is having the 'CATEGORY' 'BRANDS' and 'FEATURES ITEMS' on the home page")
    public void verifyHomePageElements() {
        home.verifyHomePageElements("CATEGORY", "BRANDS", "FEATURES ITEMS");
    }

    @And("verify the user is able to see the products on the home page")
    public void verifyProductsOnHomePage() {
        home.verifyProductsOnHomePage();
    }
}
