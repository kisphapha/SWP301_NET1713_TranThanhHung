package BaiTap;

import driver.driverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.io.File;

/*

Test Steps:

1. Go to http://live.techpanda.org/

2. Click on �MOBILE� menu

3. In the list of all mobile , click on �ADD TO CART� for Sony Xperia mobile

4. Change �QTY� value to 1000 and click �UPDATE� button. Expected that an error is displayed

"The requested quantity for "Sony Xperia" is not available.

5. Verify the error message

6. Then click on �EMPTY CART� link in the footer of list of all mobiles. A message "SHOPPING CART IS EMPTY" is shown.

7. Verify cart is empty

*/
public class testcase03 {
    @Test
    public static void testcase3() {

        //1. Init web-driver session
        WebDriver driver = driverFactory.getChromeDriver();
        try {
            //Step 1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org");
            //Step 2. Click on �MOBILE� menu
            WebElement mobile = driver.findElement(By.cssSelector("body > div:nth-child(1) > div:nth-child(2) > header:nth-child(2) > div:nth-child(1) > div:nth-child(4) > nav:nth-child(1) > ol:nth-child(1) > li:nth-child(1) > a:nth-child(1)"));
            mobile.click();
            Thread.sleep(1000);
            //Step 3. In the list of all mobile , click on �ADD TO CART� for Sony Xperia mobile
            WebElement addToCart = driver.findElement(By.cssSelector("body > div:nth-child(1) > div:nth-child(2) > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > ul:nth-child(2) > li:nth-child(2) > div:nth-child(2) > div:nth-child(4) > button:nth-child(1)"));
            addToCart.click();
            Thread.sleep(1000);

            //Step 4. Change �QTY� value to 1000 and click �UPDATE� button. Expected that an error is displayed
            //"The requested quantity for "Sony Xperia" is not available.
            WebElement qty = driver.findElement(By.cssSelector(("input[title='Qty']")));
            qty.clear();
            qty.sendKeys("1000");
            WebElement updateBtn = driver.findElement(By.cssSelector("button[title='Update'] span span"));
            updateBtn.click();
            String error = driver.findElement(By.cssSelector("li[class='error-msg'] ul li span")).getText();
            Thread.sleep(1000);

            //Step 5. Verify the error message
            AssertJUnit.assertEquals(error,"The requested quantity for \"Sony Xperia\" is not available.");

            //Step 6.  Then click on �EMPTY CART� link in the footer of list of all mobiles. A message "SHOPPING CART IS EMPTY" is shown.
            WebElement emptyCart = driver.findElement(By.cssSelector("button[id='empty_cart_button'] span span"));
            emptyCart.click();

            //Step 7. Verify cart is empty
            String info = driver.findElement(By.cssSelector("div[class='page-title'] h1")).getText();
            AssertJUnit.assertEquals(error,"SHOPPING CART IS EMPTY");



        }catch (Exception e){
            e.printStackTrace();
        }
        //7. Quit browser session
        driver.quit();
    }
}
