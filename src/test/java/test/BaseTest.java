package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Optional;

public class BaseTest {

    protected RemoteWebDriver driver;

    @BeforeTest
    @Parameters({"browser"})
    public void setUp(String browser) throws MalformedURLException {
        String host = Optional.ofNullable(System.getProperty("hub_host")).orElse("localhost");
        String browserName = Optional.ofNullable(browser).orElse("firefox");
        String remote_url = "http://" + host + ":4444/wd/hub";
        if (browserName.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            this.driver = new RemoteWebDriver(new URL(remote_url), options);
        } else if (browserName.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            this.driver = new RemoteWebDriver(new URL(remote_url), options);
        }
        this.driver.manage().window().maximize();
    }

    @AfterTest
    public void tearDown() {
        this.driver.quit();
    }

    public void waitForVisibilityAndClick(By by) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(by)).click();
    }

    public String waitForVisibilityAndGetText(By by) {
        return new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(by)).getText();
    }

    public void waitForVisibilityAndSendText(By by, String text) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(by)).sendKeys(text);
    }

    public int waitUntilElementsAreMoreThanZero(By by) {
        return new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.numberOfElementsToBeMoreThan(by, 0)).size();
    }

    public WebElement waitUntilElementIsVisible(By by) {
        return new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(by));
    }
}