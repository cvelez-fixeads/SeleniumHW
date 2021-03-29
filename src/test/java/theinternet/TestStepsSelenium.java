package theinternet;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.nio.file.Paths;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestStepsSelenium {

    private WebDriver driver;
    private Scenario scenario;

    @Before
    public void setUp(Scenario scenario) {

        this.scenario = scenario;

        System.setProperty("webdriver.chrome.driver",
                Paths.get("src/test/resources/chromedriver_standalone/chromedriver").toString());

        if (driver == null) {
            ChromeOptions options = new ChromeOptions();
            options.setHeadless(true);
            options.addArguments("--headless");
            driver = new ChromeDriver(options);
        }

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    private void takeScreenShot(){
        try {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.write(String.format("[%tDT%1$tT] Browser screenshot at scenario end:", Calendar.getInstance().getTime()));
            scenario.embed(screenshot, "image/jpeg");
        } catch (Exception ex) {
            // failure to take a screenshot should not fail the test
            ex.printStackTrace();
        }
    }

    @After
    public void tearDown() {

        if (driver!=null) {
            takeScreenShot();
            driver.close();
            driver.quit();
        }
    }

    @Given("Navigate to Page Seleniumeasy")
    public void navigateToPageSeleniumeasy() {
        //Selenium test site
        driver.navigate().to("https://www.seleniumeasy.com/test/basic-first-form-demo.html");
    }

    @When("^A User enters a valid input text (.+)")
    public void aUserEntersAValidInputText(String text) {
        driver.findElement(By.id("user-message")).sendKeys(text);
    }


    @And("A User clicks on Show Message button")
    public void aUserClicksOnShowMessageButton() {
        //*[@id="get-input"]/button
        driver.findElement(By.xpath("//*[@id=\"get-input\"]/button")).click();
    }

    @Then("^Application shows message (.+)")
    public void applicationShowsMessage(String expectedOutput) {
        String outputText = driver.findElement(By.id("display")).getText();
        assertThat(outputText, is(expectedOutput));
    }
}
