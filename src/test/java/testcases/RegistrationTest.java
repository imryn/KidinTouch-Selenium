package testcases;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.TestBase;

public class RegistrationTest extends TestBase {
	
	public static int randomNumber;
    HashMap<String, String> inputsMapParent = new HashMap<String, String>();
    HashMap<String, String> inputsMapKid = new HashMap<String, String>();
    
	@BeforeTest
	public void registration() {
		
		randomNumber = ( int )( Math.random() * 99999 );

		driver.findElement(By.cssSelector(OR.getProperty("registrationLink"))).click();
		inputsMapParent.put("kindergartenid", String.valueOf(randomNumber));
		inputsMapParent.put("firstname", "aia");
		inputsMapParent.put("lastname", "noy");
		inputsMapParent.put("parentId", "2073"+String.valueOf(randomNumber));
		inputsMapParent.put("password", "aia"+String.valueOf(randomNumber));
		inputsMapParent.put("addressuser", "yehoda amacabi");
		inputsMapParent.put("city", "Herzelia");
		inputsMapParent.put("email", "noyaia"+String.valueOf(randomNumber)+"@gmail.com");
		inputsMapParent.put("mobilephone", "054"+String.valueOf(randomNumber)+"74");
		inputsMapParent.put("anothercontact", "yossi");
		inputsMapParent.put("relationship", "grandfather");
		inputsMapParent.put("mobilephone2", "052"+String.valueOf(randomNumber));
		
		inputsMapKid.put("fname", "dani");
		inputsMapKid.put("kidId", "2073"+String.valueOf(randomNumber));
		inputsMapKid.put("bDate", "2014-04-24");
	}
	
	
	@Test
	public void fillFormOfParent() {
		  runOnHashMap(inputsMapParent);		
	      selectValue("familyMember", "Mother");
	      ScrollToElement("bDate");
	}
	
	
	@Test
	public void fillFormOfKid() {	
		runOnHashMap(inputsMapKid);
		selectValue("genders", "Male");
		ScrollToElement("foodpreference");
		
		for(int i=1; i<=4; i++) {
			driver.findElement(By.xpath("//section[@id='registration-kid']/form/div[3]/div["+i+"]"+"/div/p/input")).click();
		}
		
		selectRadioButton("foodpreference");
		
	}
	
	@Test(dependsOnMethods = {"fillFormOfKid", "fillFormOfParent"})
	public void SaveForm() {
		driver.findElement(By.className(OR.getProperty("saveBtn"))).click();
		WebDriverWait success_wait = new WebDriverWait(driver, 5000);
		success_wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className(("modal-content"))));
		success_wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className(("btn-primary"))));
		driver.findElement(By.className("btn-primary")).click();
		
		success_wait.until(ExpectedConditions.urlToBe("http://sadna.byethost33.com/index.php"));

	}
	
	
}
