package testcases;

import java.util.Iterator;
import java.util.List;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.TestBase;

public class LoginTest extends TestBase {

	@BeforeTest
	public void loginLink() {
		Actions act = new Actions(driver);
		
		WebElement loginLink  = driver.findElement(By.id(OR.getProperty("loginLink")));

		WebElement parentLogin = driver.findElement(By.cssSelector((".dropdown-item:last-child")));

		act.moveToElement(loginLink).perform();
		loginLink.click();
		parentLogin.click();

		Assert.assertEquals(driver.getCurrentUrl(), "http://sadna.byethost33.com/login_page.php?usertype=parent");
	}
	
	@Test(dependsOnMethods = {"fakeLogin", "fakeParentId", "fakePass", "EmptyInputsForLogin"})
	public void login() {
		enterValueToInput("parentId", "201211943");
		enterValueToInput("password", "12345i");
		
		driver.findElement(By.className("btn")).click();
		WebDriverWait success_wait = new WebDriverWait(driver, 5000);
		
		success_wait.until(ExpectedConditions.urlToBe("http://sadna.byethost33.com/index.php"));
		
	}
	
	@Test
	public void fakeLogin() {
		enterValueToInput("parentId", "54353gffg");
		enterValueToInput("password", "33242");
		WriteLoginInputs();		
	}
	
	@Test
	public void fakeParentId() {
		enterValueToInput("parentId", "54353gffg");
		enterValueToInput("password", "12345i");
		WriteLoginInputs();
	}
	
	@Test
	public void fakePass() {
		enterValueToInput("parentId", "201211943");
		enterValueToInput("password", "33242");
		WriteLoginInputs();
	}
	
	@Test
	public void EmptyInputsForLogin() {
		enterValueToInput("parentId", "");
		enterValueToInput("password", "");
		WriteLoginInputs();	
	}
	
	public static void WriteLoginInputs() {
		driver.findElement(By.className("btn")).click();
		
		WebElement parentId = driver.findElement(By.name(OR.getProperty("parentId")));
		WebElement pass = driver.findElement(By.name(OR.getProperty("password")));
		
		
		Assert.assertEquals(parentId.getAttribute("value").isEmpty(), true);
		Assert.assertEquals(pass.getAttribute("value").isEmpty(), true);
		Assert.assertEquals(driver.getCurrentUrl(), "http://sadna.byethost33.com/login_page.php?usertype=parent");	
	}
}
