package test.table;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class HandleTable {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "D:\\webDrivers\\chromedriver-win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver(new ChromeOptions().addArguments("--remote-allow-origins=*"));
        driver.get("https://www.selenium.dev/ecosystem/");

        // size of table (Number of rows int the table is size of table.)
        int size = driver.findElements(By.xpath("//div[4]//div[1]//table[1]/tbody/tr")).size();
        System.out.println("-->Number of rows in the tables: " + size);

        int columnsSize = driver.findElements(By.xpath("//div[4]//div[1]//table[1]/thead/tr/th")).size();
        System.out.println("-->No. of columns in the table: " + columnsSize);

        //Retrieve specific row/column data
        String text = driver.findElement(By.xpath("//div[4]//div[1]//table[1]//tbody/tr[1]/td[2]")).getText();
        System.out.println("-->Specific row/column data: " + text);

        //get all the of the table.
        for (int r = 1; r <size; r++) {
            for (int c = 1; c <columnsSize; c++) {
                String data = driver.findElement(By.xpath("//div[4]//div[1]//table[1]//tbody/tr[" + r + "]/td[" + c + "]")).getText();
                System.out.print("-->" + data + "    ");
            }
            System.out.println();
        }
        driver.quit();
    }

}
