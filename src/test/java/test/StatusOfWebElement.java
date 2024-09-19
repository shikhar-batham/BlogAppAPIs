package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class StatusOfWebElement {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "D:\\webDrivers\\chromedriver-win32\\chromedriver.exe");

        WebDriver driver = new ChromeDriver(new ChromeOptions().addArguments("--remote-allow-origins=*"));
//        driver.get("https://www.nopcommerce.com/en");
        driver.get("http://localhost:3000/signup");
        WebElement element = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/form/div[3]/div[1]"));
        System.out.println("-->Displayed: " + element.isDisplayed());
        System.out.println("-->Enabled: "+element.isEnabled());

        System.out.println("-->Selected: "+element.isSelected());
        element.click();
        System.out.println("-->Selected:"+element.isSelected());

    }
}
