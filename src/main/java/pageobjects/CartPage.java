package pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.WaitUtils;

public class CartPage extends WaitUtils{
	
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartSection;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutButton;
	
	public List<WebElement> cartProducts() {
		return cartSection;
	}
	
	public boolean verifyCartProducts(String productName) {
		return cartSection.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
	}
	
	public CheckoutPage goToCheckOut() {
		checkoutButton.click();
		return new CheckoutPage(driver);
	}
	
	
}
