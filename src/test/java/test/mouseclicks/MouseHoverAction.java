package test.mouseclicks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class MouseHoverAction {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "D:\\webDrivers\\chromedriver-win32\\chromedriver.exe");

        List<String> options = List.of("--remote-allow-origins=*", "--disable-notifications");
        WebDriver driver = new ChromeDriver(new ChromeOptions().addArguments(options));
        driver.get("https://www.flipkart.com/");

        WebElement source = driver.findElement(By.xpath("//span[contains(text(),'Electronics')]"));
        WebElement target = driver.findElement(By.xpath("//a[@class='_1BJVlg _11MZbx']"));

        Actions actions = new Actions(driver);
        actions.moveToElement(source).moveToElement(target).click().perform();
    }

}
