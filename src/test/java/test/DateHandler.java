package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

public class DateHandler {

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "D:\\webDrivers\\chromedriver-win32\\chromedriver.exe");

        List<String> options = List.of("--remote-allow-origins=*", "--disable-notifications");
        WebDriver driver = new ChromeDriver(new ChromeOptions().addArguments(options));
        driver.get("https://www.rewredbus.in/");

        driver.findElement(By.xpath("//div[@id='onwardCal']")).click();

        String year = "2024";
        String month = "Oct";
        String date = "10";

        while (true) {
            String monthYear = driver.findElement(By.xpath("//div[@class='DayNavigator__CalendarHeader-qj8jdz-1 fxvMrr']//div[2]")).getText();

            String[] arr = monthYear.split(" ");
            String[] split = arr[1].split("\n");

            if (month.equalsIgnoreCase(arr[0]) && year.equals(split[0]))
                break;
            else
                driver.findElement(By.xpath("//div[@class='DayNavigator__CalendarHeader-qj8jdz-1 fxvMrr']//div[3]//*[name()='svg']")).click();
        }

        //working till here
        //calendar disappearing here that's why giving error

        List<WebElement> allDates = driver.findElements(By.xpath("//table[@class='rb-monthTable first last']//td"));
        for (WebElement ele : allDates) {
            if (ele.getText().equals(date)) {
                ele.click();
                break;
            }
        }
    }
}
