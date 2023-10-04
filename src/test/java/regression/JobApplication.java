package regression;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class JobApplication extends BaseTest {

    @Test
    void engineerJobApplication() {

        // Go to Career section
        WebElement career = driver.findElement(By.cssSelector("[data-path='/ti8m-ch/career']"));
        career.click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        // Chose Engineering (jobs)
        WebElement engineering = driver.findElement(By.partialLinkText("Engineering"));
        engineering.click();

        // Scroll down
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,3000)", "");

        // Chose 2nd job offer
        driver.switchTo().frame("iframe-552c1559-9a64-4b42-8c48-d3ea3b7d0396");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        WebElement firstJob = driver.findElement(By.cssSelector(".job.job-3"));
        String text = firstJob.getText();
        System.out.println(text.split("\n")[0]);
        WebElement firstLink = firstJob.findElement(By.partialLinkText(text.split("\n")[0]));
        firstLink.click();

        // Switch to a new (2nd) tab
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.numberOfWindowsToBe(2));
        String originalWindow = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            if (!handle.equals(originalWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }

        // Click on apply button
        WebElement applyNow = driver.findElement(By.xpath("//footer//a[@class='button apply-button'][contains(.,'Jetzt bewerben')]"));
        applyNow.click();

        // Switch to a new (3rd) tab
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.numberOfWindowsToBe(3));
        List<String> windowHandlesList = new ArrayList<>(driver.getWindowHandles());
        if(windowHandlesList.size() >= 3) {
            driver.switchTo().window(windowHandlesList.get(2));
        } else {
            throw new IllegalStateException("Expected 3 tabs, but got " + windowHandlesList.size());
        }

        // Choose gender
        WebElement dropdown = driver.findElement(By.name("customeraddressshopperanrede"));
        Select select = new Select(dropdown);
        select.selectByValue("2");

        // Type name
        WebElement firstNameField = driver.findElement(By.name("customeraddressshoppervorname"));
        firstNameField.sendKeys("Michalina");

        // Type surname
        WebElement lastNameField = driver.findElement(By.name("customeraddressshoppername"));
        lastNameField.sendKeys("Zablocka");

        // Type city
        WebElement placeField = driver.findElement(By.name("customeraddressshopperort"));
        placeField.sendKeys("Wadenswil");

        // Type mail
        WebElement mailField = driver.findElement(By.name("customeraddressshopperaddressemail"));
        mailField.sendKeys("zablockamichalina@gmail.com");

        // Type potential start date
        WebElement startDateField = driver.findElement(By.name("LAA__USERFIELD22"));
        startDateField.sendKeys("03.01.2024");

        // Type how do you know about ti&m
        WebElement startField = driver.findElement(By.name("LAA__USERFIELD25"));
        startField.sendKeys("Freund");

        // Upload CV
        WebElement fileInput = driver.findElement(By.cssSelector("input[type='file']"));
        fileInput.sendKeys("C:/Users/MichalinaA/Desktop/Java learning/ti-m/data/Michalina_Zablocka_CV.pdf");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[title='Löschen']")));

        // Agree on data policy
        WebElement dataPrivacyField = driver.findElement(By.name("jobportal_taca"));
        dataPrivacyField.click();

        // To be deleted
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        // Submit your application
        WebElement submit = driver.findElement(By.name("jobportal_application_submit"));
        submit.click();

        // Check if confirmation is visible
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        try {
            WebElement confirmationMessage = driver.findElement(By.xpath("//p[contains(text(),'Bewerbung abgeschickt.\n')]"));
            System.out.println("The job application form was successfully sent!");
        } catch (NoSuchElementException e) {
            System.out.println("The job application form was not sent!");
        }
    }
}
