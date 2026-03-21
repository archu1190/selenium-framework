package base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageobjects.LandingPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseTest {

	String browserName;
	public WebDriver driver;
	protected LandingPage landingPage;
	public static Logger log= LogManager.getLogger(BaseTest.class);
	

	public WebDriver initializeBrowser() throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\resources\\config.properties");
		prop.load(fis);
		browserName = prop.getProperty("browser");
		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;

	}

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts= (TakesScreenshot)driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		String folderPath=System.getProperty("user.dir")+"//reports//";
		File folder=new File(folderPath);
		if(!folder.exists()) {
			folder.mkdir();
		}
		String filePath= folderPath+testCaseName+"_"+System.currentTimeMillis()+".png";
		
		FileUtils.copyFile(src, new File(filePath));
		return filePath;
		
	}

	@BeforeMethod(alwaysRun = true)
	public void launchBrowser() throws IOException {

		driver = initializeBrowser();
		landingPage = new LandingPage(driver);
		landingPage.goTo();

	}

	
	 @AfterMethod(alwaysRun = true) public void tearDown() { driver.quit(); }
	 

}
