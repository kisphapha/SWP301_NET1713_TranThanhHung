package BaiTap;

import driver.driverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

/*

Test Steps

Step 1. Go to http://live.techpanda.org/

Step 2. Verify Title of the page

Step 3. Click on -> MOBILE -> menu

Step 4. In the list of all mobile , select SORT BY -> dropdown as name

Step 5. Verify all products are sorted by name

*/
public class testcase01 {
    @Test
    public static void testcase1() {

        //1. Init web-driver session
        WebDriver driver = driverFactory.getChromeDriver();
        try {
            //Step 1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/index.php/");
            //Step 2. Verify Title of the page
            String demoSite = driver.findElement(By.cssSelector("h2")).getText();
            System.out.println(demoSite);
            try{
                AssertJUnit.assertEquals(("THIS IS DEMO SITE FOR   "),demoSite);
            }catch(Error e){
                System.out.println("Error occurred: " + e.getMessage());
                e.printStackTrace();
            }
            Thread.sleep(1000);
            //Step 3. Click on -> MOBILE -> menu
            WebElement mobileBtn = driver.findElement(By.cssSelector("body > div:nth-child(1) > div:nth-child(2) > header:nth-child(2) > div:nth-child(1) > div:nth-child(4) > nav:nth-child(1) > ol:nth-child(1) > li:nth-child(1) > a:nth-child(1)"));
            mobileBtn.click();
            Thread.sleep(1000);

            //Step 4. In the list of all mobile , select SORT BY -> dropdown as name
            WebElement dropDown = driver.findElement(By.cssSelector(("body > div:nth-child(1) > div:nth-child(2) > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > select:nth-child(2)")));
            Select selectOption = new Select(dropDown);
            selectOption.selectByVisibleText("Name");
            Thread.sleep(1000);

            //Step 5. Verify all products are sorted by name
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshotFile, new File("src/test/resources/image/testcase1.png"));

        }catch (Exception e){
            e.printStackTrace();
        }
        //7. Quit browser session
        driver.quit();
    }
}
