package TestNG.TestLoginPage;

import models.base.BaseTest;
import models.components.BottomNavBarComponent;
import models.components.login_page_component.DialogComponent;
import models.components.login_page_component.LoginFormComponent;
import models.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest extends BaseTest {

    LoginPage loginPage;
    LoginFormComponent loginFormComp;
    DialogComponent dialogComp;
    BottomNavBarComponent bottomNavBarComp;

    @BeforeClass
    public void beforeClass() {
        loginPage = new LoginPage(appiumDriver);
        loginFormComp = loginPage.loginFormComponent();
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
    public void loginWithValidCreds() {
        softAssert.assertTrue(loginPage.isLoginFormSelect());

        dialogComp = loginFormComp.inputEmailField(loginData.getJSONObject("validCredentials").getString("email"))
                                  .inputPasswordField(loginData.getJSONObject("validCredentials").getString("password"))
                                  .clickOnLoginBtn();

        Assert.assertTrue(dialogComp.isDialogTemplateDisplay());

        String actualDialogTitle = dialogComp.dialogTitleElem().getText();
        String actualDialogMessage = dialogComp.dialogMessageElem().getText();
        String expectedDialogTitle = expectedStringMap.get("success_login_dialog_title");
        String expectedDialogMessage = expectedStringMap.get("success_login_dialog_msg");

        softAssert.assertEquals(actualDialogTitle, expectedDialogTitle);
        softAssert.assertEquals(actualDialogMessage, expectedDialogMessage);
        softAssert.assertAll();

        dialogComp.clickDialogBtn();
    }

    @Test
    public void loginWithInvalidEmail() {
        softAssert.assertTrue(loginPage.isLoginFormSelect());

        dialogComp = loginFormComp.inputEmailField(loginData.getJSONObject("invalidCredentials").getString("email"))
                                  .inputPasswordField(loginData.getJSONObject("validCredentials").getString("password"))
                                  .clickOnLoginBtn();

        String actualWrongEmailMessage = loginFormComp.wrongEmailTextElem().getText();
        String expectedWrongEmailMessage = expectedStringMap.get("error_login_invalid_email_msg");

        Assert.assertEquals(actualWrongEmailMessage, expectedWrongEmailMessage);
        softAssert.assertAll();
    }

    @Test
    public void loginWithInvalidPassword() {
        softAssert.assertTrue(loginPage.isLoginFormSelect());

        dialogComp = loginFormComp.inputEmailField(loginData.getJSONObject("validCredentials").getString("email"))
                                  .inputPasswordField(loginData.getJSONObject("invalidCredentials").getString("password"))
                                  .clickOnLoginBtn();

        String actualWrongPwMessage = loginFormComp.wrongPasswordTextElem().getText();
        String expectedWrongPwMessage = expectedStringMap.get("error_login_invalid_password_msg");

        Assert.assertEquals(actualWrongPwMessage, expectedWrongPwMessage);
        softAssert.assertAll();
    }

}
