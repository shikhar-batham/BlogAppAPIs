package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class PriceSlider {
    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "D:\\webDrivers\\chromedriver-win32\\chromedriver.exe");

        List<String> options = List.of("--remote-allow-origins=*", "--disable-notifications");
        WebDriver driver = new ChromeDriver(new ChromeOptions().addArguments(options));
        driver.get("https://www.w3schools.com/howto/howto_js_rangeslider.asp");

        WebElement minSlider = driver.findElement(By.xpath("//input[@id='id2']"));
        System.out.println("-->Location" + minSlider.getLocation());//252,545
        System.out.println("-->Height and width of the element" + minSlider.getSize());//601,24

        Actions actions = new Actions(driver);
        actions.dragAndDropBy(minSlider, 100, 0).perform();

        System.out.println("-->Location" + minSlider.getLocation());//252,545
        System.out.println("-->Height and width of the element" + minSlider.getSize());//601,24
    }
}
