package test.mouseclicks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class MouseRightClickAction  {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "D:\\webDrivers\\chromedriver-win64\\chromedriver.exe");

        List<String> options = List.of("--remote-allow-origins=*", "--disable-notifications");
        WebDriver driver = new ChromeDriver(new ChromeOptions().addArguments(options));
        driver.get("https://swisnl.github.io/jQuery-contextMenu/demo.html");

        WebElement button = driver.findElement(By.xpath("//span[@class='context-menu-one btn btn-neutral']"));
        Actions actions = new Actions(driver);

        actions.contextClick(button).perform();// perform Right Click
    }
}
