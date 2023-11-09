package Pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    WebDriver driver;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    By loginButton = By.xpath("//span[contains(text(),'Login')]");
    By myWishlistLink = By.linkText("MY WISHLIST");
    By emailInputLocator = By.id("email");
    By passwordInputLocator = By.id("pass");
    By addToCartButton = By.xpath("//span[contains(text(),'Add to Cart')]");

    public void enterEmail(String email) {
        WebElement emailElement = driver.findElement(emailInputLocator);
        emailElement.clear();
        emailElement.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement passwordElement = driver.findElement(passwordInputLocator);
        passwordElement.clear();
        passwordElement.sendKeys(password);
    }
    public void clickMyWishlistLink() {
        driver.findElement(myWishlistLink).click();
    }

    public void clickAddToCartButton() {
        driver.findElement(addToCartButton).click();
    }
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

}
