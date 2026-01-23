package StepsFile;

import org.openqa.selenium.WebDriver;

import Pages.KidsPage;
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
    KidsPage kids;

    public defnSteps() {
        driver = DriverFactory.getDriver();
        login = new loginPage(driver);
        home = new homePage(driver);
        kids = new KidsPage(driver);
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

    @Then("click on the {string} section from the category and select the {string} option")
    public void selectCatAndSubCat(String category, String subCategory){
        kids.selectSubCategory(category, subCategory);

    }

    @Then("verify the user is on the {string} page")
    public void verifyuserOnPage(String pageName){
        kids.verifyPage(pageName);
        home.verifyProductsOnHomePage();
    }
    
    @And("Select the product that range from {int} to {int} and add to cart")
    public void selectProductInRange(int min, int max){
        kids.GetImageDetailsWithRange(min, max);
    }

    @Then("verify the products is added to the {string} and proceed to checkout")
    public void verifyInCart(String cartName){
        kids.verifyProductInCart(cartName);
    }

    @And("verify the user is in {string} page and proceed to payment process")
    public void verifyCheckoutAndPayment(String checkout){
        kids.checkOutProcess(checkout);
    }

    @Then("verify the user is in {string} page and complete the payment process")
    public void verifyPaymentAndCompleteProcess(String paymentName){
        kids.paymentProcess(paymentName);
    }

    @And("verify the order is placed successfully and download the invoice")
    public void verifyOrderPlacedAndDownloadInvoice(){
        kids.verifyOrderPlaced();
    }

    @Then("Logout from the application")
    public void logoutFromApplication(){
        login.logoutProcess();
        System.out.println("User logged out successfully");
    }
    

}
