package TestNG.TestLoginPage;

import models.base.BaseTest;
import models.components.BottomNavBarComponent;
import models.components.login_page_component.DialogComponent;
import models.components.login_page_component.SignUpFormComponent;
import models.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.*;

public class SignUpTest extends BaseTest {

    LoginPage loginPage;
    SignUpFormComponent signUpFormComp;
    DialogComponent dialogComp;
    BottomNavBarComponent bottomNavBarComp;

    @BeforeClass
    public void beforeClass() {
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

    @Test
    public void signUpWithValidCreds() {
        softAssert.assertTrue(loginPage.isSignUpFormSelect());

        dialogComp = signUpFormComp.inputEmailField(loginData.getJSONObject("validCredentials").getString("email"))
                                   .inputPasswordField(loginData.getJSONObject("validCredentials").getString("password"))
                                   .inputRepeatPasswordField(loginData.getJSONObject("validCredentials").getString("password"))
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

    @Test
    public void signUpWithInvalidEmail() {
        softAssert.assertTrue(loginPage.isSignUpFormSelect());

        dialogComp = signUpFormComp.inputEmailField(loginData.getJSONObject("invalidCredentials").getString("email"))
                                   .inputPasswordField(loginData.getJSONObject("validCredentials").getString("password"))
                                   .inputRepeatPasswordField(loginData.getJSONObject("validCredentials").getString("password"))
                                   .clickOnSignUpBtn();

        String actualWrongEmailMessage = signUpFormComp.wrongEmailTextElem().getText();
        String expectedWrongEmailMessage = expectedStringMap.get("error_login_invalid_email_msg");

        softAssert.assertEquals(actualWrongEmailMessage, expectedWrongEmailMessage);
        softAssert.assertAll();
    }

    @Test
    public void signUpWithInvalidPassword() {
        softAssert.assertTrue(loginPage.isSignUpFormSelect());

        dialogComp = signUpFormComp.inputEmailField(loginData.getJSONObject("validCredentials").getString("email"))
                                   .inputPasswordField(loginData.getJSONObject("invalidCredentials").getString("password"))
                                   .inputRepeatPasswordField(loginData.getJSONObject("validCredentials").getString("password"))
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
