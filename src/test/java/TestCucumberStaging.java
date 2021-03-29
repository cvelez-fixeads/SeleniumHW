import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"theinternet"},
        tags = {"@automation:done"},
        plugin = {
                "com.olx.mercury.test.report.cucumber.CucumberProgressFormatterWithoutScenarioOutput",
                "com.olx.mercury.test.report.cucumber.CucumberSignalFX",
                "pretty:build/reports/cucumber/pretty.txt",
                "com.olx.mercury.test.report.restassured.CucumberRestAssuredTrace:build/reports/api-coverage",
                "com.olx.mercury.test.report.cucumber.CucumberJsonSummaryPrinter:build/reports/cucumber-summary.json",
                "html:build/reports/cucumber/html",
                "json:build/reports/cucumber/cucumber.json",
                "junit:build/reports/cucumber/cucumber.xml",
                "rerun:rerun.txt",
                "com.olx.mercury.test.report.cucumber.CucumberFileSummaryPrinter:build/reports/cucumber/summary.txt"
        })
public class TestCucumberStaging extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
