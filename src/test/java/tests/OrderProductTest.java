package tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pageobjects.CartPage;
import pageobjects.CheckoutPage;
import pageobjects.ConfirmationPage;
import pageobjects.OrdersPage;
import pageobjects.ProductCatalaguePage;

public class OrderProductTest extends BaseTest {
	
	String productName="ZARA COAT 3";
	String email="archutce@gmail.com";
	String password="Archvimal$5";
	String country="India";
	String confirmationMessage="Thankyou for the order.";

	
	@Test(groups= {"Smoke"})
	public void addToCartAndOrder() {
		
		ProductCatalaguePage productCatalaguePage=landingPage.login(email,password);
		productCatalaguePage.getProductList();
		productCatalaguePage.addToCart(productName);
		
		CartPage cartPage=productCatalaguePage.goToCart();
		boolean match=cartPage.verifyCartProducts(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage=cartPage.goToCheckOut();
		
		checkoutPage.selectCountry(country);
		ConfirmationPage confirmationPage = checkoutPage.goToConfirmation();
		
		String actualMessage=confirmationPage.getConfirmationMessage();
		Assert.assertTrue(actualMessage.equalsIgnoreCase(confirmationMessage));
	}

	@Test(dependsOnMethods= {"addToCartAndOrder"})
	public void verifyOrder() {
		ProductCatalaguePage productCatalaguePage=landingPage.login(email,password);
		productCatalaguePage.getProductList();
		productCatalaguePage.addToCart(productName);
		OrdersPage ordersPage= productCatalaguePage.goToOrders();
		String orderedProduct=ordersPage.getOrderedText();
		Assert.assertEquals(orderedProduct, productName);
	}
	

}
