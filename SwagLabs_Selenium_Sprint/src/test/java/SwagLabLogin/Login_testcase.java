package SwagLabLogin;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import BaseClass.BaseClass;
import PomPages.LoginPage;
import PomPages.ProductPage;
import Utilities.ExcelUtility;

public class Login_testcase extends BaseClass {
	// =====================================================
    // TS1 - Valid Login
    // =====================================================

	@Test(priority = 1, groups = {"ST", "FT", "IT", "YT"})
	public void validLogin() throws Exception {

		Reporter.log("=================================",true);
		Reporter.log("Test Case Started - TS1",true);
		Reporter.log("=================================",true);

        LoginPage loginPage = new LoginPage(driver);

        // Read TS1 data from Excel
        // Column 0 = TestCase
        // Column 1 = Username
        // Column 2 = Password
        // Column 3 = Expected

        String username = ExcelUtility.getData("Login", 1, 1);
        String password = ExcelUtility.getData("Login", 1, 2);
        String expected = ExcelUtility.getData("Login", 1, 3);

        Reporter.log("Username = " + username,true);
        Reporter.log("Password = " + password,true);
        Reporter.log("Expected = " + expected,true);

        // Enter login details
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);

        // Click Login
        loginPage.clickLogin();

        Thread.sleep(2000);

        Reporter.log("URL after login = " + driver.getCurrentUrl(),true);

        // Validate login
        Assert.assertTrue(
                loginPage.isLoginSuccessful(),
                "TS1 Login failed"
        );

        Reporter.log("TS1 - Valid Login Passed",true);
        Reporter.log("Test Case Completed - TS1",true);
    }


    // =====================================================
    // TS2 - Invalid Login
    // =====================================================

	@Test(priority = 2, groups = {"FT", "YT", "NT"})
	public void invalidLogin() throws Exception {

		Reporter.log("=================================",true);
		Reporter.log("Test Case Started - TS2",true);
		Reporter.log("=================================",true);

        LoginPage loginPage = new LoginPage(driver);

        // Read TS2 data from Excel
        String username = ExcelUtility.getData("Login", 2, 1);
        String password = ExcelUtility.getData("Login", 2, 2);
        String expected = ExcelUtility.getData("Login", 2, 3);

        Reporter.log("Username = " + username,true);
        Reporter.log("Password = " + password,true);
        Reporter.log("Expected = " + expected,true);

        // Enter login details
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);

        // Click Login
        loginPage.clickLogin();

        Thread.sleep(2000);

        Reporter.log("URL after login = " + driver.getCurrentUrl(),true);

        // Validate invalid login
        Assert.assertFalse(
                loginPage.isLoginSuccessful(),
                "TS2 should not login successfully"
        );
        Reporter.log("TS2 - Invalid Login Passed",true);
        Reporter.log("Test Case Completed - TS2",true);
    }
}
