package regression;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class Contact extends BaseTest {
    @Test
    void contactForm () {

        // Choose english
        WebElement english = driver.findElement(By.xpath("//a[@aria-label='Englisch']"));
        english.click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        // Go to Career section
        WebElement career = driver.findElement(By.cssSelector("[data-path='/ti8m-ch/career']"));
        career.click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        // Chose Career section in the menu
        WebElement career2 = driver.findElement(By.partialLinkText("Career"));
        career2.click();

        // Scroll down
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,4000)", "");

        // Type message
        WebElement contactfirstNameField = driver.findElement(By.name("bab55ca1-ab7f-ed11-81ac-000d3a253f23"));
        contactfirstNameField.sendKeys("Test");

        // Type name
        WebElement contactcontactfirstNameField = driver.findElement(By.name("3f746946-34b4-442c-a677-e232cdd2bc40"));
        contactcontactfirstNameField.sendKeys("Michalina");

        // Type surname
        WebElement contactcontactlastNameField = driver.findElement(By.name("e1dfc514-f301-4cb2-855a-4c8fa8331207"));
        contactcontactlastNameField.sendKeys("Zablocka");

        // Type company name
        WebElement contactcompaanyField = driver.findElement(By.name("118ff79c-f932-ea11-a813-000d3a2d09d1"));
        contactcompaanyField.sendKeys("Cognizant");

        // Type mail
        WebElement contactmailField = driver.findElement(By.name("7f685ebb-7c54-4cff-a1bc-772562d25c38"));
        contactmailField.sendKeys("zablockamichalina@gmail.com");

        // Type phone
        WebElement contactphoneField = driver.findElement(By.name("ac6a065d-364e-40d6-9a19-d9bf1ed4aa3e"));
        contactphoneField.sendKeys("+41766512873");

        // Accept data privacy
        WebElement contactdataPrivacyField = driver.findElement(By.name("1e717d49-b478-ea11-a811-000d3a2d0476"));
        contactdataPrivacyField.click();

        // Submit the form
        WebElement contactSubmit = driver.findElement(By.name("submit7a4da9d4-9286-ad22-b24d-c67b590cc4f3"));
        contactSubmit.click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        // Check if confirmation is visble
        try {
            WebElement messageSent = driver.findElement(By.xpath("//div[@data-submissionresponse='success']"));
            System.out.println("The contact form was successfully sent!");
        } catch (NoSuchElementException e) {
            System.out.println("The contact form was not sent!!");
        }

    }
}
