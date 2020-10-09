package org.example.datatables;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.base.BaseSelenium;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class StepDefinitionsDatatables extends BaseSelenium {

    @After()
    public void closeBrowser() {
        driver.quit();
    }

    @After("@browser and not @headless")
    public void doSomethingAfter(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            String screenshotBase64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
            scenario.attach(file, "image/png", screenshotBase64);
        }
    }

    @Given("I'm on datatables zero configuration webpage")
    public void openDatatablesWebPage() {
        initializeDriver();
        driver.get("https://datatables.net/examples/basic_init/zero_configuration.html");
    }

    @When("I search {string}")
    public void iSearch(String arg0) {
        WebElement search = driver.findElement(By.cssSelector("#example_filter > label > input[type=search]"));
        search.sendKeys(arg0);
    }

    @Then("I should have {string} answers")
    public void iShouldHaveAnswers(String arg0) {
        List<WebElement> rows = driver.findElements(By.cssSelector("#example > tbody > tr"));
        int result = Integer.parseInt(arg0);
        assertEquals(rows.size(), result);
    }
}
