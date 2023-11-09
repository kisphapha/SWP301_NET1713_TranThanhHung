package Pom;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;

public class CheckOutPage {
    WebDriver driver;

    By firstNameInput = By.id("billing:firstname");
    By lastNameInput = By.id("billing:lastname");
    By addressInput = By.id("billing:street1");
    By cityInput = By.id("billing:city");
    By stateDropdown = By.id("billing:region_id");
    By countryDropdown = By.id("billing:country_id");
    By telephoneInput = By.id("billing:telephone");

    By zipInput = By.id("billing:postcode");
    By toThisAddressRadio = By.cssSelector("label[for='billing:use_for_shipping_yes']");
    By continueButton = By.cssSelector("button[onclick='billing.save()']");

    By shippingMethodContinueButton = By.cssSelector("button[onclick='shippingMethod.save()']");

    By moneyRadio = By.cssSelector("#p_method_checkmo");

    By paymentContinueButton = By.cssSelector("button[onclick='payment.save()']");

    By placeOrderButton = By.cssSelector("button[title='Place Order']");

    By availableDropDown = By.cssSelector("#billing-address-select");

    By orderIdLocator = By.cssSelector("body > div:nth-child(1) > div:nth-child(2) > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > p:nth-child(3)");

    public CheckOutPage(WebDriver driver) {
        this.driver = driver;
    }
    public void enterFirstName(String firstName) {
        WebElement element = driver.findElement(firstNameInput);
        element.clear();
        element.sendKeys(firstName);
    }
    public void enterLastName(String lastName) {
        WebElement element = driver.findElement(lastNameInput);
        element.clear();
        element.sendKeys(lastName);
    }
    public void enterAddress(String address) {
        WebElement element = driver.findElement(addressInput);
        element.clear();
        element.sendKeys(address);
    }
    public void enterCity(String city) {
        WebElement element = driver.findElement(cityInput);
        element.clear();
        element.sendKeys(city);
    }
    public void chooseState(String state) {
        WebElement element = driver.findElement(stateDropdown);
        Select selectOption = new Select(element);
        selectOption.selectByVisibleText(state);
    }
    public void chooseCountry(String country) {
        WebElement element = driver.findElement(countryDropdown);
        Select selectOption = new Select(element);
        selectOption.selectByVisibleText(country);
    }

    public void enterZipInput(String zip) {
        WebElement element = driver.findElement(zipInput);
        element.clear();
        element.sendKeys(zip);
    }

    public void enterPhone(String phone) {
        WebElement element = driver.findElement(telephoneInput);
        element.clear();
        element.sendKeys(phone);
    }
    public void chooseToThisAddress() {
        WebElement element = driver.findElement(toThisAddressRadio);
       element.click();
    }
    public void clickCountinue() {
        WebElement element = driver.findElement(continueButton);
        element.click();
    }
    public void clickShippingMethodCountinue() {
        WebElement element = driver.findElement(shippingMethodContinueButton);
        element.click();
    }
    public void chooseCheckoutMoney() {
        WebElement element = driver.findElement(moneyRadio);
        element.click();
    }

    public void clickPaymentContinueButton() {
        WebElement element = driver.findElement(paymentContinueButton);
        element.click();
    }

    public void clickPlaceOrder() {
        WebElement element = driver.findElement(placeOrderButton);
        element.click();
    }

    public void checkOrderId() {
        WebElement element = driver.findElement(orderIdLocator);
        System.out.println(element.getText());;
    }
    public boolean isAvailable(){
        WebElement element = driver.findElement(availableDropDown);
        return element.isDisplayed();
    }

    public void chooseNewAddress() {
        WebElement element = driver.findElement(availableDropDown);
        Select selectOption = new Select(element);
        selectOption.selectByVisibleText("New Address");
    }
    public void takeScreenShot(String filename) throws IOException {
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotFile, new File("src/test/resources/image/"+filename+".png"));
    }
}
