package AppiumLearning;

import driver.DriverFactoryRD;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import models.pages.LoginPage;

public class TestLab_14 {

    public static void main(String[] args) {

        DriverFactoryRD.startAppiumServer();
        AndroidDriver<MobileElement> androidDriver = DriverFactoryRD.getAndroidDriver();
        LoginPage loginPage = new LoginPage(androidDriver);

        String result_01 = null;
        String result_02 = null;
        String result_03 = null;

        loginPage.navigateToLoginPage();

        if (loginPage.isLoginForm()) {
            loginPage.fillUsrNameAndPwd();
            loginPage.clickLoginBtn();

            if (loginPage.verifyLoginSuccess()) result_01 = "TC_001_Login_App is PASSED";
            else result_01 = "TC_001_Login_App is FAILED";
        }

        if (loginPage.isSignUpForm()) {
            loginPage.fillUsrNameAndPwd();
            loginPage.fillCorrectRepeatPassword();
            loginPage.clickSignUpBtn();

            if (loginPage.verifySignUpSuccess()) result_02 = "TC_002_SignUp_App is PASSED";
            else result_02 = "TC_002_SignUp_App is FAILED";

            loginPage.fillWrongRepeatPassword();
            loginPage.clickSignUpBtn();

            if (loginPage.verifySignUpFail()) result_03 = "TC_003_Fail_SignUp_App is PASSED";
            else result_03 = "TC_003_Fail_SignUp_App is FAILED";
        }

        androidDriver.closeApp();
        DriverFactoryRD.stopAppiumServer();
        System.out.println(result_01 + "\n" + result_02 + "\n" + result_03);
    }
}
