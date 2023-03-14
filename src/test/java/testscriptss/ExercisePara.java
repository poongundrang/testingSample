  package testscriptss;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExercisePara {
	WebDriver driver;
	String accCheck;
	@BeforeTest	
	public void first(){
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://parabank.parasoft.com/");
		
	}
	@Test(dependsOnMethods="register")
	  public void loginPage() {
		  driver.findElement(By.name("username")).sendKeys("Atman1");
		  driver.findElement(By.name("password")).sendKeys("Poo@786514");
		  driver.findElement(By.xpath("//input[@class='button']")).click();
	  }
	
	@Test(priority=1)
	public void register() throws InterruptedException
	{
		driver.findElement(By.partialLinkText("Register")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("customer.firstName")).sendKeys("Poongundran");
		driver.findElement(By.id("customer.lastName")).sendKeys("G");
		driver.findElement(By.id("customer.address.street")).sendKeys("Rice mill");
		driver.findElement(By.id("customer.address.city")).sendKeys("Vpm");
		driver.findElement(By.id("customer.address.state")).sendKeys("TN");
		driver.findElement(By.id("customer.address.zipCode")).sendKeys("605651");
		driver.findElement(By.id("customer.phoneNumber")).sendKeys("9361336050");
		driver.findElement(By.id("customer.ssn")).sendKeys("123456");
		driver.findElement(By.id("customer.username")).sendKeys("Atman");
		driver.findElement(By.id("customer.password")).sendKeys("Poo@786514");
		driver.findElement(By.xpath("//td/input[@id='repeatedPassword']")).sendKeys("Poo@786514");
		driver.findElement(By.xpath("//td/input[@class='button']")).click();
		
	}  
	
	/*@Test(dependsOnMethods="register")
	  public void loginPage() {
		  driver.findElement(By.name("username")).sendKeys("Atman1");
		  driver.findElement(By.name("password")).sendKeys("Poo@786514");
		  driver.findElement(By.xpath("//input[@class='button']")).click();
	  }
	*/
  @Test(priority=2)
  public void openaccount() throws InterruptedException
  {
	  Thread.sleep(3000);
	  driver.findElement(By.xpath("//li/a[contains(text(),'Open New Account')]")).click();
	  Select newacc=new Select(driver.findElement(By.xpath("(//select)[1]")));
	  newacc.selectByVisibleText("SAVINGS");
	  Thread.sleep(3000);
	  driver.findElement(By.xpath("//input[@class='button']")).submit();
	  Thread.sleep(3000);
	  Assert.assertEquals("Account Opened!", driver.findElement(By.xpath("//h1")).getText());
	  accCheck=  driver.findElement(By.id("newAccountId")).getText();
	  System.out.println(accCheck);
  }
  
  @Test(priority=3)
  public void overview() throws InterruptedException
  {
	  driver.findElement(By.partialLinkText("Overview")).click();
	  Thread.sleep(5000);
	 List<WebElement> list= driver.findElements(By.xpath("//td/a[@class='ng-binding']"));
  	  Assert.assertEquals(list.get(list.size()-1).getText(), accCheck);
  }
  
  @Test (dependsOnMethods="openaccount")
  public void Transferfund() throws InterruptedException {
	  driver.findElement(By.xpath("//li/a[contains(text(),'Fund')]")).click();
	  Thread.sleep(5000);
	  driver.findElement(By.xpath("//form//p/input")).sendKeys("3000");
	Select from=new Select(driver.findElement(By.id("fromAccountId")));
	from.selectByIndex(0);
	Select to=new Select(driver.findElement(By.id("fromAccountId")));
	to.selectByIndex(1);
	driver.findElement(By.xpath("//input[@class='button']")).submit();
	  Thread.sleep(5000);
	  Assert.assertEquals(driver.findElement(By.xpath("//h1")).getText(), "Transfer Complete!");
  }
  
  

}

    
