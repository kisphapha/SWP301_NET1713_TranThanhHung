package BaiTap;

import Pom.LoginPage;
import Pom.RegisterPage;
import driver.driverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.io.File;

/*

Test Steps

1. Go to http://live.techpanda.org/

2. Click on My Account link

3. Login in application using previously created credential

4. Click on 'My Orders'

5. Click on 'View Order'

6. Click on 'Print Order' link

*/
public class testcase07 {
    @Test
    public static void testcase7() {

        //1. Init web-driver session
        WebDriver driver = driverFactory.getChromeDriver();
        RegisterPage registerPage = new RegisterPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        try {
            //Step 1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/index.php/");
            //Step 2. Click on My Account link
            registerPage.clickMyAccountLink();
            //Step 3. Login in application using previously created credential
            loginPage.enterEmail("85769@aa.com");
            loginPage.enterPassword("123456");
            loginPage.clickLoginButton();
            Thread.sleep(1000);


            //Step 4. Click on 'My Orders'
            WebElement myOrder = driver.findElement(By.linkText(("MY ORDERS")));
            myOrder.click();
            Thread.sleep(1000);
            //Step 5. Click on 'View Order'
            WebElement viewOrder = driver.findElement(By.cssSelector("a[href='http://live.techpanda.org/index.php/sales/order/view/order_id/20633/']"));
            viewOrder.click();
            //Step 6. Click on 'Print Order' link
            WebElement print = driver.findElement(By.cssSelector(".link-print"));
            print.click();
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            Thread.sleep(1000);
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshotFile, new File("src/test/resources/image/testcase7.png"));

        }catch (Exception e){
            e.printStackTrace();
        }
        //7. Quit browser session
        driver.quit();
    }
}
