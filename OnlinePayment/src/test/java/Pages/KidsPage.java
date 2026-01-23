package Pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class KidsPage {

    WebDriver driver;

    public KidsPage(WebDriver driver) {
        this.driver = driver;
        driver.manage().window().maximize();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[contains(@class,'single-products')]//h2")
    List<WebElement> priceDetails;

    @FindBy(xpath = "//a[@class='btn btn-default add-to-cart']")
    WebElement addToCart;

    @FindBy(xpath = "//div[contains(@class,'single-products')]")
    List<WebElement> allProduct;

    @FindBy(xpath = "//button[contains(text(),'Continue Shopping')]")
    WebElement continueBtn;

    @FindBy(xpath = "(//a[@href='/view_cart'])[1]")
    WebElement viewCart;

    @FindBy(xpath="//td[@class='cart_description']")
    List<WebElement> cartDescription;

    @FindBy(xpath="//td[@class='cart_total']")
    List<WebElement> cartTotal;

    @FindBy(xpath="//a[@class='btn btn-default check_out']")
    WebElement checkoutBtn;

    @FindBy(xpath="//h2[contains(text(),'Address Details')]")
    WebElement address;

    @FindBy(xpath="//h3[@class='page-subheading']")
    List<WebElement> subAddress;

    @FindBy(xpath="//b[contains(text(),'Total Amount')]/following::td[1]")
    WebElement totalAmt;

    @FindBy(name="name_on_card")
    WebElement cardHolder;

    @FindBy(name="card_number")
    WebElement cardNumber;

    @FindBy(name="cvc")
    WebElement cvc;

    @FindBy(name="expiry_month")
    WebElement month;

    @FindBy(name="expiry_year")
    WebElement year;

    @FindBy(id ="submit")
    WebElement pay;

    @FindBy(xpath="//b[contains(text(),'Order Placed!')]")
    WebElement OP;

    @FindBy(xpath="//a[contains(text(),'Download Invoice')]")
    WebElement downloadInvoice;

    public void selectSubCategory(String category, String subCategory) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement CatName = wait
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@href='#" + category + "']")));
        js.executeScript("arguments[0].scrollIntoView()", CatName);
        js.executeScript("arguments[0].click();", CatName);
        WebElement SubCatName = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'"+ subCategory + "')]")));
        SubCatName.click();
        System.out.println("Navigated to " + subCategory + " under " + category);
    }

    public void verifyPage(String PageName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        WebElement Name1 = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='features_items']//h2[contains(text(),'" + PageName + "')]")));
        String ActualName1 = Name1.getText();
        String expectedText = PageName.trim().toUpperCase();
        Assert.assertEquals(ActualName1, expectedText);
        System.out.println("Verified that user is on the " + PageName + " page");
    }

    public void GetImageDetailsWithRange(int min, int max) {
        List<Integer> allPrice = new ArrayList<>();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        for (int i = 0; i < priceDetails.size(); i++) {
            String priceText = priceDetails.get(i).getText().trim().replaceAll("[^0-9]", "");
            if (!priceText.isEmpty()) {
                int priceValue = Integer.parseInt(priceText);
                allPrice.add(priceValue);
                if (priceValue >= min && priceValue <= max) {
                    System.out.println("Product Price within range: " + priceValue);
                    if (i >= allProduct.size())
                        continue;
                    WebElement prod = priceDetails.get(i).findElement(By.xpath("./ancestor::div[contains(@class,'single-products')]"));
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", prod);
                    Actions act = new Actions(driver);
                    act.moveToElement(prod).perform();
                    closeAdIfPresent();
                    List<WebElement> addBtns = prod
                            .findElements(By.xpath(".//a[@class='btn btn-default add-to-cart']"));
                    if (!addBtns.isEmpty()) {
                        addBtns.get(0).click();
                        System.out.println("Product with price " + priceValue + " actually clicked Add to Cart");
                        wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();
                        System.out.println("Product with price " + priceValue + " added to cart");

                    } else {
                        System.out.println("Add to Cart button NOT found for price: " + priceValue);
                    }
                }
            }
        }
    }

    public void verifyProductInCart(String cartName){
        driver.navigate().refresh();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(viewCart)).click();
        System.out.println("Navigated to Cart page to verify products");
        WebElement cartTitleName = driver.findElement(By.xpath("//li[contains(text(),'"+cartName+"')]"));
        String cartTitle = cartTitleName.getText().trim();
        Assert.assertEquals(cartTitle, "Shopping Cart");
        System.out.println("Verified that products are added to the cart successfully");
        Assert.assertTrue(cartDescription.size() > 0, "No products found in the cart");
        System.out.println("Total products in cart: " + cartDescription.size());
        String desc = cartDescription.get(0).getText().trim();
        System.out.println("First product description in cart: " + desc);
        String total =  cartTotal.get(0).getText().trim();
        System.out.println("First product total in cart: " + total);
        checkoutBtn.click();
        System.out.println("Proceeded to checkout page");
        
     }

    public void checkOutProcess(String checkout){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement COName = driver.findElement(By.xpath("//li[contains(text(),'"+checkout+"')]"));
        String titleCheckout = COName.getText().trim();
        Assert.assertEquals(titleCheckout, "Checkout");
        System.out.println("Verified that user is in Checkout page");
        wait.until(ExpectedConditions.visibilityOf(address));
        if(subAddress.size()==2){
            System.out.println("Both Delivery and Billing address are present");
        }
        String totalAmount = totalAmt.getText().trim();
        if(totalAmt.isDisplayed()){
           System.out.println("Total Amount in Checkout page: "+totalAmount);
        }
        int paymentAmount = Integer.parseInt(totalAmount.replaceAll("[^0-9]", ""));
        System.out.println("Payment amount to be made: "+paymentAmount);
        if(paymentAmount>200){
            checkoutBtn.click();
            System.out.println("Proceeding to complete the payment process for amount: "+paymentAmount);
        }else{
            System.out.println("Payment amount is less than the minimum required to proceed: "+paymentAmount);
        }
 }

    public void paymentProcess(String payment){
        WebElement payName = driver.findElement(By.xpath("//li[contains(text(),'"+payment+"')]"));
        String paymentName = payName.getText().trim();
        if(payName.isDisplayed()){
            System.out.println("User is in Payment page: "+paymentName);
        }
        cardHolder.sendKeys("Ruban");
        cardNumber.sendKeys("987654321232");
        cvc.sendKeys("234");
        month.sendKeys("12");
        year.sendKeys("2030");
        pay.click();
        System.out.println("Payment process completed successfully");
      }


      public void verifyOrderPlaced(){
        if(OP.isDisplayed()){
            String orderText = OP.getText().trim();
            Assert.assertEquals(orderText, "ORDER PLACED!");
            System.out.println("Order has been placed successfully");
        }
        if(downloadInvoice.isDisplayed()){
            downloadInvoice.click();
            System.out.println("Invoice downloaded successfully");
        }

      }

    public void closeAdIfPresent() {
        try {
            // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            List<By> adCloseButtons = List.of(
                    By.xpath("//button[contains(text(),'Close')]"),
                    By.xpath("//button[contains(text(),'×')]"),
                    By.xpath("//div[@id='dismiss-button']"),
                    By.xpath("//iframe[contains(@src,'ads')]"));

            for (By locator : adCloseButtons) {
                List<WebElement> elements = driver.findElements(locator);
                if (!elements.isEmpty()) {
                    elements.get(0).click();
                    System.out.println("Ad closed");
                    break;
                }
            }
        } catch (Exception e) {

        }
    }



}
