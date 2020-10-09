package org.example.w3schools;

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

import static org.junit.Assert.assertTrue;

public class StepDefinitionsW3Schools extends BaseSelenium {

    private String tableRowText;

    @After()
    public void closeBrowser() {
        try{
            driver.quit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @After("@browser and not @headless")
    public void doSomethingAfter(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            String screenshotBase64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
            scenario.attach(file, "image/png", screenshotBase64);
        }
    }

    @Given("I'm on w3schools HTML tables page")
    public void im_on_w3schools_table_page() {
        initializeDriver();
        driver.get("https://www.w3schools.com/html/html_tables.asp");
    }

    @When("I check the {string} row")
    public void i_check_first_row(String row) {
        int rowNumber = Integer.parseInt(row) + 1;
        WebElement tableRow = driver.findElement(By.cssSelector("#customers > tbody > tr:nth-child(" + rowNumber + ")"));
        tableRowText = tableRow.getText();
    }

    @Then("Page contains back button")
    public void page_contains_back_button() {
        WebElement backButton = driver.findElement(By.cssSelector("#backtotutcontainer > button"));
        assertTrue(backButton.isDisplayed());
    }

    @Then("It should contain {string}")
    public void it_should_contain(String name) {
        assertTrue(tableRowText.contains(name));
    }

    @When("I close browser")
    public void i_close_browser() {
        driver.quit();
    }

    @When("I click on {string}")
    public void i_click_on(String byIdentifierCssSelector) {
        clickOnElementAndGoToChildTabIfNeeded(By.cssSelector(byIdentifierCssSelector));
    }

    @Then("It should open new page with url: {string}")
    public void it_should_redirect_me_to(String url) {
        assertTrue(driver.getCurrentUrl().equals(url));
    }

    @When("I close child Tab")
    public void i_close_child_tab() {
        closeSecondTabAndReturnToPrimary();
    }

    @Then("I have only one tab open")
    public void i_have_one_tab_open() {
        assertTrue(!moreThanOneTabOpen());
    }
}
