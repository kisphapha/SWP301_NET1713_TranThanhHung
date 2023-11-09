package BaiTap;

import driver.driverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.io.File;

/*

Test Steps:

Test Steps:

1. Go to http://live.techpanda.org/

2. Click on �MOBILE� menu

3. In mobile products list , click on �Add To Compare� for 2 mobiles (Sony Xperia & Iphone)

4. Click on �COMPARE� button. A popup window opens

5. Verify the pop-up window and check that the products are reflected in it

Heading "COMPARE PRODUCTS" with selected products in it.

6. Close the Popup Windows

*/
public class testcase04 {
    @Test
    public static void testcase4() {

        //1. Init web-driver session
        WebDriver driver = driverFactory.getChromeDriver();
        try {
            //Step 1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org");
            //Step 2. Click on �MOBILE� menu
            WebElement mobile = driver.findElement(By.cssSelector("body > div:nth-child(1) > div:nth-child(2) > header:nth-child(2) > div:nth-child(1) > div:nth-child(4) > nav:nth-child(1) > ol:nth-child(1) > li:nth-child(1) > a:nth-child(1)"));
            mobile.click();
            Thread.sleep(1000);
            //Step 3. In mobile products list , click on �Add To Compare� for 2 mobiles (Sony Xperia & Iphone)
            WebElement compare = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[3]/ul[1]/li[3]/div[1]/div[3]/ul[1]/li[2]/a[1]"));
            compare.click();
            Thread.sleep(500);
            WebElement compare2 = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[3]/ul[1]/li[1]/div[1]/div[3]/ul[1]/li[2]/a[1]"));
            compare2.click();
            Thread.sleep(500);

            //Step 4. Click on �COMPARE� button. A popup window opens
            WebElement compareBtn = driver.findElement(By.cssSelector("button[title='Compare'] span span"));
            compareBtn.click();
            Thread.sleep(1000);

            //Step 5. Verify the pop-up window and check that the products are reflected in it
            // switching to new window
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            String heading = driver.findElement(By.cssSelector("div[class='page-title title-buttons'] h1")).getText();
            String sony = driver.findElement(By.cssSelector("h2[class='product-name'] a[title='Sony Xperia']")).getText();
            String iphone = driver.findElement(By.cssSelector("h2[class='product-name'] a[title='IPhone']")).getText();
            AssertJUnit.assertEquals(heading,"COMPARE PRODUCTS");
            AssertJUnit.assertEquals(sony,"SONY XPERIA");
            AssertJUnit.assertEquals(iphone,"IPHONE");
            //Take screenshot for extra reliability
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshotFile, new File("src/test/resources/image/testcase4.png"));
            Thread.sleep(1000);
            //Step 6.  Close the Popup Windows
            driver.quit();

        }catch (Exception e){
            e.printStackTrace();
        }
        //7. Quit browser session
        driver.quit();
    }
}
