package SwagLabCheckOut;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import BaseClass.BaseClass;
import PomPages.AddToCartPage;
import PomPages.CheckOutPage;
import PomPages.LoginPage;
import PomPages.ProductPage;
import Utilities.ExcelUtility;

public class CheckOut_testcase extends BaseClass {

	 // =====================================================
    // TC1 - VALID CHECKOUT
    // =====================================================

    @Test(priority = 1, groups = {"ST", "FT", "IT", "YT"})
    public void validCheckoutDetails() throws Exception {

        Reporter.log("====================================",true);
        Reporter.log("TC1 - Valid Checkout Started",true);
        Reporter.log("====================================",true);

        // LOGIN
        LoginPage login = new LoginPage(driver);

        String username = ExcelUtility.getData("Login", 1, 1);
        String password = ExcelUtility.getData("Login", 1, 2);

        Reporter.log("Username = " + username,true);
        Reporter.log("Password = " + password,true);

        login.enterUsername(username);
        login.enterPassword(password);
        login.clickLogin();

        Thread.sleep(2000);

        Reporter.log("URL after login = " + driver.getCurrentUrl(),true);

        // Check whether login was successful
        Assert.assertTrue(
                driver.getCurrentUrl().contains("inventory"),
                "Login failed - Inventory page was not opened"
        );

        // PRODUCT
        ProductPage product = new ProductPage(driver);

        product.addBackpackToCart();

        Thread.sleep(1000);

        product.clickCart();

        Thread.sleep(1000);

        // CART
        AddToCartPage cart = new AddToCartPage(driver);

        cart.clickCheckout();

        Thread.sleep(1000);

        // CHECKOUT
        CheckOutPage checkout = new CheckOutPage(driver);

        String firstName = ExcelUtility.getData("checkout", 1, 0);
        String lastName = ExcelUtility.getData("checkout", 1, 1);
        String pincode = ExcelUtility.getData("checkout", 1, 2);

        checkout.enterFirstName(firstName);
        checkout.enterLastName(lastName);
        checkout.enterPostalCode(pincode);

        checkout.clickContinue();

        Thread.sleep(1000);

        Reporter.log("TC1 - Checkout details entered successfully",true);

        checkout.clickFinish();

        Thread.sleep(1000);

        Reporter.log("TC1 - Valid Checkout Passed",true);
    }


    // =====================================================
    // TC2 - CHECKOUT WITHOUT FIRST NAME
    // =====================================================

    @Test(priority = 2, groups = {"FT", "YT", "NT"})
    public void checkoutWithoutFirstName() throws Exception {

    	Reporter.log("====================================",true);
    	Reporter.log("TC2 - Empty First Name Started",true);
    	Reporter.log("====================================",true);

        // LOGIN
        LoginPage login = new LoginPage(driver);

        String username = ExcelUtility.getData("Login", 1, 1);
        String password = ExcelUtility.getData("Login", 1, 2);

        login.enterUsername(username);
        login.enterPassword(password);
        login.clickLogin();

        Thread.sleep(2000);

        Reporter.log("URL after login = " + driver.getCurrentUrl(),true);

        // Check login
        Assert.assertTrue(
                driver.getCurrentUrl().contains("inventory"),
                "Login failed - Inventory page was not opened"
        );

        // PRODUCT
        ProductPage product = new ProductPage(driver);

        product.addBackpackToCart();

        Thread.sleep(1000);

        product.clickCart();

        Thread.sleep(1000);

        // CART
        AddToCartPage cart = new AddToCartPage(driver);

        cart.clickCheckout();

        Thread.sleep(1000);

        // CHECKOUT
        CheckOutPage checkout = new CheckOutPage(driver);

        String lastName = ExcelUtility.getData("checkout", 1, 1);
        String pincode = ExcelUtility.getData("checkout", 1, 2);

        // First name is intentionally NOT entered
        checkout.enterLastName(lastName);
        checkout.enterPostalCode(pincode);

        checkout.clickContinue();

        Thread.sleep(1000);

        Reporter.log("TC2 - Empty First Name validation completed",true);
    }


    // =====================================================
    // TC3 - CHECKOUT WITHOUT DETAILS
    // =====================================================

    @Test(priority = 3, groups = {"FT","YT","NT"})
    public void checkoutWithoutDetails() throws Exception {

    	Reporter.log("====================================",true);
    	Reporter.log("TC3 - Empty Checkout Details Started",true);
    	Reporter.log("====================================",true);

        // LOGIN
        LoginPage login = new LoginPage(driver);

        String username = ExcelUtility.getData("Login", 1, 1);
        String password = ExcelUtility.getData("Login", 1, 2);

        login.enterUsername(username);
        login.enterPassword(password);
        login.clickLogin();

        Thread.sleep(2000);

        Reporter.log("URL after login = " + driver.getCurrentUrl(),true);

        // Check login
        Assert.assertTrue(
                driver.getCurrentUrl().contains("inventory"),
                "Login failed - Inventory page was not opened"
        );

        // PRODUCT
        ProductPage product = new ProductPage(driver);

        product.addBackpackToCart();

        Thread.sleep(1000);

        product.clickCart();

        Thread.sleep(1000);

        // CART
        AddToCartPage cart = new AddToCartPage(driver);

        cart.clickCheckout();

        Thread.sleep(1000);

        // CHECKOUT
        CheckOutPage checkout = new CheckOutPage(driver);

        // Leave all fields empty
        checkout.clickContinue();

        Thread.sleep(1000);

        Reporter.log("TC3 - Empty Checkout validation completed",true);
    }
}
