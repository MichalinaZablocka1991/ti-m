package regression;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest {


        protected WebDriver driver;

        @BeforeEach
        public void setUp() {
            System.setProperty("webdriver.edge.driver", "C:/Users/MichalinaA/Desktop/Java learning/chromedriver/chromedriver.exe");
            driver = new ChromeDriver();
        }

        @AfterEach
        public void tearDown() {
            System.out.println("Closing the browser");
            if (driver != null) {
                driver.quit();
            }
        }

}
