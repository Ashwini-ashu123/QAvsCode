package Hooks;
import utilities.*;



import io.cucumber.java.After;
import io.cucumber.java.Before;

public class hooks {

    @Before
    public void launchBrowser(){
        DriverFactory.setup();

    }

    @After
    public void closeBrowser(){
        DriverFactory.tearDown();
    }


}
