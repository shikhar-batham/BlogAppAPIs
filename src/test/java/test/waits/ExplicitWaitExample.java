package test.waits;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ExplicitWaitExample {

    //we can put explicit wait on any web element
    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "D:\\webDrivers\\chromedriver-win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver(new ChromeOptions().addArguments("--remote-allow-origins=*"));
        driver.get("https://www.google.com/");

        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.findElement(By.name("q")).sendKeys("Selenium");
        driver.findElement(By.name("q")).sendKeys(Keys.RETURN);

//        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='https://www.selenium.dev/']//h3[@class='LC20lb MBeuO DKV0Md'][normalize-space()='Selenium']"))).click();

        By locator = By.xpath("//a[@href='https://www.selenium.dev/']//h3[@class='LC20lb MBeuO DKV0Md'][normalize-space()='Selenium']");
        waitForElementPresent(driver, 10, locator).click();

    }

    static WebElement waitForElementPresent(WebDriver driver, int timeout, By locator) {

        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        WebElement element = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element;
    }

}
