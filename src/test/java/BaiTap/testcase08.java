package BaiTap;
import Pom.CartPage;
import Pom.CheckOutPage;
import Pom.LoginPage;
import Pom.RegisterPage;
import driver.driverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.io.File;
public class testcase08 {
    @Test
    public static void testcase08() {
        // 0. Init web-driver session
        WebDriver driver = driverFactory.getChromeDriver();
        CartPage cartPage = new CartPage(driver);

        try {

            // 1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");

            // 2. Click on My Account link
            RegisterPage registerPage = new RegisterPage(driver);
            registerPage.clickMyAccountLink();

            // 3. Login in application using previously created credential
            LoginPage loginPage = new LoginPage(driver);
            loginPage.enterEmail("85769@aa.com");
            loginPage.enterPassword("123456");
            loginPage.clickLoginButton();

            // 4. Click on 'REORDER' link , change QTY & click Update
            By reorderLinkLocator = By.xpath("//tr[@class='first odd']/td[@class='a-center view last']//a[@class='link-reorder']");
            driver.findElement(reorderLinkLocator).click();
            Thread.sleep(1000);
            WebElement qty = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/table[1]/tbody[1]/tr[1]/td[4]/input[1]"));
            double oldGrand = Double.parseDouble(cartPage.getInitGrandTotal());
            qty.sendKeys("2");
            WebElement update = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/table[1]/tbody[1]/tr[1]/td[4]/button[1]"));
            update.click();
            // 5. Verify Grand Total is changed
            double newGrand = Double.parseDouble(cartPage.getInitGrandTotal());

            AssertJUnit.assertNotSame(oldGrand, newGrand);

            // 6. Complete Billing & Shipping Information
            cartPage.clickCheckOut();

            CheckOutPage checkOutPage = new CheckOutPage(driver);
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

            checkOutPage.clickShippingMethodCountinue();
            Thread.sleep(3000);
            checkOutPage.chooseCheckoutMoney();
            Thread.sleep(2000);
            checkOutPage.clickPaymentContinueButton();
            Thread.sleep(3000);
            checkOutPage.clickPlaceOrder();
            Thread.sleep(3000);


            checkOutPage.checkOrderId();
            checkOutPage.takeScreenShot("testcase08");
            Thread.sleep(2000);
        }catch (Exception e){
            e.printStackTrace();
        }
        //7. Quit browser session
        driver.quit();
    }
}