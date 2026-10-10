package PomPages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {
	 WebDriver driver;
	    WebDriverWait wait;

	    // Constructor
	    public ProductPage(WebDriver driver) {

	        this.driver = driver;
	        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    }


	    // =========================================================
	    // LOCATORS
	    // =========================================================

	    // Sauce Labs Backpack
	    By backpack =
	            By.id("add-to-cart-sauce-labs-backpack");

	    // Remove Backpack button
	    By removeBackpack =
	            By.id("remove-sauce-labs-backpack");

	    // Shopping Cart
	    By cart =
	            By.className("shopping_cart_link");


	    // =========================================================
	    // VERIFY PRODUCT PAGE
	    // =========================================================

	    public boolean isProductPageDisplayed() {

	        return driver.getCurrentUrl().contains("inventory.html");
	    }


	    // =========================================================
	    // ADD BACKPACK TO CART
	    // =========================================================

	    public void addBackpackToCart() {

	        wait.until(
	                ExpectedConditions.elementToBeClickable(backpack)
	        ).click();

	        System.out.println("Backpack added to cart");
	    }


	    // =========================================================
	    // VERIFY BACKPACK ADDED
	    // =========================================================

	    public boolean isBackpackAddedToCart() {

	        try {

	            wait.until(
	                    ExpectedConditions.visibilityOfElementLocated(removeBackpack)
	            );

	            return true;

	        } catch (Exception e) {

	            return false;
	        }
	    }


	    // =========================================================
	    // OPEN CART
	    // =========================================================

	    public void clickCart() {

	        wait.until(
	                ExpectedConditions.elementToBeClickable(cart)
	        ).click();

	        System.out.println("Cart opened successfully");
	    }
	}