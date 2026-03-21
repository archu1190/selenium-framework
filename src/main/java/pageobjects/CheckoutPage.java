package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import utils.WaitUtils;

public class CheckoutPage extends WaitUtils{
	
	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement enterCountry;
	
	By selectDropdown = By.cssSelector(".ta-results");
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement enteredCountry;
	
	@FindBy(css=".action__submit")
	WebElement placeOrderButton;
	
	
	public void selectCountry(String country) {
		Actions action=new Actions(driver);
		action.sendKeys(enterCountry, country).build().perform();
		waitForElementToAppearBy(selectDropdown);
		enteredCountry.click();
	}
	
	public ConfirmationPage goToConfirmation() {
		placeOrderButton.click();
		return new ConfirmationPage(driver);
	}
	
	
	

}
