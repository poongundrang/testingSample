package testscriptss;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SampleTwoTest {
  @Test(retryAnalyzer = RetrySampleTest.class)
  public void CypressSearchTest() {
	 // System.setProperty("webdriver.chrome.driver", "E:\\ExSelenium'\\chromedriver_win32 (2)\\chromedriver.exe");
		WebDriverManager.chromedriver().setup();
		WebDriver driver= new ChromeDriver();
	//  WebDriverManager.edgedriver().setup();
	//  WebDriver driver = new EdgeDriver();
	//	WebDriverManager.firefoxdriver().setup();
	//	WebDriver driver= new FirefoxDriver();
	  //Actions actions = new Actions(driver);
		driver.manage().window().maximize();
		driver.get("https://www.google.com/");
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("Cypress Tutorial");
		searchBox.submit();
		Assert.assertEquals(driver.getTitle(), "Cypress Tutorial - Google Search"); //If we add Google search Page it may cause failure in such cases
		//driver.close();
  }
}
