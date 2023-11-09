package BaiTap;

import Pom.CartPage;
import Pom.CheckOutPage;
import Pom.LoginPage;
import Pom.RegisterPage;
import driver.driverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

/*

Test Steps:

1. Go to http://live.techpanda.org/

2. Click on my account link

3. Login in application using previously created credential

4. Click on MY WISHLIST link

5. In next page, Click ADD TO CART link

6. Enter general shipping country, state/province and zip for the shipping cost estimate

7. Click Estimate

8. Verify Shipping cost generated

9. Select Shipping Cost, Update Total

10. Verify shipping cost is added to total

11. Click "Proceed to Checkout"

12a. Enter Billing Information, and click Continue

12b. Enter Shipping Information, and click Continue

13. In Shipping Method, Click Continue

14. In Payment Information select 'Check/Money Order' radio button. Click Continue

15. Click 'PLACE ORDER' button

16. Verify Oder is generated. Note the order number

*/
public class testcase06 {
    @Test
    public static void testcase6() {

        //1. Init web-driver session
        WebDriver driver = driverFactory.getChromeDriver();
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        CartPage cartPage = new CartPage(driver);

        try {
            //1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");

            //2. Click on my account link
            registerPage.clickMyAccountLink();


            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }

            //3. Login in application using previously created credential

            loginPage.enterEmail("85769@aa.com");
            loginPage.enterPassword("123456");
            Thread.sleep(1000);

            loginPage.clickLoginButton();

            //4. Click on MY WISHLIST link
            loginPage.clickMyWishlistLink();

            Thread.sleep(1000);
            //5. In next page, Click ADD TO CART link
            loginPage.clickAddToCartButton();
            //6. Enter general shipping country, state/province and zip for the shipping cost estimate
            cartPage.chooseCountry("United States");
            cartPage.chooseState("Alaska");
            cartPage.enterZipInput("123345");
            //7. Click Estimate
            cartPage.clickEstimate();
            //8. Verify Shipping cost generated
            cartPage.checkShippingCost();
            //9. Select Shipping Cost, Update Total
            cartPage.clickShippingCost();
            cartPage.clickUpdateTotal();
            Thread.sleep(1000);
            //10. Verify shipping cost is added to total
            cartPage.verifyCost();
            //11. Click "Proceed to Checkout"
            cartPage.clickCheckOut();
            //12a. Enter Billing Information, and click Continue
            if (checkOutPage.isAvailable()){
                checkOutPage.chooseNewAddress();
            }
            Thread.sleep(1000);
            checkOutPage.enterFirstName("Ahihihihihi");
            checkOutPage.enterLastName("JOINTHEDARKSIDE");
            checkOutPage.enterAddress("KOKOKOKOKOKOMELOM");
            checkOutPage.enterCity("HCMCITY");
            checkOutPage.chooseState("Alaska");
            checkOutPage.enterZipInput("123456");
            checkOutPage.chooseCountry("United States");
            checkOutPage.enterPhone("1234567890");
            checkOutPage.chooseToThisAddress();
            checkOutPage.clickCountinue();
            Thread.sleep(3000);
            //12b. Enter Shipping Information, and click Continue
            //13. In Shipping Method, Click Continue
            checkOutPage.clickShippingMethodCountinue();
            Thread.sleep(3000);
            checkOutPage.chooseCheckoutMoney();
            Thread.sleep(2000);
            //14. In Payment Information select 'Check/Money Order' radio button. Click Continue
            checkOutPage.clickPaymentContinueButton();
            Thread.sleep(3000);
            //15. Click 'PLACE ORDER' button
            checkOutPage.clickPlaceOrder();
            Thread.sleep(3000);

            //16. Verify Oder is generated. Note the order number
            checkOutPage.checkOrderId();
            checkOutPage.takeScreenShot("testcase06");
            Thread.sleep(1000);

        }catch (Exception e){
            e.printStackTrace();
        }
        //7. Quit browser session
        driver.quit();
    }
}
