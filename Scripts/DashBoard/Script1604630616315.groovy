import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
//import com.kms.katalon.core.util.keywordUtil
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil





WebUI.openBrowser("https://opensource-demo.orangehrmlive.com/index.php/auth/validateCredentials")

WebUI.maximizeWindow();

if(CustomKeywords.'Test.verifyScreen'(findTestObject('Object Repository/Object_validator/label_login'))){
	KeywordUtil.markPassed("Login successfully")
	
	WebUI.callTestCase(findTestCase("Login"), [:] ,FailureHandling.OPTIONAL)
	
	if(CustomKeywords.'Test.verifyScreen'(findTestObject('Object Repository/Object_validator/h1_dashboard'))){
		KeywordUtil.markPassed("Dashboard successfully")
		
		CustomKeywords.'Test.dashboard'(findTestObject('Object Repository/Page_DashBoard/b_Admin'),
			findTestObject('Object Repository/Page_Add/input_Status_btnAdd'))
		
		if(CustomKeywords.'Test.verifyScreen'(findTestObject('Object Repository/Object_validator/h1_AddUser'))){
			KeywordUtil.markPassed("Add User successfully")
			
				CustomKeywords.'Test.Form'(findTestObject('Object Repository/Page_Add/select_AdminESS'),
					findTestObject('Object Repository/Page_Add/input__systemUseremployeeNameempName'),
					findTestObject('Object Repository/Page_Add/input__systemUseruserName'),
					findTestObject('Object Repository/Page_OrangeHRM/select_EnabledDisabled'),
					findTestObject('Object Repository/Page_Add/input__systemUserpassword'),
					findTestObject('Object Repository/Page_Add/input__systemUserconfirmPassword'),
					findTestObject('Object Repository/Page_Add/input__btnSave'))
			
				if(CustomKeywords.'Test.verifyScreen'(findTestObject('Object Repository/Object_validator/a_usermanager'))){
					KeywordUtil.markPassed("Delete successfully")
					
					CustomKeywords.'Test.deleteUser'(findTestObject('Object Repository/Page_OrangeHRM/input_Username_searchSystemUseruserName'), 
						findTestObject('Object Repository/Page_OrangeHRM/select_AllAdminESS'), 
						findTestObject('Object Repository/Page_OrangeHRM/input_Employee Name_searchSystemUseremploye_55f714'), 
						findTestObject('Object Repository/Page_OrangeHRM/select_AllEnabledDisabled'), 
						findTestObject('Object Repository/Page_OrangeHRM/input_Status__search'), 
						findTestObject('Object Repository/Page_Delete/input_Status_chkSelectAll'), 
						findTestObject('Object Repository/Page_Delete/input_Status_btnDelete'), 
						findTestObject('Object Repository/Page_Delete/input_confirmacionDelete'))
					
				} else {
//					System.out.print("No se valido pantalla Delete");
					KeywordUtil.markFailedAndStop("No se valido pantalla Delete")
				}
			
		} else {
//			System.out.print("No se valido pantalla Add User");
			KeywordUtil.markFailedAndStop("No se valido pantalla Add User")
		}
		
	}else{
//		System.out.print("No se valido pantalla Dashboard");
		KeywordUtil.markFailedAndStop("No se valido pantalla Dashboard")
	}
}else {
//	System.out.print("No se valido pantalla Login");
	KeywordUtil.markFailedAndStop("No se valido pantalla Login")
}


WebUI.delay(3)
WebUI.closeBrowser();
