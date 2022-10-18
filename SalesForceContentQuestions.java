package selenium_Java_Marathon;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SalesForceContentQuestions extends BaseClassSalesForce {

	// Content Check in SalesForce

	@Test(dataProvider = "fetchContentData")
	public void contentCheckSalesForce(String question, String detail) throws InterruptedException {

		// Type Content in search and Click on Content Link from search

		driver.findElement(By.xpath("//input[contains(@placeholder,'Search apps')]")).sendKeys("Content", Keys.ENTER);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//mark[text()='Content']")).click();

		// Click on Chatter

		WebElement chatterEle = driver.findElement(By.xpath("//span[text()='Chatter']"));
		driver.executeScript("arguments[0].click();", chatterEle);
		Thread.sleep(3000);
		String title = driver.getTitle();
		if (title.contains("Chatter")) {
			System.out.println("Current Page is : " + title);
		}

		else {
			System.out.println("Current Page is : " + title);
		}

		// Click on Questions

		driver.findElement(By.xpath("//span[text()='Question']")).click();

		// Type Questions and Details

		driver.findElement(By.xpath("//textarea[@role='textbox']")).sendKeys(question);
		driver.findElement(By.xpath("//span[text()='Details']/following::p")).sendKeys(detail);

		// Click on Ask

		driver.findElement(By.xpath("//button[text()='Ask']")).click();
		Thread.sleep(3000);

		// To verify the question which is posted

		String questions = driver.findElement(By.xpath("(//span[@class='uiOutputText'])[3]")).getText();
		System.out.println("Posted Question is ? ");
		System.out.println(questions);

	}

	// Dataprovider to pass the data to questions field which are fetched from Excel
	
	@DataProvider(name = "fetchContentData")
	public String[][] fetchContentData() throws IOException {

		String[][] excelData = BaseClassSalesForce.excelDataContent("content");
		return excelData;

	}

}
