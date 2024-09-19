package test.dropdowns;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import test.BaseClass;

import java.util.List;

public class HandlingDropdown extends BaseClass {

    public static void main(String[] args) {

        initialize();
        driver.get("http://localhost:3000/signup");

        WebElement dropdown = driver.findElement(By.id("registeras"));

        Select selectType = new Select(dropdown);
//        selectType.selectByVisibleText("Student");
//        selectType.selectByValue("teacher");
        selectType.selectByIndex(2);

//        //select option without using Select class methods
//        List<WebElement> options = selectType.getOptions();
//        for (WebElement e : options) {
//            if (e.getText().equals("Alumni")) {
//                e.click();
//                break;
//            }
//        }
    }
}

