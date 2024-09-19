package test.mouseclicks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class DoubleClick {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "D:\\webDrivers\\chromedriver-win32\\chromedriver.exe");

        List<String> options = List.of("--remote-allow-origins=*", "--disable-notifications");
        WebDriver driver = new ChromeDriver(new ChromeOptions().addArguments(options));
        driver.get("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_ondblclick_addeventlistener");

        driver.switchTo().frame("iframeResult");
        WebElement demo = driver.findElement(By.id("demo"));

        Actions actions = new Actions(driver);
        actions.doubleClick(demo).perform();
    }
}
