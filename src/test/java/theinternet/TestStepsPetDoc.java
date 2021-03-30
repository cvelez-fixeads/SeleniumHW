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

public class TestStepsPetDoc {

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
            options.addArguments("--ignore-ssl-errors=yes");
            options.addArguments("--ignore-certificate-errors");
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

    @Given("Navigate to Petdoc")
    public void navigateToPetdoc() {
        driver.navigate().to("https://bladescave.dyndns.org:4433/Petdoc");

    }

    @When("^A User enters a user (.+)")
    public void aUserEntersAUserKike(String user) {
        driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/input[1]")).sendKeys(user);
    }

    @And("^A User enters a valid password (.+)")
    public void aUserEntersAValidPasswordSecret(String password) {
        driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/input[2]")).sendKeys(password);

    }

    @And("A User clicks in entrar")
    public void aUserClicksInEntrar() {
        driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/input[3]")).click();
    }

    @Then("Application should show succes")
    public void applicationShouldShowSucces() {
        String output = driver.findElement(By.xpath(" //*[@id=\"header\"]/h2/span")).getText();
        assertThat(output, stringContainsInOrder("Usuario", "kike"));

    }
}
