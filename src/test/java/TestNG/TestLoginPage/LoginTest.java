package TestNG.TestLoginPage;

import models.base.BaseTest;
import models.components.BottomNavBarComponent;
import models.components.login_page_component.DialogComponent;
import models.components.login_page_component.LoginFormComponent;
import models.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.*;
import test_data.LoginCreds;
import test_data.authentication.DataObjectBuilder;

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

    @Test(dataProvider = "loginCredsData")
    public void loginWithValidCreds(LoginCreds loginCreds) {
        softAssert.assertTrue(loginPage.isLoginFormSelect());

/*      Old way to test - read json object and retrieve "key":"value" of that object
        dialogComp = loginFormComp.inputEmailField(loginData.getJSONObject("validCredentials").getString("email"))
                                  .inputPasswordField(loginData.getJSONObject("validCredentials").getString("password"))
                                  .clickOnLoginBtn();
*/
        dialogComp = loginFormComp.inputEmailField(loginCreds.getEmail())
                                  .inputPasswordField(loginCreds.getPassword())
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

    @DataProvider
    public LoginCreds[] loginCredsData() {
        String jsonLoc = "src/test/resources/data/loginCreds.json";
        return DataObjectBuilder.buildDataObject(jsonLoc, LoginCreds[].class);
    }
}
