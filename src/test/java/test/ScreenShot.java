package test;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class ScreenShot extends BaseClass {

    public static void main(String[] args) throws IOException {

        initialize();

//        System.setProperty("webdriver.chrome.driver", "D:\\webDrivers\\chromedriver-win64\\chromedriver.exe");

        driver.get("https://www.w3schools.com/howto/howto_js_rangeslider.asp");

        TakesScreenshot screenshot = (TakesScreenshot) driver;

        //Full page sc
        File src = screenshot.getScreenshotAs(OutputType.FILE);
        File target = new File(".\\screenshots\\homepage.png");
        FileUtils.copyFile(src, target);


        //SS of particular web element
//        driver.get("https://www.nopcommerce.com/en");
//        WebElement element = driver.findElement(By.xpath("//div[@class='master-wrapper-page']"));
//        File secScreenshot = element.getScreenshotAs(OutputType.FILE);
//        File secScreen = new File(".\\screenshots\\secsec.png");
//        FileUtils.copyFile(secScreenshot, secScreen);

    }
}
