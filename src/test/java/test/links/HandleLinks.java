package test.links;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

public class HandleLinks {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "D:\\webDrivers\\chromedriver-win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver(new ChromeOptions().addArguments("--remote-allow-origins=*"));
        driver.get("https://www.amazon.in/");

        driver.findElement(By.linkText("Today's Deals")).click();
        driver.findElement(By.partialLinkText("Deals")).click();

        //count all the links present at webpage
        List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println("-->Number of links: " + links.size());

        for (WebElement l : links) {
            System.out.println(l.getText());
            System.out.println(l.getAttribute("href"));
        }
    }
}
