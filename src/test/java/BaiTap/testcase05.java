package BaiTap;

import Pom.RegisterPage;
import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

/*

Test Steps:

1. Go to http://live.techpanda.org/

2. Click on my account link

3. Click Create an Account link and fill New User information excluding the registered Email ID.

4. Click Register

5. Verify Registration is done. Expected account registration done.

6. Go to TV menu

7. Add product in your wish list - use product - LG LCD

8. Click SHARE WISHLIST

9. In next page enter Email and a message and click SHARE WISHLIST

10.Check wishlist is shared. Expected wishlist shared successfully.

*/
public class testcase05 {
    @Test
    public static void testcase5() {

        //1. Init web-driver session
        WebDriver driver = driverFactory.getChromeDriver();
        RegisterPage registerPage = new RegisterPage(driver);
        try {
            //Step 1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org");
            //Step 2. Click on my account link
            WebElement account = driver.findElement(By.cssSelector("a[class='skip-link skip-account'] span[class='icon']"));
            account.click();
            registerPage.clickMyAccountLink();
            Thread.sleep(1000);
            //Step 3. Click Create an Account link and fill New User information excluding the registered Email ID.
            registerPage.clickCreateAccountLink();
            //Register
            Thread.sleep(1000);
            registerPage.enterFirstName("waudauowdhoa");
            Thread.sleep(200);
            registerPage.enterLastName("kwoakdoawkdoa");
            Thread.sleep(200);
            registerPage.enterEmail("kokokokoakokwo2a@gmail.com");
            Thread.sleep(200);
            registerPage.enterPassword("123456");
            Thread.sleep(200);
            registerPage.enterConfirmPassword("123456");
            Thread.sleep(200);

            //Step 4. Click Register
            registerPage.clickRegisterButton();
            //Step 5. Verify Registration is done. Expected account registration done.
            String message = driver.findElement(By.cssSelector("li[class='success-msg'] ul li span")).getText();
            AssertJUnit.assertEquals(message,"Thank you for registering with Main Website Store.");

            //Step 6.  Go to TV menu
            WebElement tv = driver.findElement(By.cssSelector("body > div:nth-child(1) > div:nth-child(2) > header:nth-child(2) > div:nth-child(1) > div:nth-child(4) > nav:nth-child(1) > ol:nth-child(1) > li:nth-child(2) > a:nth-child(1)"));
            tv.click();

            //Step 7.  Add product in your wish list - use product - LG LCD.
            WebElement addWishList = driver.findElement(By.cssSelector("body > div:nth-child(1) > div:nth-child(2) > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > ul:nth-child(2) > li:nth-child(1) > div:nth-child(2) > div:nth-child(4) > ul:nth-child(2) > li:nth-child(1) > a:nth-child(1)"));
            addWishList.click();

            //Step 8. Click SHARE WISHLIST
            WebElement share = driver.findElement(By.cssSelector("button[title='Share Wishlist'] span span"));
            share.click();

            //Step 9. In next page enter Email and a message and click SHARE WISHLIST
            WebElement emailField = driver.findElement(By.id("email_address"));
            WebElement messageField = driver.findElement(By.id("message"));
            emailField.sendKeys("abc@abc.com");
            messageField.sendKeys("I love you");

            WebElement shareWishList = driver.findElement(By.cssSelector("button[title='Share Wishlist'] span span"));
            shareWishList.click();

            //Step 10. Check wishlist is shared. Expected wishlist shared successfully.
            String share_message = driver.findElement(By.cssSelector("li[class='success-msg'] ul li span")).getText();
            AssertJUnit.assertEquals(share_message,"Your Wishlist has been shared.");
        }catch (Exception e){
            e.printStackTrace();
        }
        //7. Quit browser session
        driver.quit();
    }
}
