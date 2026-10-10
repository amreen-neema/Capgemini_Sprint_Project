package PomPages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckOutPage {
	WebDriver driver;
    WebDriverWait wait;

    public CheckOutPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    By firstName = By.id("first-name");
    By lastName = By.id("last-name");
    By postalCode = By.id("postal-code");

    By continueButton = By.id("continue");
    By finishButton = By.id("finish");

    By checkoutContainer = By.className("checkout_info");

    public void enterFirstName(String value) {

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(firstName)
        ).sendKeys(value);
    }

    public void enterLastName(String value) {

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(lastName)
        ).sendKeys(value);
    }

    public void enterPostalCode(String value) {

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(postalCode)
        ).sendKeys(value);
    }

    public void clickContinue() {

        wait.until(
                ExpectedConditions.elementToBeClickable(continueButton)
        ).click();
    }

    public void clickFinish() {

        wait.until(
                ExpectedConditions.elementToBeClickable(finishButton)
        ).click();
    }

    public boolean isCheckoutPageDisplayed() {

        try {
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(firstName)
            ).isDisplayed();

        } catch (Exception e) {
            return false;
        }
    }

    public boolean isOverviewPageDisplayed() {

        try {
            wait.until(
                    ExpectedConditions.urlContains("checkout-step-two.html")
            );

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean isOrderCompleted() {

        try {
            wait.until(
                    ExpectedConditions.urlContains("checkout-complete.html")
            );

            return true;

        } catch (Exception e) {
            return false;
        }
    }
	}