package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.WaitUtils;

public class OrdersPage extends WaitUtils {
	
	WebDriver driver;

	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//*[@text()='ZARA COAT 3']")
	WebElement orderedText;
	
	public String getOrderedText() {
		return orderedText.getText();
	}
	
}
