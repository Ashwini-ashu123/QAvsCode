package Pages;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

public class productAPIPage {

  
    public Response getAllProducts(){
        return given().when().get("/productsList");
    
    }

   

    }


