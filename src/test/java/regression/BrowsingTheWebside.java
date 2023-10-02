package regression;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertEquals;

public class BrowsingTheWebside extends BaseTest {
    @Test
    void goingToEngineeringSection() {

        // Open https://www.ti8m.com/de/career
        driver.get("https://www.ti8m.com/de/career");

        // Accept cookies
        WebElement acceptCookies = driver.findElement(By.id("onetrust-accept-btn-handler"));
        acceptCookies.click();

        // Choose english
        WebElement english = driver.findElement(By.xpath("//a[@aria-label='Englisch']"));
        english.click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        // Choose Career at the top menu
        WebElement careerTopMenu = driver.findElement(By.partialLinkText("Career"));
        careerTopMenu.click();

        // Scroll down
        WebElement element = driver.findElement(By.cssSelector(".container.py-5"));
        /*JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1500)", "");*/
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);

        // Chose Engineering section
        WebElement engineeringSection = driver.findElement(By.partialLinkText("Engineering"));
        engineeringSection.click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        // Check if Engineering section is opened
        WebElement engineeringSectionName = driver.findElement(By.cssSelector(".content-page-header__tag"));
        String actualText = engineeringSectionName.getText();
        String expectedText = "Engineering";
        try {
            Assert.assertEquals(expectedText, actualText);
            System.out.println("Text match");
        } catch (AssertionError e) {
            System.out.println("Text does not match");
            throw e; // Rethrow the caught exception
        }
    }
}
