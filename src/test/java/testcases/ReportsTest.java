package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.TestBase;

public class ReportsTest extends TestBase{
	public static JavascriptExecutor js;
	
	@Test
	public void makingAlergieReport() {
		WebDriverWait success_wait = new WebDriverWait(driver, 5000);
		driver.findElement(By.cssSelector(OR.getProperty("childProfile"))).click();
		
		success_wait.until(ExpectedConditions.urlToBe("http://sadna.byethost33.com/reports.php"));

		Assert.assertEquals(driver.findElement(By.cssSelector(OR.getProperty(("alergieOption")))).getText(), " Allergies Report ");
		
		driver.findElement(By.id("options_for_report")).click();
		success_wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("alergicOptions")));
		
		driver.findElement(By.cssSelector("button[onclick^=getAlergicReports]")).click();
		success_wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("unsuccess-message")));
			
		String unsuccessMessage = driver.findElement(By.className("unsuccess-message")).getText();
		Assert.assertEquals(unsuccessMessage, "No alergic was recorded on selected");
				
		selectValue("alergicOptions"," Nuts ");
		driver.findElement(By.cssSelector("button[onclick^=getAlergicReports]")).click();
		
		success_wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("kids-table")));
	}
	
	@Test
	public void makingExceptionReport() {
		WebDriverWait success_wait = new WebDriverWait(driver, 5000);
		
		selectValue("optionsReport", " Exceptions Report ");
		driver.findElement(By.id("options_for_report")).click();
		
		success_wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("exceptionOptions")));
		success_wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("startDate")));
		success_wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("endDate")));		
		String nameOfKid = getSelectedOptionValue("exceptionOptions");
		
		Assert.assertEquals(nameOfKid, "shany noy");
		
		js= ((JavascriptExecutor) driver);
   	 	js.executeScript("document.getElementsByName('startDate')[0].value='2014-04-24'");
   	 	js.executeScript("document.getElementsByName('endDate')[0].value='2020-04-24'");
   	 	
   	 	driver.findElement(By.cssSelector("button[onclick^=genrateKidReport]")).click();
   	 	
		success_wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("kids-observation-table")));

	}
	
	@Test
	public void makingPresenceReport() {
		WebDriverWait success_wait = new WebDriverWait(driver, 5000);
		
		selectValue("optionsReport", " Non Presence Report ");
		driver.findElement(By.id("options_for_report")).click();
		
		success_wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("nopresenceoptions")));
		success_wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("startDate")));
		success_wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("endDate")));		
		String nameOfKid = getSelectedOptionValue("nopresenceoptions");
		
		Assert.assertEquals(nameOfKid, "shany noy");
		
		js= ((JavascriptExecutor) driver);
   	 	js.executeScript("document.getElementsByName('startDate')[0].value='2014-04-24'");
   	 	js.executeScript("document.getElementsByName('endDate')[0].value='2020-04-24'");
   	 	
   	 	driver.findElement(By.cssSelector("button[onclick^=getPresenceReport]")).click();
   	 	
	}
}
