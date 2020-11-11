package org.example.frisco.pages;

import org.example.base.BaseSelenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class FriscoLogin extends BaseSelenium {

    private String url = "https://www.frisco.pl/";
    private By openLoginWindowButton = By.xpath("//*[@id=\"header\"]/div/div[1]/div/div[3]/div/a[1]");
    private By loginButton = By.xpath("//*[@id=\"container\"]/div/div[2]/div/div[2]/div/form/section/input");
    private By loginInput = By.name("username");

    private By passwordInput = By.xpath("//*[@id=\"loginPassword\"]");
    private String login = System.getenv("fr_log");
    private String pswd = System.getenv("fr_pswd");

    private By loggedIn = By.className("logged-in");

    public FriscoReservartions loginToPage() {
        initializeDriver();
        driver.get(url);
        processLogin(login, pswd);
        return new FriscoReservartions();
    }

    private void processLogin(String log, String password) {
        driver.findElement(openLoginWindowButton).click();
        WebElement loginBox = driver.findElement(loginInput);
        WebElement pswdBox = driver.findElement(passwordInput);
        loginBox.sendKeys(log);
        pswdBox.sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public boolean isLoggedInCorrectly() {
        return driver.findElement(loggedIn).isDisplayed();
    }

}
