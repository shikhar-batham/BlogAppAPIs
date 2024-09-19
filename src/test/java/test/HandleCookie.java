package test;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Set;

public class HandleCookie {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "D:\\webDrivers\\chromedriver-win32\\chromedriver.exe");

        WebDriver driver = new ChromeDriver(new ChromeOptions().addArguments("--remote-allow-origins=*"));
        driver.get("https://www.nopcommerce.com/en");

        //get cookies from the browser
        Set<Cookie> cookies = driver.manage().getCookies();
        System.out.println("-->Size of the cookie set" + cookies.size());

        for (Cookie cookie : cookies) {
            System.out.println("-->" + cookie.getName() + " " + cookie.getValue());
        }

        //add cookie
        Cookie obj = new Cookie("My Cookie", "123");
        driver.manage().addCookie(obj);
        cookies = driver.manage().getCookies();
        System.out.println("-->Size of the cookie set after adding one cookie" + cookies.size());
        for (Cookie cookie : cookies) {
            System.out.println("-->" + cookie.getName() + " " + cookie.getValue());
        }

        //Delete cookie
//        driver.manage().deleteCookie(obj);
        driver.manage().deleteCookieNamed("My Cookie");
        cookies = driver.manage().getCookies();
        System.out.println("-->Size of the cookie set after deleting one cookie" + cookies.size());

        //delete all cookie
        driver.manage().deleteAllCookies();
        System.out.println("-->Size of the cookie set after deleting all cookie" + cookies.size());

    }
}
