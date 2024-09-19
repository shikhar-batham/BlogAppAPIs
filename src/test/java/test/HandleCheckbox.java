package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HandleCheckbox extends BaseClass {

    public static void main(String[] args) {

        initialize();
        driver.get("https://testautomationpractice.blogspot.com/");

        //single checkbox click
//        driver.findElement(By.xpath("//label[normalize-space()='Sunday']")).click();

        //multiple check box click
        List<WebElement> checkBoxList = driver.findElements(By.xpath("//input[@type='checkbox' and contains(@id,'day')]"));
//        for (WebElement cb : checkBoxList) cb.click();

        // click only on sunday and saturday
        for (WebElement cb : checkBoxList) {
            if (cb.getAttribute("id").equalsIgnoreCase("Sunday") || cb.getAttribute("id").equalsIgnoreCase("Saturday"))
                cb.click();
        }
    }
}
