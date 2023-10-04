package regression;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest {


        protected WebDriver driver;

        @BeforeEach
        public void setUp() {
            System.setProperty("webdriver.chrome.driver", "C:/Users/MichalinaA/Desktop/Java learning/chromedriver/chromedriver.exe");
            driver = new ChromeDriver();

            // Open the webpage
            driver.get("https://www.ti8m.com/de/career");

            // Accept cookies
            WebElement acceptCookies = driver.findElement(By.id("onetrust-accept-btn-handler"));
            acceptCookies.click();
        }

        @AfterEach
        public void tearDown() {
            System.out.println("Closing the browser");
            if (driver != null) {
                driver.quit();
            }
        }

}
