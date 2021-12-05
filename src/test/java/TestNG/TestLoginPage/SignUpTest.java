package TestNG.TestLoginPage;

import TestNG.BaseTestEx;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Description;
import models.components.BottomNavBarComponent;
import models.components.login_page_component.DialogComponent;
import models.components.login_page_component.SignUpFormComponent;
import models.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.*;

public class SignUpTest extends BaseTestEx {

    LoginPage loginPage;
    SignUpFormComponent signUpFormComp;
    DialogComponent dialogComp;
    BottomNavBarComponent bottomNavBarComp;
    AppiumDriver<MobileElement> appiumDriver;

    @BeforeClass
    public void beforeClass() {
        appiumDriver = getDriver();
        loginPage = new LoginPage(appiumDriver);
        signUpFormComp = loginPage.signUpFormComponent();
    }

    @AfterClass
    public void afterClass() { }

    @BeforeMethod
    public void beforeMethod() {
        bottomNavBarComp = loginPage.bottomNavBarComponent();
        bottomNavBarComp.clickOnLoginLabel();
    }

    @AfterMethod
    public void afterMethod() {
        appiumDriver.closeApp();
        appiumDriver.launchApp();
    }

    @Description("Verify Signup with valid credentials")
    @Test
    public void signUpWithValidCreds() {
        softAssert.assertTrue(loginPage.isSignUpFormSelect());

        dialogComp = signUpFormComp.inputEmailField(validLoginData.getJSONObject("validCredentials").getString("email"))
                                   .inputPasswordField(validLoginData.getJSONObject("validCredentials").getString("password"))
                                   .inputRepeatPasswordField(validLoginData.getJSONObject("validCredentials").getString("password"))
                                   .clickOnSignUpBtn();

        Assert.assertTrue(dialogComp.isDialogTemplateDisplay());

        String actualDialogTitle = dialogComp.dialogTitleElem().getText();
        String actualDialogMessage = dialogComp.dialogMessageElem().getText();
        String expectedDialogTitle = expectedStringMap.get("success_sign_up_dialog_title");
        String expectedDialogMessage = expectedStringMap.get("success_sign_up_dialog_msg");

        softAssert.assertEquals(actualDialogTitle, expectedDialogTitle);
        softAssert.assertEquals(actualDialogMessage, expectedDialogMessage);
        softAssert.assertAll();

        dialogComp.clickDialogBtn();
    }

    @Description("Verify Signup with invalid email")
    @Test
    public void signUpWithInvalidEmail() {
        softAssert.assertTrue(loginPage.isSignUpFormSelect());

        dialogComp = signUpFormComp.inputEmailField(validLoginData.getJSONObject("invalidCredentials").getString("email"))
                                   .inputPasswordField(validLoginData.getJSONObject("validCredentials").getString("password"))
                                   .inputRepeatPasswordField(validLoginData.getJSONObject("validCredentials").getString("password"))
                                   .clickOnSignUpBtn();

        String actualWrongEmailMessage = signUpFormComp.wrongEmailTextElem().getText();
        String expectedWrongEmailMessage = expectedStringMap.get("error_login_invalid_email_msg");

        softAssert.assertEquals(actualWrongEmailMessage, expectedWrongEmailMessage);
        softAssert.assertAll();
    }

    @Description("Verify Signup with invalid password")
    @Test
    public void signUpWithInvalidPassword() {
        softAssert.assertTrue(loginPage.isSignUpFormSelect());

        dialogComp = signUpFormComp.inputEmailField(validLoginData.getJSONObject("validCredentials").getString("email"))
                                   .inputPasswordField(validLoginData.getJSONObject("invalidCredentials").getString("password"))
                                   .inputRepeatPasswordField(validLoginData.getJSONObject("validCredentials").getString("password"))
                                   .clickOnSignUpBtn();

        String actualWrongPwMessage = signUpFormComp.wrongPasswordTextElem().getText();
        String actualWrongRepeatPwMessage = signUpFormComp.errRepeatPwMessageElem().getText();
        String expectedWrongPwMessage = expectedStringMap.get("error_sign_up_invalid_password_msg");
        String expectedWrongRepeatPwMessage = expectedStringMap.get("error_sign_up_wrong_repeat_password_msg");

        Assert.assertEquals(actualWrongPwMessage, expectedWrongPwMessage);
        Assert.assertEquals(actualWrongRepeatPwMessage, expectedWrongRepeatPwMessage);
        softAssert.assertAll();
    }

}
