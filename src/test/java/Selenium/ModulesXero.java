package Selenium;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ModulesXero extends ReUsableMethods {
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest logger;

	public static final int FIREFOX = 3;
	public static final int CHROME = 5;
	public static final int IE = 7;

	public static final String Firefox = "Firefox";
	public static final String chrome = "chrome";
	public static final String ie = "internetexplorer";
	protected static WebDriver driver;

	String path = "E:/selenium/XeroSeleniumExam/TestSuite.xls";
	String sheet = "Sheet1";

	public WebDriver launchBrowser(String name,String URL) {
		try {
			if (name.equalsIgnoreCase(Firefox)) {
				System.setProperty("webdriver.gecko.driver",
						"C:\\Users\\KANISHKA\\Downloads\\geckodriver-v0.19.0-win64\\geckodriver.exe");
				driver = new FirefoxDriver();
				InitialDriver.logger.log(Status.INFO,"Firefox browser is opened");
				driver.get(URL);
				String title = driver.getTitle();
				InitialDriver.logger.log(Status.INFO,"Xero Login Page is opened");
				InitialDriver.logger.log(Status.PASS, title + " page is displayed");
				Thread.sleep(5000);
			} else if (name.equalsIgnoreCase(chrome)) {
				System.setProperty("webdriver.chrome.driver",
						"C:\\Users\\KANISHKA\\Downloads\\chromedriver_win32\\chromedriver.exe");
				driver = new ChromeDriver();
				InitialDriver.logger.log(Status.INFO,"Chrome browser is opened");
				driver.get(URL);
				String title = driver.getTitle();
				InitialDriver.logger.log(Status.INFO,"Xero Login Page is opened");
				InitialDriver.logger.log(Status.PASS, title + " page is displayed");
			} else if (name.equalsIgnoreCase(ie)) {
				System.setProperty("webdriver.ie.driver",
						"C:\\Users\\KANISHKA\\Downloads\\IEDriverServer_Win32_3.9.0\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				InitialDriver.logger.log(Status.INFO,"IE browser is opened");
				driver.get(URL);
				String title = driver.getTitle();
				InitialDriver.logger.log(Status.INFO,"Xero Login Page is opened");
				InitialDriver.logger.log(Status.PASS, title + " page is displayed");
				Thread.sleep(15000);
			}
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return driver;
	}
public void LoginPage(String UserName,String Password){
	String status="fail";
	try{
	WebElement username = driver.findElement(By.id("email"));
	 enterText(username, UserName, "EmailAddress");

		WebElement password = driver.findElement(By.id("password"));
		 enterText(password, Password, "Password");

			WebElement login = driver.findElement(By.id("submitButton"));
			clickObj(login, "Login");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			String actual = driver.getTitle();
			if(actual.contains("Xero")){
				InitialDriver.logger.log(Status.PASS,actual + " page is verified");
				status="pass";
			}
			
			
	}catch(Exception e){
			InitialDriver.logger.log(Status.FAIL,"page is not verified");
	}
	
	
}
public void clickBuyNow() throws InterruptedException{
	String status="fail";
	try{
	WebElement buyNow = driver.findElement(By.xpath(".//*[@id='simplebutton-1036']"));
	if(clickObj(buyNow,"Buy Now")){
		Thread.sleep(6000);
		String actual = driver.getTitle();
		if(actual.contains("Pricing Plan")){
			InitialDriver.logger.log(Status.PASS,actual + " page is verified");
			status="pass";
			Thread.sleep(4000);
			
		}
	}
		}
	catch(Exception e){
		InitialDriver.logger.log(Status.FAIL,"page is not verified");
}
	}

public void testData(){
	String status = "fail";
	try{
	WebElement continueButton = driver.findElement(By.xpath(".//*[@id='frmMain']/div/div[2]/div/main/div[10]/button"));
	if(clickObj(continueButton," Continue Button")){
		Thread.sleep(2000);
		WebElement street = driver.findElement(By.xpath(".//*[@id='POAddress']"));
		if(enterText(street,"3450 granada ave","Billing Address")){
			Thread.sleep(2000);
			WebElement city = driver.findElement(By.xpath(".//*[@id='POCity']"));
			if(enterText(city,"santa clara","City")){
				Thread.sleep(2000);
				WebElement state = driver.findElement(By.xpath(".//*[@id='PORegionDropdown']"));
				Select select = new Select(state);
				select.selectByVisibleText("California");
				Thread.sleep(2000);
				WebElement zipcode = driver.findElement(By.xpath(".//*[@id='POPostalCode']"));
				if(enterText(zipcode,"95051","ZipCode")){
					WebElement Continue = driver.findElement(By.xpath(".//*[@id='frmMain']/div/div/div/main/div[3]/div/div[2]/div/button"));
					if(clickObj(Continue,"Continue To Pay")){
						Thread.sleep(4000);
						String actualTitle = driver.getTitle();
						if(actualTitle.contains("Confirm")){
							InitialDriver.logger.log(Status.PASS,actualTitle + " page is verified");
							status="pass";
							Thread.sleep(3000);
				
						}
						else{
							InitialDriver.logger.log(Status.FAIL,"page is not verified");
						}

					}
				}
			}
		}
	}
	}
	catch (Exception e) {
		InitialDriver.logger.log(Status.FAIL,"Unable to thread sleep");
}

}

		


	public void exitBrowser() {
			
	try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {

		e.printStackTrace();
	}
		driver.quit();
	}
	public  void freeTrialPage() throws InterruptedException{
		
		WebElement freeTrial = driver.findElement(By.xpath("html/body/div[6]/header/nav/div[2]/div/div[1]/div/div/ul/li[1]/a"));
		if(clickObj(freeTrial,"Free Trail")){
			
			String title = driver.getTitle();
			InitialDriver.logger.log(Status.PASS,title + " page is verified");
			Thread.sleep(3000);
		}	
	}

	public String[][] readDataFromXl() {
		String[][] str = null;
		try {
			FileInputStream file = new FileInputStream(new File(path));
			HSSFWorkbook workbook = new HSSFWorkbook(file);
			HSSFSheet sheet = workbook.getSheet(this.sheet);

			int trow = sheet.getLastRowNum()+1;
			int tcol = sheet.getRow(0).getLastCellNum();

			str = new String[trow][tcol];
			System.out.println(" Total rows= " + trow + " Total col= " + tcol);
			for (int i = 0; i <trow; i++) {

				for (int j = 0; j < tcol; j++) {
					str[i][j] = sheet.getRow(i).getCell(j).getStringCellValue();

				}
			}
		} catch (Exception e) {

		}
		for (int i = 0; i < str.length; i++) {
			for (int j = 0; j < str[0].length; j++) {

				System.out.print(str[i][j] + " ");
			}
			System.out.println();

		}
		System.out.println();
		return str;

	}

	public void writeDataInXl(int row, int col, String text) {
		try {
			FileInputStream inputStream = new FileInputStream(new File(path));
			HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
			HSSFSheet sheet = workbook.getSheet(this.sheet);
			sheet.getRow(row).getCell(col).setCellValue(text);
			FileOutputStream outputStream = new FileOutputStream(new File(path));
			workbook.write(outputStream);
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			InitialDriver.logger.log(Status.FAIL,"Unable to write in the sheet");
		}

	}

	public void setXlColorStyle(int iRow, int iCol, String status) {
		try {
			FileInputStream inputStream = new FileInputStream(new File(path));
			HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
			HSSFSheet sheet = workbook.getSheet(this.sheet);

			HSSFRow row = sheet.getRow(iRow);
			HSSFCell cell = row.getCell(iCol);

			if (status.equalsIgnoreCase("pass")) {
				HSSFCellStyle style = (HSSFCellStyle) workbook.createCellStyle();

				style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
				style.setFillPattern(CellStyle.SOLID_FOREGROUND);
				cell.setCellStyle(style);

			} else if (status.equalsIgnoreCase("Fail")) {
				HSSFCellStyle style = (HSSFCellStyle) workbook.createCellStyle();
				style.setFillForegroundColor(IndexedColors.RED.getIndex());
				style.setFillPattern(CellStyle.SOLID_FOREGROUND);
				cell.setCellStyle(style);

			}
			FileOutputStream outputStream = new FileOutputStream(new File(path));
			workbook.write(outputStream);
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			InitialDriver.logger.log(Status.FAIL,"Unable to style the cell");
		}
	}

	public void AddAnOrganisation(){
		try{
		WebElement myXero = driver.findElement(By.xpath(".//*[@id='xero-nav']/div[2]/div[1]/div[1]/div/h2"));
		if (clickObj(myXero, "My Xero")) {
			Thread.sleep(2000);
			WebElement xero = driver
					.findElement(By.xpath(".//*[@id='xero-nav']/div[2]/div[1]/div[1]/div/div/div[1]/a"));
			if (clickObj(xero, "Xero")) {
				Thread.sleep(3000);
		
	

				WebElement addOrganisation = driver.findElement(By.xpath(".//*[@id='ext-gen1043']"));
				if (clickObj(addOrganisation, "Add an Organisation")) {
					Thread.sleep(3000);
					WebElement name = driver.findElement(By.xpath(".//*[@id='text-1022-inputEl']"));
					if (enterText(name, "self", "Name of an Organisation")) {
						Thread.sleep(3000);

						WebElement where = driver.findElement(By.xpath(".//*[@id='countryCmb-inputEl']"));
						clickObj(where,"Country");
						clear(where, "Country");
						Thread.sleep(1000);
						if (enterText(where, "United States", "Country")) {
					WebElement country = driver.findElement(By.xpath(".//*[@id='countryCmb-boundlist-listEl']/ul/li[1]"));
			
						clickObj(country,"Country");
						Thread.sleep(2000);

							WebElement timeZone = driver.findElement(By.xpath(".//*[@id='cmbTimeZone-inputEl']"));
							clickObj(timeZone,"Time Zone");
							clear(timeZone, "Time Zone");
							Thread.sleep(1000);
							if (enterText(timeZone, "(UTC-08:00) Pacific Time (US & Canada)", "Time Zone")) {
								WebElement time = driver.findElement(By.xpath(".//*[@id='cmbTimeZone-boundlist-listEl']/ul/li"));
								clickObj(time,"TimeZone");
								
								Thread.sleep(3000);

								WebElement what = driver
										.findElement(By.xpath(".//*[@id='industrysearchcombofield-1025-inputEl']"));
								clickObj(what,"What does Organisation do");
								Thread.sleep(1000);
								if (enterText(what, "Accounting", "What does your organisation do")) {
									WebElement organisation = driver.findElement(By.xpath(".//*[@id='industrysearchcombofield-1025-boundlist-listEl']/div/ul/li[3]"));
									clickObj(organisation,"What does your Organisation do");
									
									Thread.sleep(3000);
	}
							}
						}
					}
						}
				
					}
				}
				
		
	}catch (Exception e) {
		InitialDriver.logger.log(Status.FAIL,"Unable to thread sleep");
}
	}



	public static ExtentReports extentReport(String path) {
		
		htmlReporter = new ExtentHtmlReporter("E:/selenium/XeroSeleniumExam/ExtentReports/NewReport.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "eclipse");
		extent.setSystemInfo("Environment", "SalesForce");
		extent.setSystemInfo("User Name", "TestCase");

		htmlReporter.config().setDocumentTitle("extent reports");
		htmlReporter.config().setReportName("functional testing reports");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);
	

		return extent;
	}
	public static ExtentTest startReport(String tc,ExtentReports extent){
	
		logger=extent.createTest(tc);
		
		return logger;
	}
	public static void closeReport(ExtentReports extent){
		extent.flush();
	}
}

	

