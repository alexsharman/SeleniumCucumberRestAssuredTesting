package org.example.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseSelenium {

    private Properties prop;
    public WebDriver driver;

    public ChromeOptions getChromeOptionsHandleSSL() {
        // Create an object of desired capabilities class with Chrome driver
        DesiredCapabilities handlerSSLer = new DesiredCapabilities();

        handlerSSLer.acceptInsecureCerts();
        handlerSSLer.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        handlerSSLer.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.merge(handlerSSLer);
        return chromeOptions;
    }

    public WebDriver initializeDriver() {
        prop = new Properties();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\data.properties");
            prop.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String browserName = prop.getProperty("browser");
        if (browserName.contains("chrome")) {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/driver.chromium.86/chromedriver.exe");
            ChromeOptions options = getChromeOptionsHandleSSL();
            driver = new ChromeDriver(options);
        } else if (browserName.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
            driver = new FirefoxDriver();
        } else if (browserName.equals("IE")) {
            System.setProperty("webdriver.ie.driver", "src/test/resources/IEDriverServer.exe");
            driver = new InternetExplorerDriver();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    public Boolean moreThanOneTabOpen() {
        return driver.getWindowHandles().size() > 1;
    }

    public void switchToSecondTab() {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }

    public void closeSecondTabAndReturnToPrimary() {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        if (tabs.size() > 1) {
            driver.switchTo().window(tabs.get(1));
            driver.close();
            driver.switchTo().window(tabs.get(0));
        }
    }

    public void clickOnElementAndGoToChildTabIfNeeded(By byIdentifier) {
        driver.findElement(byIdentifier).click();
        if (moreThanOneTabOpen()) {
            switchToSecondTab();
        }
    }

    public WebElement findElementByText(String text) {
        return driver.findElement(By.xpath("//*[text()='" + text + "']"));
    }
}
