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

1. Goto http://live.techpanda.org/

2. Click on �MOBILE� menu

3. In the list of all mobile , read the cost of Sony Xperia mobile (which is $100)

4. Click on Sony Xperia mobile

5. Read the Sony Xperia mobile from detail page.

6. Compare Product value in list and details page should be equal ($100).

*/
public class testcase02 {
    @Test
    public static void testcase1() {

        //1. Init web-driver session
        WebDriver driver = driverFactory.getChromeDriver();
        try {
            //Step 1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/index.php/");

            //Step 2.  Click on �MOBILE� menu
            WebElement mobileBtn = driver.findElement(By.cssSelector("body > div:nth-child(1) > div:nth-child(2) > header:nth-child(2) > div:nth-child(1) > div:nth-child(4) > nav:nth-child(1) > ol:nth-child(1) > li:nth-child(1) > a:nth-child(1)"));
            mobileBtn.click();
            Thread.sleep(1000);

            //Step 3.  In the list of all mobile , read the cost of Sony Xperia mobile (which is $100)
            WebElement price = driver.findElement(By.cssSelector(("span[id='product-price-1'] span[class='price']")));
            String priceStr = price.getText();
            System.out.println(priceStr);
            Thread.sleep(1000);
            //Step 4. Click on Sony Xperia mobile
            WebElement xperia = driver.findElement((By.cssSelector("h2[class='product-name'] a[title='Sony Xperia']")));
            xperia.click();
            Thread.sleep(1000);
            //Step 5: Read the Sony Xperia mobile from detail page.
            String xperia_price = driver.findElement((By.cssSelector("span.price"))).getText();
            Thread.sleep(1000);
            //Step 6 Compare Product value in list and details page should be equal ($100).
            AssertJUnit.assertEquals(xperia_price,priceStr);
        }catch (Exception e){
            e.printStackTrace();
        }
        //7. Quit browser session
        driver.quit();
    }
}
