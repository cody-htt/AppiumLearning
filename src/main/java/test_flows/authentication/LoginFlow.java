package test_flows.authentication;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import models.components.BottomNavBarComponent;
import models.components.login_page_component.DialogComponent;
import models.components.login_page_component.LoginFormComponent;
import models.pages.LoginPage;
import org.testng.asserts.SoftAssert;
import test_data.LoginCreds;
import utils.TestUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class LoginFlow {

    private AppiumDriver<MobileElement> appiumDriver;
    private LoginPage loginPage;
    private HashMap<String, String> expectedStringMap;
    private SoftAssert softAssert;

    public LoginFlow(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
        softAssert = new SoftAssert();
        getExpectedStringMap();
    }

    public LoginFlow navigateToLoginPage() {
        if (loginPage == null) { initLoginPage(); }
        // Init Bottom Nav Comp and Navigate to Login Page
        BottomNavBarComponent bottomNavBarComp = loginPage.bottomNavBarComponent();
        bottomNavBarComp.clickOnLoginLabel();
        return this;
    }

    public LoginFlow initLoginPage() {
        loginPage = new LoginPage(appiumDriver);
        return this;
    }

    @Step("Input email as {loginCreds.email} and password as {loginCreds.password}")
    public LoginFlow login(LoginCreds loginCreds) {
        if (loginPage == null) { throw new RuntimeException("Please use navigateToLoginPage() first!!!"); }
        // Fill Login form
        LoginFormComponent loginFormComp = loginPage.loginFormComponent();
        loginFormComp.inputEmailField(loginCreds.getEmail())
                     .inputPasswordField(loginCreds.getPassword())
                     .clickOnLoginBtn();
        return this;
    }

    public LoginFlow verifyLoginWithCorrectCreds() {
        // Init Dialog Component and verification
        DialogComponent dialogComp = loginPage.loginFormComponent().clickOnLoginBtn();
        String actualDialogTitle = dialogComp.dialogTitleElem().getText();
        String actualDialogMessage = dialogComp.dialogMessageElem().getText();
        String expectedDialogTitle = expectedStringMap.get("success_login_dialog_title");
        String expectedDialogMessage = expectedStringMap.get("success_login_dialog_msg");

        softAssert.assertEquals(actualDialogTitle, expectedDialogTitle);
        softAssert.assertEquals(actualDialogMessage, expectedDialogMessage);
        softAssert.assertAll();
        return this;
    }

    public void verifyLoginWithIncorrectCreds() {

    }

    private void getExpectedStringMap() {
        TestUtils testUtils = new TestUtils();
        // Initialize HashMap expectedStringMap basing on staticStrings.xml file
        String xmlFileName = "static-string/authentication/ExpectedText.xml";
        InputStream isStringMap = getClass().getClassLoader().getResourceAsStream(xmlFileName);
        expectedStringMap = testUtils.xmlStringParser(isStringMap);
        try {
            if (isStringMap != null) { isStringMap.close(); }
        } catch (IOException ex) { ex.printStackTrace(); }
    }
}
