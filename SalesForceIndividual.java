package selenium_Java_Marathon;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SalesForceIndividual extends BaseClassSalesForce {

	
	
	@Test(dataProvider="fetchIndividualData")
	public void individualSalesForce(String searchType,String firstname,String lastname) throws InterruptedException {

		// Type Individuals in search and Click on Content Link from search
		
		driver.findElement(By.xpath("//input[contains(@placeholder,'Search apps')]")).sendKeys(searchType,Keys.ENTER);
		Thread.sleep(6000);
		driver.findElement(By.xpath("//mark[text()='Individual']")).click();

		// Click on New in individuals page

		driver.findElement(By.xpath("//div[text()='New']")).click();

		// Click on Salutation

		driver.findElement(By.xpath("//a[contains(text(),'None')]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[contains(text(),'Mrs')]")).click();
		
		// Enter First Name & Last Name
		
		driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys(firstname);
		driver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys(lastname);
		
		// Click on Save
		
		driver.findElement(By.xpath("//button[@title='Save']")).click();
		Thread.sleep(3000);
		System.out.println("Customer name created is "+firstname);
		
		// To Verify Customer name entered in previous step
		// Click on Apps
		 
		WebElement appEle2 = driver.findElement(By.xpath("//div[@class='slds-icon-waffle']"));
		appEle2.click();
		Thread.sleep(6000);
		WebElement viewAllEle2 = driver.findElement(By.xpath("//button[text()='View All']"));
		viewAllEle2.click();
		
		driver.findElement(By.xpath("//input[contains(@placeholder,'Search apps')]")).sendKeys("Customers",Keys.ENTER);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//mark[text()='Customers']")).click();
		Thread.sleep(3000);
		
		// Click on New in Customers page

		driver.findElement(By.xpath("//div[text()='New']")).click();
		
        
		// Search the customer name "Vidya" in party name
		WebElement partyEle = driver.findElement(By.xpath("//span[text()='Party']/following::input"));
		driver.executeScript("arguments[0].click();",partyEle);
		partyEle.sendKeys(firstname);
		WebElement matchEle = driver.findElement(By.xpath("//mark[@class='data-match']"));
		String matchCusName = matchEle.getText();
		if(matchCusName.contains(firstname))
		System.out.println("Customer name matched is "+matchCusName);
	}
	

	// Dataprovider to pass the data to questions field which are fetched from Excel
	
		@DataProvider(name="fetchIndividualData")
		public String[][] fetchContentData() throws IOException {

			String[][] excelData = BaseClassSalesForce.excelDataContent("individual");
			return excelData;

		}

}
