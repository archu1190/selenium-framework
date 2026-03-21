package pageobjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.WaitUtils;

public class ProductCatalaguePage extends WaitUtils {
	
	WebDriver driver;
	
	public ProductCatalaguePage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> allProducts;
	
	By BlinkingText= By.xpath("//*[@class='blinkingText']");
	By findProductInList= By.cssSelector("b");
	By addToCartButton=By.cssSelector(".card-body button:last-of-type");
	By toastMessage= By.cssSelector("#toast-container");
	
	@FindBy(css=".ng-animating")
	WebElement animate;
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartLink;
	
	@FindBy(css="[routerLink*='myorders']")
	WebElement orders;
	
	
	
	public List<WebElement> getProductList()
	{
		waitForElementToAppearBy(BlinkingText);
		return allProducts;
	}
	
	public WebElement getProductByName(String productName) {
		return allProducts.stream().filter(product -> product.findElement(findProductInList).getText()
				.equals(productName)).findFirst().orElse(null);
		
	}
	
	public void addToCart(String productName) {
		WebElement prod= getProductByName(productName);
		prod.findElement(addToCartButton).click();
		waitForElementToAppearBy(toastMessage);
		waitForElementToDisappear(animate);
	}
	
	public CartPage goToCart() {
		cartLink.click();
		return new CartPage(driver);
	}
	
	public OrdersPage goToOrders() {
		orders.click();
		return new OrdersPage(driver);
	}
	
	

}
