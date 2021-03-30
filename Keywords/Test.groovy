import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.util.ArrayList

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class Test {

	StringBuffer cadena = new StringBuffer();
	ArrayList<String> password = new ArrayList<String>();
	int numero;
	String captura;


	@Keyword
	def random(){
		if(captura == null){
			for(int i=0; i<=7; i++) {
				numero = (int)(Math. random()*9+1) //String.valueOf(numEntero)
				password.add(numero);
				cadena = cadena.append(password[i]);
				captura = cadena.toString();
			}
		}
		return captura;
	}

	@Keyword
	def verifyScreen(object){
		def vf = WebUI.verifyElementPresent(object, 20)
		if(vf ==  true){
			return true;
		} else {
			return false
		}
	}

	@Keyword
	def text(def object, def text){
		if(WebUI.verifyElementPresent(object, 20)){
			WebUI.setText(object, text)
		}
	}

	@Keyword
	def Login(def username, def pass, def submit){
		def Uname = findTestData("Orangedb").getObjectValue("Username", 1)
		def Pass = findTestData("Orangedb").getObjectValue("Password", 1)
		text(username, Uname)
		text(pass, Pass)
		takescreenshott();
		WebUI.click(submit)
	}

	@Keyword
	def Form(def UserRole, def EmpName, def UserN, def status, def pass, def confPass, def submit){

		def contrasena = CustomKeywords.'Test.random'();

		WebUI.selectOptionByValue(UserRole, '1', true)
		text(EmpName, findTestData("Orangedb").getValue("EmpName", 1))
		text(UserN, findTestData("Orangedb").getValue("UserN", 1))
		WebUI.selectOptionByValue(status, '1', true)

		text(pass, contrasena)
		text(confPass, contrasena)

		takescreenshott();
		WebUI.click(submit)
	}

	@Keyword
	def dashboard(def a_userAdmin, def btn_add){
		WebUI.click(a_userAdmin)
		takescreenshott()
		takescreenshott();
		WebUI.click(btn_add)
	}

	@Keyword
	def deleteUser(def userN, def userR, def employeeN, def status, def search, def check, def btnDelet, def confDelet){

		text(userN,  findTestData("Orangedb").getValue("UserN", 1))

		WebUI.selectOptionByValue(userR,  findTestData("Orangedb").getValue("UserRole", 1), true)

		text(employeeN, findTestData("Orangedb").getValue("EmpName", 1))

		WebUI.selectOptionByValue(status,  findTestData("Orangedb").getValue("Status", 1), true)


		WebUI.click(search)

		WebUI.click(check)

		WebUI.click(btnDelet)

		takescreenshott()
		WebUI.click(confDelet)
	}

	@Keyword
	def takescreenshott(){
		WebUI.takeScreenshot();
	}
}
