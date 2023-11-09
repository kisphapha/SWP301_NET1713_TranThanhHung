package BaiTap;

import Pom.BackendLogin;
import Pom.CartPage;
import Pom.OrderMenuPage;
import driver.driverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/*

1. Go to http://live.techpanda.org/index.php/backendlogin
2. Login the credentials provided
3. Go to Sales-> Orders menu
4. Input OrderId and FromDate -> ToDate
5. Click Search button
6. Screenshot capture.

*/
public class testcase10 {
    @Test
    public static void testcase10() {

        WebDriver driver = driverFactory.getChromeDriver();
        try {
            // 1. Go to http://live.techpanda.org/index.php/backendlogin
            driver.get("http://live.techpanda.org/index.php/backendlogin");

            // 2. Login the credentials provided
            BackendLogin loginPage = new BackendLogin(driver);
            loginPage.enterUsername("user01");
            loginPage.enterPassword("guru99com");
            loginPage.clickLoginButton();

            // 3. Go to Sales-> Orders menu
            OrderMenuPage o = new OrderMenuPage(driver);
            //o.selectOrdersLink("//*[@id=\"nav\"]/li[1]/ul/li[1]/a/span");
            o.clickOrdersLinkLocator();

            // 4. Input OrderId and FromDate -> ToDate
            o.enterOrderId("100021247");
            o.enterFromDateInputLocator("11/7/2023");
            o.enterToDateInputLocator("11/10/2023");

            // 5. Click Search button
            o.clickSearchButtonLocator();
            Thread.sleep(2000);

            // 6. Screenshot capture.
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshotFile, new File("src/test/resources/image/testcase10.png"));


            Thread.sleep(2000);

        }  catch (Exception e) {
            e.printStackTrace();
        }

        // 11. Quit browser session
        driver.quit();
    }



    public static double parseCurrencyToDouble(String currencyString) {
        // Remove currency symbols, commas, and other non-numeric characters
        String cleanedString = currencyString.replaceAll("[^0-9.]", "");
        // Parse the cleaned string as a double
        return Double.parseDouble(cleanedString);
    }
}


