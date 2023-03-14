package testscriptss;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginPageTest {
	WebDriver driver;
	@BeforeMethod
	public void setup() {
		WebDriverManager.chromedriver().setup();
	    driver= new ChromeDriver();
		driver.manage().window().maximize();
	}
  @Test(dataProvider="LoginDataa")
  public void validLoginTest(String strUser, String strPwd) throws InvalidFormatException, IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
	  driver.get("http://the-internet.herokuapp.com/login");
	  driver.findElement(By.xpath((readData("strUser")))).sendKeys(strUser);
	  driver.findElement(By.id((readData("strPwd")))).sendKeys(strPwd);
	  driver.findElement(By.className(readData("loginBtn"))).click();
	  boolean isDisp = driver.findElement(By.cssSelector(".flash.success")).isDisplayed();
	  Assert.assertTrue(isDisp);
  }
  
  public String readData(String objName) throws InvalidFormatException, IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
	  String objPath="";
	  String path = System.getProperty("user.dir")
			  +"//src//test//resources//TesstData//excelData.xlsx";
	  XSSFWorkbook workbook = new XSSFWorkbook(new File(path));
	  XSSFSheet sheet = workbook.getSheet("LoginPageTest");
	  int numRows = sheet.getLastRowNum();
	  for(int i=1;i<=numRows;i++)
	  {
		  XSSFRow row = sheet.getRow(i);
		  if(row.getCell(0).getStringCellValue().equalsIgnoreCase(objName)) {
			  objPath = row.getCell(1).getStringCellValue();
		  }
	  }
	  
	  return objPath;
  }
  
  @DataProvider(name="LoginDataa")
  public Object[][] getData() throws CsvValidationException, IOException {
	  String path = System.getProperty("user.dir")
			  +"//src//test//resources//TesstData//excelData.xlsx";
	  String[] cols;
		  CSVReader reader = new CSVReader(new FileReader(path));
	  ArrayList<Object> dataList = new ArrayList<Object>();
	  while((cols = reader.readNext()) !=null) {
		  Object[] record = {cols[0],cols[1]};
		  dataList.add(record);
		  
	  }
	  return dataList.toArray(new Object[dataList.size()][]);
	  
  }
  @AfterMethod
  public void teardown() {
	  driver.close();
  }
  
}
