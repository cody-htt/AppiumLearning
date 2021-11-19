package AppiumLearning;

import driver.DriverFactoryRD;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import models.components.BottomNavBarComponent;
import models.components.login_page_component.DialogComponent;
import models.components.login_page_component.LoginFormComponent;
import models.components.login_page_component.SignUpFormComponent;
import models.pages.LoginPage;

public class TestLab_14 {

    private static String result_01 = null;
    private static String result_02 = null;
    private static String result_03 = null;

    public static void main(String[] args) {

        DriverFactoryRD.startAppiumServer();
        AndroidDriver<MobileElement> androidDriver = DriverFactoryRD.getAndroidDriver();
        LoginPage loginPage = new LoginPage(androidDriver);
        BottomNavBarComponent bottomNavBarComp = loginPage.bottomNavBarComponent();
        LoginFormComponent loginFormComp = loginPage.loginFormComponent();
        SignUpFormComponent signUpFormComp = loginPage.signUpFormComponent();
        DialogComponent dialogComponent;

        bottomNavBarComp.clickOnLoginLabel();

        if (loginPage.isLoginFormSelect()) {
            dialogComponent = loginFormComp
                    .inputEmailField("tung@email.com")
                    .inputPasswordField("12345678")
                    .clickOnLoginBtn();

            if (dialogComponent.isDialogTemplateDisplay()) {
                dialogComponent.clickDialogBtn();
                result_01 = "TC_001_Login_App is PASSED";
            } else { result_01 = "TC_001_Login_App is FAILED"; }
        }

        if (loginPage.isSignUpFormSelect()) {
            dialogComponent = signUpFormComp
                    .inputEmailField("tung@email.com")
                    .inputPasswordField("12345678")
                    .inputRepeatPwField("12345678")
                    .clickOnSignUpBtn();

            if (dialogComponent.isDialogTemplateDisplay()) {
                dialogComponent.clickDialogBtn();
                result_02 = "TC_002_SignUp_App is PASSED";
            }
            else result_02 = "TC_002_SignUp_App is FAILED";

            signUpFormComp
                    .inputEmailField("tung@email.com")
                    .inputPasswordField("12345678")
                    .inputRepeatPwField("1234")
                    .clickOnSignUpBtn();

            String errPasswordConfirm = signUpFormComp.errRepeatPwMessageElem().getText();
            if (errPasswordConfirm.equalsIgnoreCase("Please enter the same password")) {
                result_03 = "TC_003_Fail_SignUp_App is PASSED";
            }
            else result_03 = "TC_003_Fail_SignUp_App is FAILED";
        }

        androidDriver.closeApp();
        DriverFactoryRD.stopAppiumServer();
        System.out.println(result_01 + "\n" + result_02 + "\n" + result_03);
    }
}
