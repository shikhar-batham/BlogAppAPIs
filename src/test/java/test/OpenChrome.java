package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class OpenChrome {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "D:\\webDrivers\\chromedriver-win32\\chromedriver.exe");
//        WebDriverManager.chromedriver().setup();

//        WebDriverManager.edgedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);

        driver.get("http://172.19.0.1:3000/");

        System.out.println("Page Title" + driver.getTitle());
        System.out.println("Current Url" + driver.getCurrentUrl());

        System.out.println(driver.getPageSource());
    }
}
