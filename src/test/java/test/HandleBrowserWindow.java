package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.Set;

public class HandleBrowserWindow {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "D:\\webDrivers\\chromedriver-win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver(new ChromeOptions().addArguments("--remote-allow-origins=*"));
        driver.get("https://www.orangehrm.com/");

        String windowId = driver.getWindowHandle();
        System.out.println("-->Window Id: " + windowId);

        WebElement element = driver.findElement(By.xpath("//footer//ul/li[contains(.,'Open Source HRMS')]"));
        System.out.println("-->First Link: " +element);
        element.click();

        Set<String> windowHandles = driver.getWindowHandles();
        System.out.println("-->Window Set: " + windowHandles);
    }
}
