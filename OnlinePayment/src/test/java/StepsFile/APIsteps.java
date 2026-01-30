package StepsFile;

import java.util.List;
import java.util.Map;

import org.testng.Assert;

import Pages.BookingPage;
import Pages.productAPIPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class APIsteps {

    productAPIPage productapi = new productAPIPage();
    BookingPage bookingpage = new BookingPage();
    int bookingId;
    Response response;

    @When("user sends GET request to get all products")
    public void getRequestForProducts() {
        response = productapi.getAllProducts();
    }

    @Then("response status code should be 200")
    public void verifyStatusCode() {
        Assert.assertEquals(200, response.getStatusCode());
    }

    @And("products list should not be empty")
    public void verifyProductSize() {
        List<?> products = response.jsonPath().getList("products");
        Assert.assertNotNull(products);
        Assert.assertTrue(products.size() > 0, "Products list is empty");
        System.out.println("Total products count: " + products.size());
        List<Map<String, Object>> pproducts = response.jsonPath().getList("products");
        System.out.println("---- Product Details ----");
        for (Map<String, Object> product : pproducts) {
            System.out.println("ID    : " + product.get("id"));
            System.out.println("Name  : " + product.get("name"));
            System.out.println("Price : " + product.get("price"));
            System.out.println("Brand : " + product.get("brand"));
            System.out.println("-------------------------");
        }

    }

    @Given("user has booking details")
    public void bookingDetails() {
        response = bookingpage.createBooking();
        response.then().statusCode(200);
        bookingId = response.jsonPath().getInt("bookingid");
        System.out.println("Created Booking ID: " + bookingId);
    }

    @When("user creates a booking")
    public void retriveBookingID() {
        response = bookingpage.getBookingDetails(bookingId);

    }

    @Then("booking details should match the created data")
    public void verifyBookingDetails() {
        response.then().statusCode(200);
        String firstName = response.jsonPath().getString("firstname");
        String lastName = response.jsonPath().getString("lastname");
        int totalPrice = response.jsonPath().getInt("totalprice");
        boolean depositPaid = response.jsonPath().getBoolean("depositpaid");
        String checkinDate = response.jsonPath().getString("bookingdates.checkin");
        String checkoutDate = response.jsonPath().getString("bookingdates.checkout");
        String additionalNeeds = response.jsonPath().getString("additionalneeds");
        Assert.assertEquals(firstName, "Jim");
        Assert.assertEquals(lastName, "Brown");
        Assert.assertEquals(totalPrice, 111);
        Assert.assertTrue(depositPaid);
        Assert.assertEquals(checkinDate, "2026-02-01");
        Assert.assertEquals(checkoutDate, "2026-02-12");
        Assert.assertEquals(additionalNeeds, "Breakfast");
        System.out.println("----- Booking Details -----");
        System.out.println("First Name       : " + firstName);
        System.out.println("Last Name        : " + lastName);
        System.out.println("Total Price      : " + totalPrice);
        System.out.println("Deposit Paid     : " + depositPaid);
        
        System.out.println("Check-in Date    : " + checkinDate);
        System.out.println("Check-out Date   : " + checkoutDate);
        System.out.println("Additional Needs : " + additionalNeeds);
        System.out.println("----------------------------");

        System.out.println("Booking details verified successfully.");

    }
}
