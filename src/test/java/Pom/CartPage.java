package Pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.AssertJUnit;

public class CartPage {
    WebDriver driver;

     By stateDropdown = By.cssSelector("#region_id");
    By zipInput = By.cssSelector("#postcode");
    By countryDropdown = By.cssSelector("#country");
    By estimateButton = By.cssSelector("button[title='Estimate'] span span");

    By shippingRadio = By.cssSelector("#s_method_flatrate_flatrate");
    By updateTotal = By.cssSelector("button[title='Update Total']");

    By grandTotal = By.cssSelector("strong span[class='price']");

    By checkOutButton = By.cssSelector("li[class='method-checkout-cart-methods-onepage-bottom'] button[title='Proceed to Checkout'] span span");


    By shipping = By.xpath("//td[@class='a-right']//span[@class='price'][normalize-space()='$40.00']");

    By flatRate = By.cssSelector("label[for='s_method_flatrate_flatrate'] span[class='price']");
    By defaultGrand = By.cssSelector("body > div:nth-child(1) > div:nth-child(2) > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(4) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(3) > tr:nth-child(1) > td:nth-child(2) > span:nth-child(1)");

    By applyDiscount = By.cssSelector("button[title='Apply'] span span");
    By discount = By.id("coupon_code");
    By iphoneAddToCart = By.cssSelector("body > div:nth-child(1) > div:nth-child(2) > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > ul:nth-child(2) > li:nth-child(1) > div:nth-child(2) > div:nth-child(4) > button:nth-child(1) > span:nth-child(1) > span:nth-child(1)");

    By discountGenerated = By.cssSelector("tbody tr:nth-child(2) td:nth-child(2) span:nth-child(1)");
    public CartPage(WebDriver driver) {
        this.driver = driver;
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


    public void clickEstimate() {
        WebElement element = driver.findElement(estimateButton);
        element.click();
    }
    public void clickUpdateTotal() {
        WebElement element = driver.findElement(updateTotal);
        element.click();
    }
    public void clickCheckOut() {
        WebElement element = driver.findElement(checkOutButton);
        element.click();
    }

    public void clickShippingCost() {
        WebElement element = driver.findElement(shippingRadio);
        element.click();
    }

    public void checkShippingCost() {
        WebElement element = driver.findElement(flatRate);
        System.out.println(element.getText());;
    }

    public String getInitGrandTotal(){
        return  driver.findElement(grandTotal).getText().replaceAll("[$,]", "");

    }

    public void verifyCost() {
        String oldGrand = driver.findElement(defaultGrand).getText().replaceAll("[$,]", "");
        String shippingCost = driver.findElement(flatRate).getText().replaceAll("[$,]", "");
        double number = Double.parseDouble(shippingCost) + Double.parseDouble(oldGrand);
        String element = driver.findElement(grandTotal).getText().replaceAll("[$,]", "");
        double result = Double.parseDouble(element);
        AssertJUnit.assertEquals(number,result);

    }

    public void addIphoneToCart(){
        WebElement btn = driver.findElement(iphoneAddToCart);
        btn.click();
    }

    public void enterDiscountCode(String key){
        WebElement discountCode = driver.findElement(discount);
        discountCode.sendKeys(key);
    }

    public void clickApplyDiscount(){
        WebElement apply = driver.findElement(applyDiscount);
        apply.click();
    }

    public String discountGenerated(){
        WebElement discount = driver.findElement(discountGenerated);
        String discountStr = discount.getText().replaceAll("[-$,]", "");
        return (discountStr);
    }

    public void verifyDiscount(double oldGrand){
        double expected = oldGrand - Double.parseDouble(discountGenerated());
        double actual = Double.parseDouble(getInitGrandTotal());
        AssertJUnit.assertEquals(expected,actual);
    }
}
