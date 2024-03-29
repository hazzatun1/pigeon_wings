package com.example.pigeon_wings.page.step_source;


import com.example.pigeon_wings.entity.BillInfo;
import com.example.pigeon_wings.factory.annotation.LazyAutowired;
import com.example.pigeon_wings.factory.annotation.Page;
import com.example.pigeon_wings.page.step_source.sub_source.BillFormFilling;
import com.example.pigeon_wings.repository.BillInfoRepository;
import com.github.javafaker.Faker;
import org.apache.commons.io.input.BOMInputStream;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;


@Page
public class PlaceOrderRegPage extends BillFormFilling {

    @FindBy(xpath="//ul[@class='top-menu notmobile']//a[normalize-space()='Electronics']")
    private WebElement Electronics;
    @FindBy(xpath="(//a[normalize-space()='Cell phones'])[3]")
    private WebElement cell_phone;
    @FindBy(xpath="//a[normalize-space()='Nokia Lumia 1020']")//Nokia Lumia 1020
    private WebElement nokia_lumia;
    @FindBy(id="product_enteredQuantity_20")
    private WebElement setQuantity;
    @FindBy(id="add-to-cart-button-20")
    private WebElement btnCart;
    @FindBy(xpath="//span[@class='cart-label']")
    private WebElement shopping_cart;
    @FindBy(id="termsofservice")
    private WebElement terms_conditions;
    @FindBy(id="checkout")
    private WebElement checkout_btn;
    @FindBy(xpath="//button[normalize-space()='Checkout as Guest']")
    private WebElement check_guest_btn;
    @FindBy(xpath="//h2[normalize-space()='Billing address']")
    private WebElement billingPortionClick;
    @FindBy(name="save")
    private WebElement billingContinueBtn;
    @FindBy(id="shippingoption_1")
    private WebElement shippingNextDayAir;
    @FindBy(xpath="//button[@class='button-1 shipping-method-next-step-button']")
    private WebElement shippingContinueBtn;
    @FindBy(id="paymentmethod_1")
    private WebElement paymentCredit;
    @FindBy(name="save")
    private WebElement paymentContinueBtn;
    @FindBy(xpath="//button[@class='button-1 payment-info-next-step-button']")
    private WebElement paymentInfoContinueBtn;
    @FindBy(xpath="//strong[normalize-space()='Your order has been successfully processed!']")
    private WebElement purchaseConfirmMessage;
    @FindBy(xpath="//button[normalize-space()='Confirm']")
    private WebElement purchaseConfirmBtn;
    @FindBy(xpath="//button[normalize-space()='Continue']")
    private WebElement purchaseEndingContinueBtn;

    @LazyAutowired
    private Faker faker;
    @Autowired
    private BillFormFilling placePage;
    @Autowired
    private BillInfoRepository repository;

    public void clickOnCellPhone(String category, String content) throws InterruptedException {

//       Actions // actions= new Actions(this.driver);
//        actions.moveToElement(Electronics).click(cell_phone).build().perform();
        this.Electronics.click();
        this.cell_phone.click();

    }
    public void clickOnProduct(String product){
this.nokia_lumia.click();
    }
    public void setQuantity(int quantity){
this.setQuantity.sendKeys("2");
    }
    public void addToCart(String cartButton){
this.btnCart.click();

    }
    public void goToShippingPage(){

this.shopping_cart.click();

    }
    public void performCheckout(){
        this.terms_conditions.click();
this.checkout_btn.click();

    }

    public void clickCheckoutAsGuest(){
this.check_guest_btn.click();

    }

    public void inputRequiredDetails(BillInfo u) throws InterruptedException {
        String fName=faker.name().firstName();
        String lName=faker.name().lastName();

        this.setNames(fName, lName);
        String bill_email= "pigeon.wings.elubilu+"+faker.name().firstName()+"@gmail.com";
        this.setBillingEmail(bill_email);
        this.setCompanyName(faker.name().nameWithMiddle());
        this.placePage.setLocation("United States", "Florida", "dhaka", "4/24, mmirpur-12");
        this.placePage.setContact("12345", "0144452221", "11123343");
        billingPortionClick.click();
        billingContinueBtn.click();
    }

    public void chooseShippingMethod(String shipMethod) throws InterruptedException {
        Thread.sleep(3000);
this.shippingNextDayAir.click();

    }
    public void choosePaymentMethod(String payMethod) throws InterruptedException {
        Thread.sleep(3000);
this.shippingContinueBtn.click();

    }
    public void chooseCardMethod(String cardType) throws InterruptedException {
        Thread.sleep(3000);
this.paymentCredit.click();

    }
    public void confirmPurchase() throws InterruptedException {
        Thread.sleep(3000);
this.paymentContinueBtn.click();
this.paymentInfoContinueBtn.click();
this.purchaseConfirmBtn.click();
    }

    public void getConfirmationMsg(String txt){

        this.wait.until((d)->this.purchaseConfirmMessage.isDisplayed());
        assert this.purchaseConfirmMessage.getText().equals(txt);
        this.purchaseConfirmBtn.click();
        this.purchaseEndingContinueBtn.click();

    }
    @Override
    public boolean isAt() {
        return this.wait.until((d)->this.purchaseConfirmMessage.isDisplayed());
    }
}
