package test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class OpenLinkInOtherTab {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "D:\\webDrivers\\chromedriver-win32\\chromedriver.exe");

        WebDriver driver = new ChromeDriver(new ChromeOptions().addArguments("--remote-allow-origins=*"));
        driver.get("https://www.flipkart.com/");

//        WebElement src = driver.findElement(By.xpath("//span[@class='user-actions-ico']//*[name()='svg']//*[name()='path' and contains(@fill-rule,'evenodd')]"));
//        WebElement register = driver.findElement(By.xpath("//span[normalize-space()='Register']"));
//
//        Actions actions = new Actions(driver);
//        String chord = Keys.chord(Keys.CONTROL, Keys.RETURN);
//        actions.moveToElement(src).moveToElement(register).sendKeys(chord).perform();

        String chord = Keys.chord(Keys.CONTROL, Keys.RETURN);
        driver.findElement(By.linkText("Grocery")).sendKeys(chord);
    }
}
