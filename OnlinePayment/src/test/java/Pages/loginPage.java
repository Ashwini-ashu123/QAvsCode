package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class loginPage {

    WebDriver driver;

    public loginPage(WebDriver driver){
        this.driver = driver;
        driver.manage().window().maximize();
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//a[contains(text(),'Log in')]")
    WebElement SignIn;

    @FindBy(xpath="(//input[@name='email'])[1]")
    WebElement username;

    @FindBy(xpath="//input[@name='password']")
    WebElement password;

    @FindBy(xpath="(//button[@type='submit'])[1]")
    WebElement login;

    @FindBy(xpath="//*[contains(text(),' Logged in as ')]")
    WebElement Userlogin;

    @FindBy(xpath="//a[contains(text(),'Logout')]")
    WebElement logout;
    
    public void signinProcess(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        // wait.until(ExpectedConditions.elementToBeClickable(SignIn));
        // SignIn.click();
        wait.until(ExpectedConditions.elementToBeClickable(username));
        username.click();
        username.sendKeys("Frank@dev.com");
        wait.until(ExpectedConditions.elementToBeClickable(password));
        password.click();
        password.sendKeys("RISqa@123");
        login.click();
     }

     public void verifyLogin(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.textToBePresentInElement(Userlogin, "Logged in as Franklin"));
        
        
     }

     public void logoutProcess(){
        logout.click();

     }

    



}
