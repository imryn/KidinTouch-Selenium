package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

// import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
/*
	WebDriver
	Properties
	Logs
	ExtenctReports
	DB
	Excel
	Mail
*/
	
	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static JavascriptExecutor js;
	
	//public static Logger log = Logger.getLogger("devpinoyLogger");
	
	@BeforeSuite
	public void setUp() {
		if(driver==null) {
			
			try {
				fis = new FileInputStream("C:\\Users\\noyim\\eclipse-workspace\\dataKidinTouchFramework\\src\\test\\resources\\properties\\Config.properties");

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fis);
			//	log.debug("Config file loaded!!");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				fis = new FileInputStream("C:\\Users\\noyim\\eclipse-workspace\\dataKidinTouchFramework\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
			//	log.debug("OR file loaded!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(config.getProperty("browser").equals("chrome")) {
			
			WebDriverManager.chromedriver().setup();
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\noyim\\eclipse-workspace\\dataKidinTouchFramework\\src\\test\\resources\\executables\\chromedriver.exe");
			driver = new ChromeDriver();
		//	log.debug("Chrome launched!!");
		}
		
		driver.get(config.getProperty("testsiteurl"));
	//	log.debug("Navigate to: " + config.getProperty("testsiteurl"));

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);
		
	}
	
	
	public void runOnHashMap(HashMap <String, String> myHash) {
		Set set = myHash.entrySet();
	    Iterator iterator = set.iterator();
	    while(iterator.hasNext()) {
	         Map.Entry mentry = (Map.Entry)iterator.next();
	         if(mentry.getKey().toString().equals("bDate")) {
	        	 js = ((JavascriptExecutor) driver);
	        	 js.executeScript("document.getElementsByName('bDate')[0].value='2014-04-24'");
	         } else {
	        	 enterValueToInput(mentry.getKey().toString(), mentry.getValue().toString());
		 		 driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS); 
	         }	        
	    }
	}
	
	
	public void enterValueToInput(String inputName, String val) {		
		driver.findElement(By.name(OR.getProperty(inputName))).sendKeys(val);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}
	
	
	public void selectValue(String selectName, String selVal) {
		Select selectValues = new Select(driver.findElement(By.name(OR.getProperty(selectName))));
		selectValues.selectByVisibleText(selVal);
	}
	
	public String getSelectedOptionValue(String selectName) {
		Select selectValues = new Select(driver.findElement(By.name(OR.getProperty(selectName))));
		WebElement option = selectValues.getFirstSelectedOption();
		String defaultItem = option.getText();
		
		return defaultItem;
	}
	
	
	public void ScrollToElement(String selectName) {
		WebElement inputBtn = driver.findElement(By.name(OR.getProperty(selectName)));
		Actions actions = new Actions(driver);
		actions.moveToElement(inputBtn);
		actions.perform();
	}
	
	
	public void selectRadioButton(String radioOptions) {
        List<WebElement> radioButton = driver.findElements(By.name(OR.getProperty(radioOptions)));
        for(int i=0; i<radioButton.size(); i++) {
            radioButton.get(i).click();
        }
	}
	
	@AfterSuite
	public void tearDown() {
		if(driver != null) {
			driver.quit();
		}
	}
	
}
