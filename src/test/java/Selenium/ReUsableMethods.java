package Selenium;

import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;






public class ReUsableMethods  {
	//ModulesXero modules = new ModulesXero();
	public static boolean enterText(WebElement obj, String textValue, String objName) {
		if(obj.isDisplayed()){
			obj.sendKeys(textValue);
			//System.out.println("Pass: " + textValue + " is entered in " + objName + " the field");
			InitialDriver.logger.log(Status.PASS, textValue + " is entered in " +objName + " field"  );
			return true;
		}
		else{
			//System.out.println("Fail: " + objName + "field is not displayed");
			InitialDriver.logger.log(Status.FAIL, objName + " field is not displayed ");
			return false;
		}
	}
	public static boolean clickObj(WebElement obj, String objName){
		if(obj.isDisplayed()){
			obj.click();
			//System.out.println("Pass: " + objName + " is clicked");
			InitialDriver.logger.log(Status.PASS, objName + " field is clicked ");
			return true;
		}
		else{
			//System.out.println("Fail: " + objName + "field is not displayed");
			InitialDriver.logger.log(Status.FAIL, objName + " field is not displayed");
			return false;
		}
	}
	public static boolean clear(WebElement obj,String objName){
		if(obj.isDisplayed()){
			obj.clear();
			//System.out.println("Pass: " + objName +" is displayed and  field  is cleared");
			InitialDriver.logger.log(Status.PASS,objName +" field is cleared ");
			return true;
		}
		else{
			//System.out.println("Fail: " + objName + "field is not displayed");
			InitialDriver.logger.log(Status.FAIL,objName+ "is not displayed ");
			return false;
		}
	}
	public static boolean selectCheckBox(WebElement obj,String objName){
		if(obj.isDisplayed()){
			if(obj.isSelected()){
				//System.out.println("Pass:" +objName + " is already selected ");
				InitialDriver.logger.log(Status.PASS,objName +" is already selected ");
				return true;
			}
			else{
				obj.click();
				//System.out.println("Pass:" +objName + " is selected ");
				InitialDriver.logger.log(Status.PASS,objName + "field is selected ");
				return true;
			}
		}
			else{
				//System.out.println("Fail: " + objName + "field is not displayed");
				InitialDriver.logger.log(Status.FAIL,objName+ "is not displayed ");
				return false;
			}
		}
	public static boolean validateContent(WebElement obj, String expectedText,String objName){
		if(obj.isDisplayed()){
			String actualText=obj.getText();
			if(expectedText.equals(actualText)){
				//System.out.println("Pass: " + " Expected text '" + expectedText + "' is matching with actual text: '" + actualText + "'.");
				InitialDriver.logger.log(Status.PASS," Expected Text" +expectedText + " is matching with" +actualText);
				return true;
			}
			else{
				//System.out.println("Fail: " + objName + "field is not displayed");
				InitialDriver.logger.log(Status.FAIL," Expected Text" +expectedText+ " is not matching with" +actualText);
				return false;
			}
		}
		else{
			//System.out.println("Fail: " + objName + "field is not displayed");
		    InitialDriver.logger.log(Status.FAIL,objName+ " is not displayed ");
			return false;
		}
	}
}


