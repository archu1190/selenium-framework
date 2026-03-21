package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.WaitUtils;

public class LandingPage extends WaitUtils {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver); //pass driver scope to parent
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="//*[@id='userEmail']")
	WebElement userEmail;
	
	@FindBy(xpath="//*[@id='userPassword']")
	WebElement userPassword;
	
	@FindBy(xpath="//*[@id='login']")
	WebElement loginButton;
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");	
	}
	
	public ProductCatalaguePage login(String email,String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		loginButton.click();
		return new ProductCatalaguePage(driver);
	}

}
