package base;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StandAloneTest {
	
	@Test
	public void addToCart() {
		
		String productName="ZARA COAT 3";
		WebDriver driver=new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		driver.findElement(By.xpath("//*[@id='userEmail']")).sendKeys("archutce@gmail.com");
		driver.findElement(By.xpath("//*[@id='userPassword']")).sendKeys("Archvimal$5");
		driver.findElement(By.xpath("//*[@id='login']")).click();
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='blinkingText']")));
		
		//search all products
		
		List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement prod= products.stream().filter(product -> product.findElement(By.cssSelector("b")).getText()
		.equals(productName)).findFirst().orElse(null);
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		//performance issue with commented line, so using invisibilityOf with full driver 
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		//click cart
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		//verify cart
		
		List<WebElement> cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));
		boolean match=cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		//payment page
		
		Actions action=new Actions(driver);
		action.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "India").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		//confirmation page
		String confirmationMessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("Thankyou for the order."));
		
		
		
	}
	
	

}
