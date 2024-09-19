package test.links;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class HandleBrokenLinks {

    public static void main(String[] args) throws IOException {

        System.setProperty("webdriver.chrome.driver", "D:\\webDrivers\\chromedriver-win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver(new ChromeOptions().addArguments("--remote-allow-origins=*"));
        driver.get("http://www.deadlinkcity.com/");

        try {
            driver.findElement(By.xpath("//*[@id=\"reload-button\"]")).click();
        } catch (Exception e) {
        }

        List<WebElement> links = driver.findElements(By.tagName("a"));
        int count = 0;
        for (WebElement e : links) {
            String url = e.getAttribute("href");
            if (url == null || url.isEmpty()) continue;

            URL link = new URL(url);
            try {
                HttpURLConnection httpConnection = (HttpURLConnection) link.openConnection();
                httpConnection.connect();
                if (httpConnection.getResponseCode() >= 400) {
                    System.out.println("-->" + httpConnection.getResponseCode() + " " + url);
                    count++;
                }

            } catch (Exception exception) {
            }
        }
        System.out.println("-->Total Broken Links:" + count);
    }
}
