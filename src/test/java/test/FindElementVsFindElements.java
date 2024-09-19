package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class FindElementVsFindElements {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "D:\\webDrivers\\chromedriver-win32\\chromedriver.exe");

        WebDriver driver = new ChromeDriver(new ChromeOptions().addArguments("--remote-allow-origins=*"));
        driver.navigate().to("https://www.amazon.in/");
//        driver.navigate().to("https://www.nopcommerce.com/en");
        WebElement searchBox = driver.findElement(By.xpath("//*[@id=\"twotabsearchtextbox\"]"));
        System.out.println("-->Search Box: " + searchBox.isEnabled());
        System.out.println("-->Search box value: " + searchBox.getAttribute("placeholder"));

        if (searchBox.isEnabled()) {
            searchBox.clear();
            searchBox.sendKeys("keyboard");

            WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"nav-search-submit-button\"]"));
            if (searchButton.isEnabled())
                searchButton.click();
        }

        WebElement twotabsearchtextbox = driver.findElement(By.id("twotabsearchtextbox"));
        String value = twotabsearchtextbox.getAttribute("value");
        System.out.println("-->Search box value: " + value);



//        List<WebElement> links = driver.findElements(By.xpath("//*[@id=\"en-page\"]/body/div[7]/footer/div[1]/div/div//a"));
//        System.out.println("Navigation Bar: " + links.size());
//
//        for (WebElement ele:links){
//            System.out.println(ele.getText());
//        }


    }
}
