package Selenium;

import java.lang.reflect.Method;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


public class InitialDriver extends ModulesXero{
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest logger;
	public static ModulesXero modules = new ModulesXero();
	
	
	
	public static void main(String[] args) {
		
		
	String[][] recData = modules.readDataFromXl();
	extent = ModulesXero.extentReport("E:/selenium/XeroSeleniumExam/ExtentReports/NewReport.html");
	String firefoxStatus, ChromeStatus, ieStatus;

	for (int i = 1;i< recData.length; i++) {

		if (recData[i][1].equalsIgnoreCase("Y")) {
			firefoxStatus = recData[i][2];
			ChromeStatus = recData[i][4];
			ieStatus = recData[i][6];
			
			String UserName=recData[i][8];
			String Password=recData[i][9];
			String URL=recData[i][10];

			if (firefoxStatus.equalsIgnoreCase("Y")) {
				String tc = recData[i][0];
				
							

				System.out.println("output:" + tc);

				try {
				
                        
					logger=ModulesXero.startReport(tc, extent);
					
					
					Method testcase = AutomationTestScripts.class.getMethod(tc,String.class,String.class,String.class, String.class, int.class);
					
					testcase.invoke(new AutomationTestScripts(),UserName,Password,URL, ModulesXero.Firefox, i);
				} catch (Exception e) {

					modules.writeDataInXl(i, ModulesXero.FIREFOX, "fail");

					modules.setXlColorStyle(i, ModulesXero.FIREFOX, "fail");

				}

			}
			closeReport(extent);
			extent.flush();
			

			if (ChromeStatus.equalsIgnoreCase("Y")) {
				String tc = recData[i][0];
				
				
				try {
					logger=ModulesXero.startReport(tc, extent);
					
					Method testcase = AutomationTestScripts.class.getMethod(tc,String.class,String.class,String.class, String.class, int.class);
					testcase.invoke(new AutomationTestScripts(),UserName,Password,URL, ModulesXero.chrome, i);
				} catch (Exception e) {
					modules.writeDataInXl(i, ModulesXero.CHROME, "fail");
					modules.setXlColorStyle(i, ModulesXero.CHROME, "fail");

				}

			}
			closeReport(extent);
			extent.flush();

		/*	if (ieStatus.equalsIgnoreCase("Y")) {
				String tc = recData[i][0];
				try {
					Method testcase = AutomationTestScripts.class.getMethod(tc, String.class);
					testcase.invoke(new AutomationTestScripts(), "ie");
				} catch (Exception e) {
					modules.writeDataInXl(i, ModulesXero.IE, "fail");
					modules.setXlColorStyle(i, ModulesXero.IE, "fail");
				}

			}
			*/

		}

	}

}
}



