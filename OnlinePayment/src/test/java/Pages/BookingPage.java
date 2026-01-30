package Pages;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BookingPage {

    public Response createBooking(){

        String payload = "{\n" +
                "    \"firstname\" : \"Jim\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2026-02-01\",\n" +
                "        \"checkout\" : \"2026-02-12\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";
                return given().contentType(ContentType.JSON).body(payload).when().post("/booking");
    }

    public Response getBookingDetails(int bookingId){
        return given().pathParam("id",bookingId).when().get("/booking/{id}");
    }



}
