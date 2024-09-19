package test.dropdowns;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortedDropdownList {

    /*check whether dropdown list is sorted of not*/

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "D:\\webDrivers\\chromedriver-win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver(new ChromeOptions().addArguments("--remote-allow-origins=*"));
        driver.get("https://www.twoplugs.com/");

        driver.findElement(By.id("details-button")).click();
        driver.findElement(By.xpath("//*[@id=\"details\"]//a")).click();

        driver.findElement(By.xpath("//a[normalize-space()='Live Posting']")).click();
        WebElement categoryDropdown = driver.findElement(By.name("category_id"));

        Select select = new Select(categoryDropdown);
        List<WebElement> optionList = select.getOptions();

        ArrayList<String> originalList = new ArrayList<>();
        ArrayList<String> tempList = new ArrayList<>();

        for (WebElement ele : optionList) {
            originalList.add(ele.getText());
            tempList.add(ele.getText());
        }

        System.out.println("-->Original List:" + originalList);
        System.out.println("-->Temp List:" + tempList);

        Collections.sort(tempList);

        if (originalList.equals(tempList)) {
            System.out.println("-->Lists are sorted");
        } else {
            System.out.println("-->Lists are not sorted");
        }

        driver.close();
    }
}
