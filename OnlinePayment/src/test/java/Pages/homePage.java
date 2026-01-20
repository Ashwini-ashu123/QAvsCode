package Pages;

import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
// import org.openqa.selenium.support.ui.ExpectedConditions;
// import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

// import java.time.Duration;

public class homePage {

    WebDriver driver;

    public homePage(WebDriver driver){
        this.driver = driver;
        driver.manage().window().maximize();
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath="//div[@class='left-sidebar']//h2[contains(text(),'Category')]")
    WebElement CategoryText;

    @FindBy(xpath="//div[@class='left-sidebar']//h2[contains(text(),'Brand')]")
    WebElement BrandText;

    @FindBy(xpath="//div[@class='features_items']//h2[contains(text(),'Features Items')]")
    WebElement FIText;

    @FindBy(xpath="//div[contains(@class,'single-products')]//img" )
    List<WebElement> imgProduct;


    public void verifyHomePageElements(String name1, String name2, String name3){
        // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView({block: 'center'});", CategoryText);
        js.executeScript("arguments[0].scrollIntoView()",CategoryText);
        String ActualName1 = CategoryText.getText();
        Assert.assertEquals(ActualName1, name1);
        js.executeScript("arguments[0].scrollIntoView()",BrandText);
        String ActualName2 = BrandText.getText();
        Assert.assertEquals(ActualName2, name2);
        js.executeScript("arguments[0].scrollIntoView()",FIText);
        String ActualName3 = FIText.getText();
        Assert.assertEquals(ActualName3, name3);
        System.out.println("Home page elements verified successfully");
       }

    public void verifyProductsOnHomePage(){
        int prodCount = imgProduct.size();
        System.out.println("Number of products on home page: " + prodCount);
        Assert.assertTrue(prodCount > 0, "No products found on home page");
        System.out.println("Products are visible on the home page");
        }
    
    }



    


