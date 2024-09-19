package test.mouseclicks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class DragAndDrop {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "D:\\webDrivers\\chromedriver-win32\\chromedriver.exe");

        List<String> options = List.of("--remote-allow-origins=*", "--disable-notifications");
        WebDriver driver = new ChromeDriver(new ChromeOptions().addArguments(options));
        driver.get("http://dhtmlgoodies.com/scripts/drag-drop-custom/demo-drag-drop-3.html");

        WebElement source = driver.findElement(By.id("box6"));
        WebElement target = driver.findElement(By.id("box106"));

        Actions actions = new Actions(driver);
        actions.dragAndDrop(source, target).perform();
    }
}
