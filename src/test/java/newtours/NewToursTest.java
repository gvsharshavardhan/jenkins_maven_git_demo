package newtours;

import mytests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class NewToursTest extends BaseTest {

    private String noOfPassengers;
    private String expectedPrice;

    @BeforeTest
    @Parameters({"noOfPassengers", "expectedPrice"})
    public void setupParameters(String noOfPassengers, String expectedPrice) {
        this.noOfPassengers = noOfPassengers;
        this.expectedPrice = expectedPrice;
    }

    @Test
    public void testTours() {
        driver.get("https://vins-udemy.s3.amazonaws.com/docker/docker-book-flight.html#");
        waitForVisibilityAndClick(By.cssSelector("[id='register-btn']"));
        waitForVisibilityAndClick(By.partialLinkText("Flights"));
        WebElement se = waitUntilElementIsVisible(By.cssSelector("[id='passCount']"));
        Select s = new Select(se);
        s.selectByValue(noOfPassengers);
        waitForVisibilityAndClick(By.cssSelector("[id='findFlights']"));
        waitForVisibilityAndClick(By.cssSelector("[id='reserveFlights']"));
        waitForVisibilityAndClick(By.cssSelector("[id='buyFlights']"));
        String totalPrice = waitForVisibilityAndGetText(By.xpath("//*[.='Total Price']/..//font"));
        Assert.assertEquals(totalPrice, expectedPrice, "prices are not matching!");
    }
}