package searchModule;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import test.BaseTest;

public class SearchModuleTest extends BaseTest {

    @Test
    @Parameters({"keyword"})
    public void testSearchModule(String keyword) {
        driver.get("https://duckduckgo.com/");
        waitForVisibilityAndSendText(By.cssSelector("[name='q']"), keyword);
        waitForVisibilityAndClick(By.cssSelector("#search_button_homepage"));
        waitForVisibilityAndClick(By.cssSelector("[data-zci-link=\"videos\"]"));
        int size = waitUntilElementsAreMoreThanZero(By.cssSelector(".tile--vid"));
        Assert.assertTrue(size > 0, "no elements found!");
    }
}