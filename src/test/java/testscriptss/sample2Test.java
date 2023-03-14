package testscriptss;


	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.FileOutputStream;
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
	import org.testng.annotations.Parameters;
	import org.testng.annotations.Test;
	import org.testng.asserts.SoftAssert;

	import io.github.bonigarcia.wdm.WebDriverManager;


	public class sample2Test {
	  WebDriver driver;
	  @BeforeTest
	  public void setup() {
		    WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
	  }
	  @Test(retryAnalyzer = RetrySampleTest.class)//to ignore the test, so does not run
	  public void javaSearchTest() throws IOException {
		    driver.get("https://www.google.com/");
//		    driver.get(connProp.getProperty("Url"));
		 	WebElement searchBox = driver.findElement(By.name("q"));
			searchBox.sendKeys("Java Tutorial");
			searchBox.submit();
			Assert.assertEquals(driver.getTitle(),"Java Tutorialh - Google Search");	
	  }

}
