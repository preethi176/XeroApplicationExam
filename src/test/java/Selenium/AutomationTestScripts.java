package Selenium;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

public class AutomationTestScripts extends ModulesXero {
	private int mapToBrowser(String browserName) {
		if (browserName.equals(Firefox)) {
			return ModulesXero.FIREFOX;
		}
		if (browserName.equals(chrome)) {
			return ModulesXero.CHROME;
		}
		if (browserName.equals(ie)) {
			return ModulesXero.IE;
		}
		return 0;
	}

	public void NavigateToXERO_TC01_A(String UserName, String Password, String URL, String browserName, int i) {
		String status = "fail";
		
		launchBrowser(browserName, URL);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {

			e1.printStackTrace();
		}

		LoginPage(UserName, Password);
		InitialDriver.logger.log(Status.INFO, "Xero Home Page is displayed");
		status="pass";

		exitBrowser();
		writeDataInXl(i, mapToBrowser(browserName), status);

		setXlColorStyle(i, mapToBrowser(browserName), status);

	}

	public void IncorrectPassword_TC01_B(String UserName, String Password, String URL, String browserName, int i) {
		String status = "fail";
		try{
		launchBrowser(browserName, URL);
		

		WebElement emailAddress = driver.findElement(By.id("email"));
		if (enterText(emailAddress, "user@gmail.com", "Email Address")) {

			WebElement password = driver.findElement(By.id("password"));
			if (enterText(password, "apple", "Password")) {

				WebElement login = driver.findElement(By.id("submitButton"));
				if (clickObj(login, "Login")) {
					Thread.sleep(5000);

					WebElement ErrorMsg = driver
							.findElement(By.xpath(".//*[@id='contentTop']/div[2]/div[1]/div[2]/p"));
					String actualErrorMsg=ErrorMsg.getText();
					Thread.sleep(2000);
					if (actualErrorMsg.contains("Your email or password is incorrect")) {
						status = "pass";
						InitialDriver.logger.log(Status.PASS, actualErrorMsg + "Error message is verified");
					} else {
						InitialDriver.logger.log(Status.FAIL,"Error message is not verified");
					}

				}
			}
		}
		}catch(Exception e){
			InitialDriver.logger.log(Status.INFO,"Unable to thread sleep");
		}

		exitBrowser();
		writeDataInXl(i, mapToBrowser(browserName), status);
		setXlColorStyle(i, mapToBrowser(browserName), status);

	}

	public void IncorrectEmail_TC01_C(String UserName, String Password, String URL, String browserName, int i) {
		String status = "fail";
		try{
		launchBrowser(browserName, URL);

		WebElement username = driver.findElement(By.id("username"));
		enterText(username, "apple.mango@gmail.com", "Username");
		Thread.sleep(3000);
		WebElement password = driver.findElement(By.id("password"));
		if (enterText(password, "apple", "Password")) {

			WebElement login = driver.findElement(By.id("submitButton"));
			if (clickObj(login, "Login")) {
				Thread.sleep(5000);

				String actualErrorMsg = driver.findElement(By.xpath(".//[@id='contentTop']/div[2]/div[1]/div[2]/p"))
						.getText();
				InitialDriver.logger.log(Status.PASS,actualErrorMsg + " Error message is verified");
				if (actualErrorMsg.contains("Your email or password is incorrect")) {
					status = "pass";
					InitialDriver.logger.log(Status.PASS,actualErrorMsg + " Error message is verified");
				} else {
					InitialDriver.logger.log(Status.FAIL,"Error message is not verified");
				}

			}
		}
		}catch(Exception e){
			InitialDriver.logger.log(Status.INFO,"Unable to thread sleep");
		}

		exitBrowser();
		writeDataInXl(i, mapToBrowser(browserName), status);
		setXlColorStyle(i, mapToBrowser(browserName), status);

	}

	public void NavigateToXERO_TC01_D(String UserName, String Password, String URL, String browserName, int i) {
		String status = "fail";
		try{
		launchBrowser(browserName, URL);
		Thread.sleep(3000);
		WebElement un = driver.findElement(By.xpath(".//*[@id='email']"));
		if (enterText(un, "User@gmail.com", "Username")) {
			Thread.sleep(2000);

		WebElement forgotPwd = driver.findElement(By.xpath(".//*[@id='contentTop']/div[2]/div[1]/a"));
		if (clickObj(forgotPwd, "Forgot Password")) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			Thread.sleep(5000);
			String actual = driver.getTitle();
			if (actual.contains("Forgotten Password")) {
				InitialDriver.logger.log(Status.PASS,actual + " Error message is verified");
			} else {
				InitialDriver.logger.log(Status.FAIL," Error message is not verified");
			}
			Thread.sleep(3000);
			WebElement username = driver.findElement(By.xpath(".//[@id='UserName']"));
			if (enterText(username, "User@gmail.com", "Username")) {
				Thread.sleep(2000);

				WebElement sendLink = driver.findElement(By.xpath(".//[@id='submitButton']/a/span"));
				if (clickObj(sendLink, "Send Link")) {
					Thread.sleep(2000);

					WebElement message = driver.findElement(By.xpath(".//[@id='contentTop']/div/p[1]"));
					if (validateContent(message, "A link to reset your password has been sent to:User@gmail.com","Message")) {
						Thread.sleep(2000);
						status = "pass";

					}
				}
			}
			
		}
		
			
		}
		}
catch(Exception e){
	InitialDriver.logger.log(Status.INFO,"Unable to thread sleep");
}
		exitBrowser();
		writeDataInXl(i, mapToBrowser(browserName), status);
		setXlColorStyle(i, mapToBrowser(browserName), status);

	}

	public void SignUpToXDC_TC02_A(String UserName, String Password, String URL, String browserName, int i)
			throws InterruptedException {
		String status = "fail";
		launchBrowser(browserName, URL);
		InitialDriver.logger.log(Status.INFO,"browser opened");
		freeTrialPage();
		InitialDriver.logger.log(Status.INFO,"Free Trial page is  opened");

		WebElement firstName = driver
				.findElement(By.xpath("html/body/div[6]/main/div[1]/div/div/form/div[2]/label/input"));
		if (enterText(firstName, "apple", "First Name")) {
			Thread.sleep(1000);
			WebElement lastName = driver
					.findElement(By.xpath("html/body/div[6]/main/div[1]/div/div/form/div[3]/label/input"));
			if (enterText(lastName, "Mango", "Last Name")) {

				Thread.sleep(1000);
				WebElement emailAddress = driver
						.findElement(By.xpath("html/body/div[6]/main/div[1]/div/div/form/div[4]/label/input"));
				if (enterText(emailAddress, "preethi.176@gmail.com", "Email Address")) {

					Thread.sleep(1000);

					WebElement phoneNumber = driver
							.findElement(By.xpath("html/body/div[6]/main/div[1]/div/div/form/div[5]/label/input"));
					if (enterText(phoneNumber, "4257184596", "PhoneNumber")) {

						Thread.sleep(1000);

						WebElement country = driver.findElement(
								By.xpath("html/body/div[6]/main/div[1]/div/div/form/div[6]/label/span/select"));
						clickObj(country,"Country");
						Select select = new Select(country);
						select.selectByVisibleText("India");

						Thread.sleep(1000);

						WebElement terms = driver.findElement(
								By.xpath("html/body/div[6]/main/div[1]/div/div/form/div[8]/div/label/input"));
						if (selectCheckBox(terms, "Terms and Conditions")) {

						}
					}
				}

			}
			exitBrowser();
			writeDataInXl(i, mapToBrowser(browserName), status);
			setXlColorStyle(i, mapToBrowser(browserName), status);
		}

	}

	public void SignUpToXDC_TC02_B(String UserName, String Password, String URL, String browserName, int i)
			throws InterruptedException {
		String status = "fail";
		launchBrowser(browserName, URL);

		freeTrialPage();

		WebElement getStarted = driver.findElement(By.xpath("/html/body/div[6]/main/div[1]/div/div/form/div[9]/span"));
		if (clickObj(getStarted, "GetStarted")) {

			WebElement errorMsg1 = driver.findElement(By.xpath(".//[@id='signup-form-error-message-1']"));
			String actual1 = errorMsg1.getText();
			if (actual1.contains("First name can't be empty")) {
				InitialDriver.logger.log(Status.PASS,actual1 + "FirstName Error message is verified");
			} else {
				InitialDriver.logger.log(Status.FAIL,"FirstName Error message is not verified");
			}
			Thread.sleep(1000);
			WebElement errorMsg2 = driver.findElement(By.xpath(".//[@id='signup-form-error-message-2']"));
			String actual2 = errorMsg2.getText();
			if (actual2.contains("Last name can't be empty")) {
				InitialDriver.logger.log(Status.PASS,actual2 + "LastNAme Error message is verified");
			} else {
				InitialDriver.logger.log(Status.FAIL,"LastNAme Error message is not verified");
			}
			Thread.sleep(1000);
			WebElement errorMsg3 = driver.findElement(By.xpath(".//[@id='signup-form-error-message-3']"));
			String actual3 = errorMsg3.getText();
			if (actual3.contains("Email address can't be empty")) {
				InitialDriver.logger.log(Status.PASS,actual3 + "Email Error message is verified");
			} else {
				InitialDriver.logger.log(Status.FAIL,"Email Error message is not verified");
			}
			Thread.sleep(1000);

			WebElement errorMsg4 = driver.findElement(By.xpath(".//[@id='signup-form-error-message-4']"));
			String actual4 = errorMsg4.getText();
			if (actual4.contains("Phone number")) {
				InitialDriver.logger.log(Status.PASS,actual4 + "Phone Number Error message is verified");
			} else {
				InitialDriver.logger.log(Status.FAIL,"Phone Number Error message is not verified");
			}
			Thread.sleep(1000);

			WebElement emailAddress = driver
					.findElement(By.xpath("html/body/div[6]/main/div[1]/div/div/form/div[4]/label/input"));
			if (enterText(emailAddress, "apple", "Email Address")) {
				String actualMsg = driver.findElement(By.xpath(".//[@id='signup-form-error-message-3']")).getText();
				if (actualMsg.contains("Email address is invalid")) {
					status = "pass";
					InitialDriver.logger.log(Status.PASS,actualMsg + " Email addressError message is verified");
				} else {
					InitialDriver.logger.log(Status.FAIL,"email address Error message is not verified");
				}
				Thread.sleep(1000);

				// WebElement checkbox =
				// driver.findElement(By.xpath("html/body/div[6]/main/div[1]/div/div/form/div[8]/div"));
				// checkbox.isEnabled();
			}
		}
		exitBrowser();
		writeDataInXl(i, mapToBrowser(browserName), status);
		setXlColorStyle(i, mapToBrowser(browserName), status);
	}

	public void SignUpToXDC_TC02_C(String UserName, String Password, String URL, String browserName, int i)
			throws InterruptedException {
		String status = "fail";
		launchBrowser(browserName, URL);

		freeTrialPage();

		WebElement termsOfUse = driver
				.findElement(By.xpath("/html/body/div[6]/main/div[1]/div/div/form/div[8]/div/label/a[1]"));
		if (clickObj(termsOfUse, "Terms Of Use")) {
			Thread.sleep(3000);
			String parentWindow = driver.getWindowHandle();
			Set<String> getAllWindows = driver.getWindowHandles();
			int count = getAllWindows.size();
			for (String child : getAllWindows) {
				if (!parentWindow.equalsIgnoreCase(child)) {
					driver.switchTo().window(child);
					System.out.println("child window titile is :" + driver.getCurrentUrl());
				}
			}
			driver.switchTo().window(parentWindow);
			Thread.sleep(2000);
			System.out.println("parent window title is :" + driver.getTitle());
			Thread.sleep(2000);

			WebElement policyLink = driver
					.findElement(By.xpath("html/body/div[6]/main/div[1]/div/div/form/div[8]/div/label/a[2]"));
			if (clickObj(policyLink, "Privacy Policy")) {
				Thread.sleep(3000);
				String parentWindow1 = driver.getWindowHandle();
				Set<String> getAllWindows1 = driver.getWindowHandles();
				int count1 = getAllWindows1.size();
				for (String child1 : getAllWindows1) {
					if (!parentWindow1.equalsIgnoreCase(child1)) {
						driver.switchTo().window(child1);
						status = "pass";
						System.out.println("Child1 window title is:" + driver.getCurrentUrl());
					}
				}
				driver.switchTo().window(parentWindow1);

			}
			exitBrowser();
			writeDataInXl(i, mapToBrowser(browserName), status);
			setXlColorStyle(i, mapToBrowser(browserName), status);

		}
	}

	public void SignUpToXDC_TC02_D(String UserName, String Password, String URL, String browserName, int i)
			throws InterruptedException {
		String status = "fail";
		launchBrowser(browserName, URL);

		freeTrialPage();

		WebElement offerDetails = driver
				.findElement(By.xpath("html/body/div[6]/main/div[1]/div/div/form/div[8]/div/label/a[3]"));
		if (clickObj(offerDetails, "Offer Details")) {
			Thread.sleep(3000);
			String parentWindow = driver.getWindowHandle();
			Set<String> getAllWindows = driver.getWindowHandles();
			int count = getAllWindows.size();
			for (String child : getAllWindows) {
				if (!parentWindow.equalsIgnoreCase(child)) {
					driver.switchTo().window(child);
					status = "pass";
					System.out.println("Child Window title is:" + driver.getCurrentUrl());
				}
			}
			driver.switchTo().window(parentWindow);
		}
		exitBrowser();
		writeDataInXl(i, mapToBrowser(browserName), status);
		setXlColorStyle(i, mapToBrowser(browserName), status);

	}

	public void SignUpToXDC_TC02_E(String UserName, String Password, String URL, String browserName, int i)
			throws InterruptedException {
		String status = "fail";
		launchBrowser(browserName, URL);

		freeTrialPage();

		WebElement clickAccount = driver.findElement(By.xpath("html/body/div[6]/main/div[2]/div/div/div/p/a"));
		if (clickObj(clickAccount, "Click Account")) {
			Thread.sleep(2000);

			String actualMsg = driver.findElement(By.xpath("html/body/div[6]/main/div/div[1]/div/div/h2")).getText();

			if (actualMsg.contains("Let’s get started")) {
				status = "pass";
				InitialDriver.logger.log(Status.PASS,actualMsg + " Error message is verified");
			} else {
				InitialDriver.logger.log(Status.FAIL,"  message is not verified");
			}
			String actualURL = driver.getCurrentUrl();
			System.out.println(actualURL);

			String actualTitle = driver.getTitle();
			System.out.println(actualTitle);

		}
		exitBrowser();
		writeDataInXl(i, mapToBrowser(browserName), status);
		setXlColorStyle(i, mapToBrowser(browserName), status);
	}

	
	public void TestAllTabsPage_TC03_A(String UserName, String Password, String URL, String browserName, int i) {
		String status = "fail";
		try {
			launchBrowser(browserName, URL);

			Thread.sleep(2000);

			WebElement username = driver.findElement(By.xpath(".//[@id='email']"));
			if (enterText(username, UserName, "Username")) {

				Thread.sleep(1000);

				WebElement password = driver.findElement(By.xpath(".//[@id='password']"));
				if (enterText(password, Password, "Password")) {

					Thread.sleep(1000);

					WebElement login = driver.findElement(By.xpath(".//[@id='submitButton']"));
					if (clickObj(login, "Login")) {

						Thread.sleep(2000);

						String trial = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[1]/p")).getText();
						String expect = "You are currently using a trial account.";
						if (trial.contains(expect)) {
							InitialDriver.logger.log(Status.PASS, trial + "  message is verified");

							WebElement dashboard = driver.findElement(By.xpath("//[@id=\"Dashboard\"]"));
							if (clickObj(dashboard, "Dashboard")) {

								Thread.sleep(1000);
								String DashboardPage = driver.getTitle();
								String expected = "Dashboard";
								if (DashboardPage.contains(expected)) {
									InitialDriver.logger.log(Status.PASS,DashboardPage + "  message is verified");

									WebElement account = driver.findElement(By.xpath("//[@id=\"Accounts\"]"));
									if (clickObj(account, "Accounts")) {

										Thread.sleep(1000);

										WebElement report = driver.findElement(By.xpath("//[@id=\"Reports\"]"));
										if (clickObj(report, "Reports")) {

											Thread.sleep(1000);

											WebElement contact = driver.findElement(By.xpath("//[@id=\"Contacts\"]"));
											if (clickObj(contact, "Contacts")) {

												Thread.sleep(1000);
												WebElement setting = driver
														.findElement(By.xpath("//[@id=\"Settings\"]"));
												if (clickObj(setting, "Settings")) {

													Thread.sleep(1000);

													WebElement plus = driver
															.findElement(By.xpath("//[@id=\"quicklaunchTab\"]"));
													if (clickObj(plus, "+/New")) {

														Thread.sleep(1000);

														WebElement file = driver.findElement(By.xpath(
																"/html/body/div[1]/div/div/div[2]/div[2]/div[2]/ul/li[2]/a"));
														if (clickObj(file, "File")) {

															Thread.sleep(1000);

															WebElement notification = driver.findElement(By.xpath(
																	"/html/body/div[1]/div/div/div[2]/div[2]/div[2]/ul/li[3]/a"));
															if (clickObj(notification, "notification")) {

																Thread.sleep(2000);

																WebElement Search = driver.findElement(By.xpath(
																		"/html/body/div[1]/div/div/div[2]/div[2]/div[2]/ul/li[4]/a"));
																if (clickObj(Search, "Search")) {

																	Thread.sleep(3000);

																	WebElement Searchbar = driver.findElement(
																			By.xpath(".//[@id='placeholder']"));
																	if (clickObj(Searchbar, "Searchbar")) {

																		Thread.sleep(2000);

																		WebElement help = driver.findElement(By.xpath(
																				"/html/body/div[1]/div/div/div[2]/div[2]/div[2]/ul/li[5]/a"));
																		if (clickObj(help, "Help")) {

																			WebElement helpfield = driver.findElement(
																					By.xpath("//*[@id=\"menu_help\"]"));
																			if (helpfield.isDisplayed()) {
																				System.out.println(
																						"Pass: help field is displayed");

																				WebElement helpcenter = driver
																						.findElement(By.xpath(
																								"/html/body/div[1]/div/div/div[2]/div[2]/div[2]/ul/li[5]/div/div/div/ul/li[1]/a]"));
																				if (helpcenter.isDisplayed()) {
																					System.out.println(
																							"Pass: help center is displayed");

																					WebElement getHelp = driver
																							.findElement(By.xpath(
																									"//[@id=\"get_help\"]]"));
																					if (getHelp.isDisplayed()) {
																						System.out.println(
																								"Pass: get Help is displayed");

																						WebElement advisor = driver
																								.findElement(By.xpath(
																										"//[@id=\"get_help\"]]"));
																						if (advisor.isDisplayed()) {
																							System.out.println(
																									"Pass: advisor is displayed");
																							status = "pass";
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Unable to locate element");
		}

		exitBrowser();
		writeDataInXl(i, mapToBrowser(browserName), status);
		setXlColorStyle(i, mapToBrowser(browserName), status);
	}
	 
	 
	public void TestLogoutFunctionality_TC04_A(String UserName, String Password, String URL, String browserName, int i)
			throws InterruptedException {
		String status = "fail";
		launchBrowser(browserName, URL);

		WebElement username = driver.findElement(By.xpath(".//[@id='email']"));
		if (enterText(username, UserName, "Username")) {

			Thread.sleep(1000);

			WebElement password = driver.findElement(By.xpath(".//[@id='password']"));
			if (enterText(password, Password, "Password")) {

				Thread.sleep(1000);

				WebElement login = driver.findElement(By.xpath(".//[@id='submitButton']"));
				if (clickObj(login, "Login")) {

					Thread.sleep(2000);

					WebElement userMenu = driver
							.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[1]/div[2]/a"));
					if (clickObj(userMenu, "User Menu Drop Down")) {

						Thread.sleep(2000);

						WebElement logout = driver
								.findElement(By.xpath(".//[@id='xero-nav']/div[2]/div[1]/div[2]/div/ul/li[3]/a"));
						if (clickObj(logout, "Logout")) {

							Thread.sleep(2000);

							String logoutMessage = driver
									.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/h2")).getText();
							String exp = "Welcome to Xero";
							if (logoutMessage.equals(exp)) {
								status = "pass";
								InitialDriver.logger.log(Status.PASS,logoutMessage + "  message is verified");

							} else {
								InitialDriver.logger.log(Status.FAIL,logoutMessage + " Error message is not verified");
							}
						}
					}
				}
			}
		}

		exitBrowser();

		writeDataInXl(i, mapToBrowser(browserName), status);
		setXlColorStyle(i, mapToBrowser(browserName), status);
	}

	public void TestUploadProfileImage_TC06_A(String UserName, String Password, String URL, String browserName, int i)
			throws InterruptedException {
		String status = "fail";
	try{
		launchBrowser(browserName, URL);
		Thread.sleep(2000);
		LoginPage(UserName, Password);
		Thread.sleep(4000);
		
			WebElement user = driver.findElement(By.xpath(".//*[@id='xero-nav']/div/div[1]/div[2]/a"));
			if (clickObj(user, "User")) {
				Thread.sleep(2000);
				WebElement profile = driver
						.findElement(By.xpath(".//*[@id='xero-nav']/div/div[1]/div[2]/div/ul/li[1]/a"));
				if (clickObj(profile, "Profile")) {
					Thread.sleep(2000);
					String actualTitle = driver.getTitle();
					if (actualTitle.contains("Profile Settings")) {
						InitialDriver.logger.log(Status.PASS,actualTitle + "  message is verified");
					} else {
						InitialDriver.logger.log(Status.FAIL,"page is not verified");
					}
					Thread.sleep(2000);
					WebElement uploadImage = driver.findElement(By.xpath(".//*[@id='button-1041']"));
					if (clickObj(uploadImage, "Upload Image")) {
						Thread.sleep(2000);
						
						Thread.sleep(3000);

						WebElement browse = driver
								.findElement(By.xpath("//*[@class=\"x-form-file-input\"]"));
						browse.sendKeys("C:\\Users\\KANISHKA\\Downloads\\Ava-Cake-Smash.JPG");
						
	
							Thread.sleep(5000);

							WebElement upload = driver.findElement(By.xpath("//*[@class=\"x-btn-button\"]/span[text() = \"Upload\"]"));
							if (clickObj(upload, "Upload")) {
								Thread.sleep(2000);
								status = "pass";
							}

						} else {
							InitialDriver.logger.log(Status.FAIL,"The image is not uploaded");
						}

					}
				}
			
		
		
		
		}catch (Exception e) {
			InitialDriver.logger.log(Status.INFO,"Unable to thread sleep");
		}
		
		

		exitBrowser();
		writeDataInXl(i, mapToBrowser(browserName), status);
		setXlColorStyle(i, mapToBrowser(browserName), status);
	}
	

	public void AddOrganisationTrailVersion_TC08_A(String UserName, String Password, String URL, String browserName,
			int i) throws InterruptedException {
		String status = "fail";
		launchBrowser(browserName, URL);

		Thread.sleep(1000);

		LoginPage(UserName, Password);
		Thread.sleep(4000);
		AddAnOrganisation();
		WebElement startTrial = driver.findElement(By.xpath(".//[@id='simplebutton-1035']"));
		if (clickObj(startTrial, "Start Trial")) {
			status = "pass";
		}

		exitBrowser();
		writeDataInXl(i, mapToBrowser(browserName), status);
		setXlColorStyle(i, mapToBrowser(browserName), status);
	}

	public void AddOrganisationPaidVersion_TC08_B(String UserName, String Password, String URL, String browserName,
			int i) throws InterruptedException {
		String status = "fail";
		launchBrowser(browserName, URL);

		Thread.sleep(1000);

		LoginPage(UserName, Password);
		Thread.sleep(4000);
		AddAnOrganisation();
		clickBuyNow();
		exitBrowser();
		writeDataInXl(i, mapToBrowser(browserName), status);
		setXlColorStyle(i, mapToBrowser(browserName), status);
	}

	public void AddOrganisationStarterPlan_TC08_C(String UserName, String Password, String URL, String browserName,
			int i) {
		String status = "fail";
		try {
			System.out.println("pass");
			launchBrowser(browserName, URL);

			Thread.sleep(1000);

			LoginPage(UserName, Password);
			Thread.sleep(4000);
			AddAnOrganisation();
			clickBuyNow();

			WebElement starterPlan = driver.findElement(By.xpath(".//[@id='PRODUCTOPTION/ORG/SOLO']/div[1]/label"));
			if (clickObj(starterPlan, "Starter Plan")) {
				status="pass";
				Thread.sleep(2000);

				testData();

				exitBrowser();
				writeDataInXl(i, mapToBrowser(browserName), status);
				setXlColorStyle(i, mapToBrowser(browserName), status);
			}
		} catch (Exception e) {
			InitialDriver.logger.log(Status.INFO,"Unable to thread sleep");
		}

		exitBrowser();
		writeDataInXl(i, mapToBrowser(browserName), status);
		setXlColorStyle(i, mapToBrowser(browserName), status);
	}

	public void AddOrganisationStandardPlan_TC08_D(String UserName, String Password, String URL, String browserName,
			int i) {
		String status = "fail";
		try {
			launchBrowser(browserName, URL);

			Thread.sleep(1000);

			LoginPage(UserName, Password);
			Thread.sleep(4000);
			AddAnOrganisation();
			clickBuyNow();

			WebElement standardPlan = driver
					.findElement(By.xpath(".//[@id='PRODUCTOPTION/ORG/STANDARD']/div[1]/label"));
			if (clickObj(standardPlan, " Standard Plan")) {
				Thread.sleep(2000);

				testData();

			}
			status = "pass";

		} catch (Exception e) {
			System.out.println("unable to thread");
		}

			exitBrowser();
			writeDataInXl(i, mapToBrowser(browserName), status);
			setXlColorStyle(i, mapToBrowser(browserName), status);
		}
	

	public void AddOrganisationPremiunPlan_TC08_E(String UserName, String Password, String URL, String browserName,
			int i) {
		String status = "fail";
		try {
			launchBrowser(browserName, URL);

			Thread.sleep(1000);

			LoginPage(UserName, Password);
			Thread.sleep(4000);
			AddAnOrganisation();
			clickBuyNow();

			WebElement premium = driver.findElement(By.xpath(".//[@id='PRODUCTOPTION/ORG/PRO']/div[1]/label"));
			if (clickObj(premium, "Premium")) {

				Thread.sleep(2000);

				testData();
				status = "pass";

			}

		} catch (Exception e) {
			System.out.println("unable to thread");
		}

			exitBrowser();
			writeDataInXl(i, mapToBrowser(browserName), status);
			setXlColorStyle(i, mapToBrowser(browserName), status);
		}
	public void AddOrganisationQuickBookUser_TC08_F(String UserName, String Password, String URL, String browserName,int i){
		String status = "fail";
		try {
			launchBrowser(browserName, URL);

			Thread.sleep(1000);

			LoginPage(UserName, Password);
			Thread.sleep(4000);
			AddAnOrganisation();
			
			WebElement convertData = driver.findElement(By.xpath(".//[@id='conversionLink']"));
			if(clickObj(convertData," Convert Data")){
				Thread.sleep(2000);
				
				WebElement checkbox = driver.findElement(By.xpath(".//[@id='conversionCheckbox-inputEl']"));
				if(selectCheckBox(checkbox,"Want to convert Data")){
					
					Thread.sleep(2000);
					
					clickBuyNow();
					
					Thread.sleep(4000);
					WebElement text = driver.findElement(By.xpath(".//[@id='tbtext-1045']"))	;
					String actualText = text.getText();
					if(actualText.contains("QuickBooks file conversion")){
						InitialDriver.logger.log(Status.PASS,actualText + "  message is verified");
					status="pass";
					}
					else{
						InitialDriver.logger.log(Status.FAIL,"text is not verified");
					}
				}
			}
		}
				
	catch(Exception e){
		InitialDriver.logger.log(Status.INFO,"Unable to thread sleep");
	}
		exitBrowser();
		writeDataInXl(i, mapToBrowser(browserName), status);
		setXlColorStyle(i, mapToBrowser(browserName), status);
		
	}

	public void NavigateToTestId_TC10_A(String UserName, String Password, String URL, String browserName,int i)throws InterruptedException {
		String status = "fail";
	
		try {
			
		
			launchBrowser(browserName, URL);
				
			Thread.sleep(2000);

			LoginPage(UserName, Password);

			String DashboardPage = driver.getTitle();
			
			if (DashboardPage.contains("Dashboard")) {
				InitialDriver.logger.log(Status.PASS,DashboardPage + "  message is verified");
				Thread.sleep(2000);
				
				WebElement myXero = driver.findElement(By.xpath(".//*[@id='xero-nav']/div[2]/div[1]/div[1]/div/h2"));
				if (clickObj(myXero, "My Xero")) {
					Thread.sleep(2000);
					
					WebElement tekarch = driver.findElement(By.xpath(".//*[@id='xero-nav']/div/div[1]/div[1]/div/div/ul/li/a"));
					if(clickObj(tekarch,"Tekarch")){
				Thread.sleep(2000);
				
			WebElement Account = driver.findElement(By.xpath("//*[@id=\"Accounts\"]"));
			if (clickObj(Account, "Accounts")){
			
			WebElement purchases = driver.findElement(By.xpath(".//*[@id='xero-nav']/div[2]/div[2]/div[1]/ul/li[2]/ul/li[3]/a"));
			if(clickObj(purchases,"Purchases")){
				
			
			String PurchasesPage = driver.getTitle();
		
			if (PurchasesPage.contains("Purchases")) {
				InitialDriver.logger.log(Status.PASS,PurchasesPage + "  message is verified");
				status="Pass";
			}
				else{
					InitialDriver.logger.log(Status.FAIL,PurchasesPage +  " page is not verified");
					
				}
			}
			}
		
		}
				}
			}
		}
			
catch(Exception e){
	InitialDriver.logger.log(Status.INFO,"Unable to thread sleep");
}
	
		exitBrowser();
		writeDataInXl(i, mapToBrowser(browserName), status);
		setXlColorStyle(i, mapToBrowser(browserName), status);
	}
}

		
	

