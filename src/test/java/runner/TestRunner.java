package runner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import com.github.mkolisnyk.cucumber.runner.ExtendedCucumberOptions;
import com.github.mkolisnyk.cucumber.reporting.CucumberDetailedResults;
import com.github.mkolisnyk.cucumber.runner.ExtendedCucumber;

@RunWith(ExtendedCucumber.class)
@ExtendedCucumberOptions(

        jsonReport = "target/cucumber.json",
        retryCount = 0,
        overviewReport = true,
        detailedReport = true,
        detailedAggregatedReport = true,
        jsonUsageReport = "target/cucumber-usage.json",
        usageReport = false,
        toPDF = true,
        excludeCoverageTags = {"@flaky"},
        includeCoverageTags = {"@passed"},
        outputFolder = "target",
        screenShotLocation = "Autscreenshots/",
        screenShotSize = "300px"

)
@CucumberOptions(

        features = {
                "src/test/resources/features/stibo_test.feature"
        },
        tags = {
                "@testWithExcel"
        },

        plugin = {
                "html:target/cucumber-html-report",
                "pretty:target/cucumber-json-report.json",
                "html:target/cucumberHtmlReport",
                "json:target/cucumber.json",
                "pretty:target/cucumber-pretty.txt",
                "junit:target/cucumber-results.xml"
        },

        glue = {"stepDefinitions"},
        monochrome = true
)

public class TestRunner extends CucumberDetailedResults {

}
