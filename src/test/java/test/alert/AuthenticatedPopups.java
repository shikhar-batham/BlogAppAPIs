package test.alert;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class AuthenticatedPopups {
    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "D:\\webDrivers\\chromedriver-win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver(new ChromeOptions().addArguments("--remote-allow-origins=*"));
        driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");

    }
}
