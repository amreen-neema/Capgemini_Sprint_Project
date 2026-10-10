package SwaglabProduct;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import BaseClass.BaseClass;
import PomPages.LoginPage;
import PomPages.ProductPage;
import Utilities.ExcelUtility;

public class Product_testcase extends BaseClass  {
	 // TS1 - Verify Product Page
	@Test(priority = 1, groups = {"ST", "FT", "IT", "YT"})
	public void validProduct() throws Exception {

		Reporter.log("Test Case Started - Product TS1",true);

        // Create Login Page object
        LoginPage loginPage = new LoginPage(driver);

        // Read login data from Excel
        String username = ExcelUtility.getData("Login", 1, 1);
        String password = ExcelUtility.getData("Login", 1, 2);

        Reporter.log("Username = " + username,true);
        Reporter.log("Password = " + password,true);

        // Login
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        // Create Product Page object
        ProductPage productPage = new ProductPage(driver);

        // Verify Product page
        Assert.assertTrue(productPage.isProductPageDisplayed());

        Reporter.log("Product Page displayed successfully",true);
        Reporter.log("Test Case Completed - Product TS1",true);
    }


    // TS2 - Add Backpack to Cart
	@Test(priority = 2, groups = {"ST", "FT", "IT", "YT"})
	public void productAvailability() throws Exception {

		Reporter.log("Test Case Started - Product TS2",true);

        // Create Login Page object
        LoginPage loginPage = new LoginPage(driver);

        // Read login data from Excel
        String username = ExcelUtility.getData("Login", 1, 1);
        String password = ExcelUtility.getData("Login", 1, 2);

        Reporter.log("Username = " + username,true);
        Reporter.log("Password = " + password,true);

        // Login
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        // Create Product Page object
        ProductPage productPage = new ProductPage(driver);

        // Verify Product page before adding product
        Assert.assertTrue(productPage.isProductPageDisplayed());

        // Add Backpack
        productPage.addBackpackToCart();

        // Verify product added
        Assert.assertTrue(productPage.isBackpackAddedToCart());

        Reporter.log("Backpack added to cart successfully",true);
        Reporter.log("Test Case Completed - Product TS2",true);
    }

}
