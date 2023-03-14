package testscriptss;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.beust.jcommander.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SampleOneTest {
	
	WebDriver driver;
	
	//@BeforeMethod
/*	public void readFromProp() throws IOException {
		String path = System.getProperty("user.dir")
		+"//src//test//resources//configFiles//config.properties";
		prop = new Properties();
		FileInputStream fin = new FileInputStream(path);
		prop.load(fin);
	}*/
	//@Parameters("browser")
	
	@BeforeMethod
	public void setup(String strBrowser) {
		if(strBrowser.equalsIgnoreCase("chrome")) {
		WebDriverManager.chromedriver().setup();
	    driver= new ChromeDriver();
		}
		driver.manage().window().maximize();
	}
	
  @Test
  public void javaSearchTest() {
//	  System.setProperty("webdriver.chrome.driver", "E:\\ExSelenium'\\chromedriver_win32 (2)\\chromedriver.exe");
//		WebDriver driver = new ChromeDriver();
		//Actions actions = new Actions(driver);
//		driver.manage().window().maximize();
		driver.get("https://www.google.com/");
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("Java Tutorial");
		searchBox.submit();
		Assert.assertEquals(driver.getTitle(), "Java Tutorial - Google Search");
//		driver.close();
  }
  
 // @Test
  public void javSearchTest() {
	//  System.setProperty("webdriver.chrome.driver", "E:\\ExSelenium'\\chromedriver_win32 (2)\\chromedriver.exe");
	//	WebDriver driver = new ChromeDriver();
		//Actions actions = new Actions(driver);
	//	driver.manage().window().maximize();
		driver.get("https://www.google.com/");
		WebElement searchBox = driver.findElement(By.name("q"));
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(driver.getTitle(),"Google Page");
		searchBox.sendKeys("Java Tutorial");
		searchBox.submit();
		softAssert.assertEquals(driver.getTitle(), "Java Tutorial - Google Search");
		softAssert.assertAll();
//		driver.close();
  }
  
  @Test(alwaysRun=true,dependsOnMethods="javaSearchTest")
  public void SeleniumSearchTest() {
//	  System.setProperty("webdriver.chrome.driver", "E:\\ExSelenium'\\chromedriver_win32 (2)\\chromedriver.exe");
//		WebDriver driver = new ChromeDriver();
		//Actions actions = new Actions(driver);
//		driver.manage().window().maximize();
		driver.get("https://www.google.com/");
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("Selenium Tutorial");
		searchBox.submit();
		Assert.assertEquals(driver.getTitle(), "Selenium Tutorial - Google Search"); //If we add Google search Page it may cause failure in such cases
//		driver.close();
  }
  
  @Test(enabled=false)
  public void CucumberSearchTest() {
//	  System.setProperty("webdriver.chrome.driver", "E:\\ExSelenium'\\chromedriver_win32 (2)\\chromedriver.exe");
//		WebDriver driver = new ChromeDriver();
		//Actions actions = new Actions(driver);
//  	driver.manage().window().maximize();
		driver.get("https://www.google.com/");
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("Cucumber Tutorial");
		searchBox.submit();
		Assert.assertEquals(driver.getTitle(), "Cucumber Tutorial - Google Search"); //If we add Google search Page it may cause failure in such cases
//		driver.close();
  }
  
  @AfterMethod
  //@AfterTest
  public void teardown() {
	  driver.close();
  }
}
