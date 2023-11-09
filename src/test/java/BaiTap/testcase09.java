package BaiTap;

import Pom.CartPage;
import driver.driverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.File;

/*

1. Go to http://live.techpanda.org/

2. Go to Mobile and add IPHONE to cart

3. Enter Coupon Code

4. Verify the discount generated

TestData:  Coupon Code: GURU50

Expected result:

1) Price is discounted by 5%

*/
public class testcase09 {
    @Test
    public static void testcase9() {

        //1. Init web-driver session
        WebDriver driver = driverFactory.getChromeDriver();
        CartPage cartPage = new CartPage(driver);
        try {
            //Step 1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/index.php/");
            //Step 2. Click on �MOBILE� menu
            WebElement mobile = driver.findElement(By.cssSelector("body > div:nth-child(1) > div:nth-child(2) > header:nth-child(2) > div:nth-child(1) > div:nth-child(4) > nav:nth-child(1) > ol:nth-child(1) > li:nth-child(1) > a:nth-child(1)"));
            mobile.click();
            Thread.sleep(1000);
            cartPage.addIphoneToCart();
            //Step 3. Enter Coupon Code
            double oldGrand = Double.parseDouble(cartPage.getInitGrandTotal());
            cartPage.enterDiscountCode("GURU50");
            cartPage.clickApplyDiscount();
            //Step 4. Verify the discount generated
            System.out.println(cartPage.discountGenerated());
            AssertJUnit.assertFalse(cartPage.discountGenerated().isEmpty());
            cartPage.verifyDiscount(oldGrand);

        }catch (Exception e){
            e.printStackTrace();
        }
        //7. Quit browser session
        driver.quit();
    }
}
