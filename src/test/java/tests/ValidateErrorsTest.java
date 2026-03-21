package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pageobjects.CartPage;
import pageobjects.CheckoutPage;
import pageobjects.ConfirmationPage;
import pageobjects.ProductCatalaguePage;

public class ValidateErrorsTest extends BaseTest {
	
	String productName="IPHONE 13 PRO";
	String email="archutce@gmail.com";
	String password="Archvimal$5";
	String country="Spain";
	String confirmationMessage="Thankyou for the order.";

	
	@Test
	public void addToCartAndOrder1() {
		
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

}
