package selenium_Java_Marathon;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseClassSalesForce {
	
    public RemoteWebDriver driver;
    public String browser;
    
	//	1. Login to https://login.salesforce.com in Edge Browser or Chrome Browser
	
    
    @Parameters({"url","username","password","browser"})
	@BeforeMethod  
    public void loginSalesForce(String url,String username,String password, String browser) throws InterruptedException{
		
    	if(browser.equalsIgnoreCase("edge"))
    	{
		WebDriverManager.edgedriver().setup();
		EdgeOptions options = new EdgeOptions();
		options.addArguments("--disable-notifications");
	    driver = new EdgeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(url);
		driver.manage().window().maximize();
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.xpath("//input[@name='Login']")).click();
		Thread.sleep(3000);
		WebElement appEle = driver.findElement(By.xpath("//div[@class='slds-icon-waffle']"));
		appEle.click();
		Thread.sleep(6000);
		WebElement viewAllEle = driver.findElement(By.xpath("//button[text()='View All']"));
		viewAllEle.click();
		Thread.sleep(3000);
    	}
    	
    	else if(browser.equalsIgnoreCase("chrome"))
    	{
    		WebDriverManager.chromedriver().setup();
    		ChromeOptions options = new ChromeOptions();
    		options.addArguments("--disable-notifications");
    	    driver = new ChromeDriver(options);
    		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    		driver.get(url);
    		driver.manage().window().maximize();
    		driver.findElement(By.id("username")).sendKeys(username);
    		driver.findElement(By.id("password")).sendKeys(password);
    		driver.findElement(By.xpath("//input[@name='Login']")).click();
    		Thread.sleep(3000);
    		WebElement appEle = driver.findElement(By.xpath("//div[@class='slds-icon-waffle']"));
    		appEle.click();
    		Thread.sleep(6000);
    		WebElement viewAllEle = driver.findElement(By.xpath("//button[text()='View All']"));
    		viewAllEle.click();
    		
    	}
    		
    	else
    	{
    		System.out.println("Invalid Browser Selection");
    	}
	}

    // Static method to fetch the data from Excel to pass it to dynamic data provider
    // Apache POI
    
	public static String[][] excelDataContent(String sheetName) throws IOException{
		
		XSSFWorkbook book = new XSSFWorkbook("./ExcelData/SalesForceDataSet.xlsx");
		XSSFSheet sheet = book.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		short columnCount = sheet.getRow(1).getLastCellNum();
		String[][] data = new String[rowCount][columnCount];
		
		for(int i=1; i<=rowCount; i++)
		{
			for (int j = 0; j<columnCount; j++) {
			
				String stringCellValue = sheet.getRow(i).getCell(j).getStringCellValue();
				data[i-1][j]=stringCellValue;
			}
		}
		
		return data;
	}
    
    @AfterMethod  
    public void postCondition()
    {
    	driver.close();	
    }
    
}

