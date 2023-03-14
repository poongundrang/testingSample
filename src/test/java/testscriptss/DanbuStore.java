package testscriptss;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DanbuStore {
	WebDriver driver;
	Properties prop;
	@BeforeTest
	public void setup() throws IOException
	{
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		String path=System.getProperty("user.dir")+"\\src\\test\\resources\\ConfigFiles\\config.properties";
		 prop=new Properties();
		FileInputStream obtained = new FileInputStream(path);
		prop.load(obtained);
		driver.get(prop.getProperty("url"));
		driver.get("https://danube-webshop.herokuapp.com");
	}
	
	@Test(dataProvider="Register")
	public void register(String name, String surname, String email, String password, String company) throws InterruptedException, InvalidFormatException, IOException
	{
	
		driver.findElement(By.id(XLData("Signup"))).click();
		driver.findElement(By.id(XLData("Name"))).sendKeys(name);
		driver.findElement(By.id(XLData("Surname"))).sendKeys(surname);
		driver.findElement(By.id(XLData("Email"))).sendKeys(email);
		driver.findElement(By.id(XLData("Password"))).sendKeys(password);
		driver.findElement(By.id(XLData("Company"))).sendKeys(company);
		driver.findElement(By.id(XLData("Myself"))).click();
		driver.findElement(By.id(XLData("Agreement"))).click();
		driver.findElement(By.id(XLData("Policy"))).click();
		driver.findElement(By.xpath(XLData("Register"))).click();		
	}
	@Test
	public void search() throws InterruptedException, SAXException, IOException, ParserConfigurationException {
//		driver.get(prop.getProperty("url"));
		driver.findElement(By.name(XMLData("search"))).sendKeys(prop.getProperty("search"));
		driver.findElement(By.className(XMLData("searchbtn"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(XMLData("addtocart"))).click();
		driver.findElement(By.xpath(XMLData("checkout"))).click();
		Thread.sleep(3000);
		driver.findElement(By.id(XMLData("m1"))).sendKeys(prop.getProperty("name"));
		driver.findElement(By.id(XMLData("m2"))).sendKeys(prop.getProperty("surname"));
		driver.findElement(By.id(XMLData("m3"))).sendKeys(prop.getProperty("address"));
		driver.findElement(By.id(XMLData("m4"))).sendKeys(prop.getProperty("zipcode"));
		driver.findElement(By.id(XMLData("m5"))).sendKeys(prop.getProperty("city"));
		driver.findElement(By.id(XMLData("m6"))).sendKeys(prop.getProperty("company"));
		driver.findElement(By.id(XMLData("m7"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(XMLData("buy"))).click();
		Thread.sleep(2000);
	}
	
	public String XLData(String objName) throws InvalidFormatException, IOException {
		  String objPath="";
		  String path=System.getProperty("user.dir")+"//src//test//resources//testData//danubeData.xlsx";
		  XSSFWorkbook workbook= new XSSFWorkbook(new File(path));
		  XSSFSheet sheet=workbook.getSheet("sheet1");
		  int numRows=sheet.getLastRowNum();
		  for(int i=0; i<=numRows; i++)
		  {
			  XSSFRow row=sheet.getRow(i);
			  if(row.getCell(0).getStringCellValue().equalsIgnoreCase(objName))
				  objPath=row.getCell(1).getStringCellValue();
		  }
		  return objPath;
	}
	public String XMLData(String tagname) throws SAXException, IOException, ParserConfigurationException {
		  String path=System.getProperty("user.dir")+"//src//test//resources//testData//danubeXML.xml";
		  File file= new File(path);
		  DocumentBuilderFactory factory= DocumentBuilderFactory.newInstance();
		  DocumentBuilder build=factory.newDocumentBuilder();
		  Document document= build.parse(file);
		  NodeList List= document.getElementsByTagName("Danube");
		  Node node1=List.item(0);
		  Element elem=(Element)node1;
		  return elem.getElementsByTagName(tagname).item(0).getTextContent();
	  
	  }
	
	 @DataProvider(name="Register")
	  public Object[][] getData() throws CsvValidationException, IOException{
		  String path=System.getProperty("user.dir")+"//src//test//resources//testData//danube.csv";
		  String[] cols;
		  CSVReader reader = new CSVReader(new FileReader(path));
		  ArrayList<Object> dataList=new ArrayList<Object>();
		  while((cols=reader.readNext())!=null)
		  {
			  Object[] record= {cols[0], cols[1], cols[2], cols[3], cols[4]};
			  dataList.add(record);
		  }
		  return dataList.toArray(new Object[dataList.size()][]);
		  
	  }
	 
}
