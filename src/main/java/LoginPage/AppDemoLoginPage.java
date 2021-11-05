package LoginPage;

import AbstractPage.AbstractPageObject;
import Utilities.Constant;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class AppDemoLoginPage extends AbstractPageObject {

    public AppDemoLoginPage(AndroidDriver<MobileElement> androidDriver) {
        super(androidDriver);
    }

    public void navigateToLoginPage() {
        clickOnElement(LoginPageObject.XPATH_PAGE_LOGIN_BTN);
    }

    public boolean isLoginForm() {
        clickOnElement(LoginPageObject.XPATH_LOGIN_FORM);
        return getElementText(LoginPageObject.XPATH_LOGIN_FORM).equalsIgnoreCase("login");
    }

    public void fillUsrNameAndPwd() {
        clearTextBox(LoginPageObject.XPATH_INPUT_USERNAME);
        clearTextBox(LoginPageObject.XPATH_INPUT_PASSWORD);
        fillTextBox(LoginPageObject.XPATH_INPUT_USERNAME , Constant.EMAIL);
        fillTextBox(LoginPageObject.XPATH_INPUT_PASSWORD , Constant.PASSWORD);
    }

    public void clickLoginBtn() {
        clickOnElement(LoginPageObject.XPATH_BTN_LOGIN);
    }

    public boolean verifyLoginSuccess() {
        boolean isSuccess = false;
        if (isElementPresent(LoginPageObject.ID_ALERT_BOX_TEMPLATE)) {
            isSuccess = getElementText(LoginPageObject.ID_ALERT_TITLE).equalsIgnoreCase(LoginPageObject.EXPECTED_SUCCESS_LOGIN) &&
                    getElementText(LoginPageObject.ID_ALERT_MSG).equalsIgnoreCase(LoginPageObject.EXPECTED_SUCCESS_LOGIN_MSG);
            clickOnElement(LoginPageObject.ID_ALERT_BTN_OK);
        }
        return isSuccess;
    }

    public boolean isSignUpForm() {
        clickOnElement(LoginPageObject.XPATH_SIGNUP_FORM);
        return getElementText(LoginPageObject.XPATH_SIGNUP_FORM).equalsIgnoreCase("Sign up");
    }

    public void fillCorrectRepeatPassword() {
        fillTextBox(LoginPageObject.XPATH_INPUT_CONFIRM_PASSWORD , Constant.PASSWORD);
    }

    public void fillWrongRepeatPassword() {
        clearTextBox(LoginPageObject.XPATH_INPUT_CONFIRM_PASSWORD);
        fillTextBox(LoginPageObject.XPATH_INPUT_CONFIRM_PASSWORD , Constant.WRONG_PASSWORD);
    }

    public void clickSignUpBtn() {
        clickOnElement(LoginPageObject.XPATH_BTN_SIGN_UP);
    }

    public boolean verifySignUpSuccess() {
        boolean isSuccess = false;
        if (isElementPresent(LoginPageObject.ID_ALERT_BOX_TEMPLATE)) {
            isSuccess = getElementText(LoginPageObject.ID_ALERT_TITLE).equalsIgnoreCase(LoginPageObject.EXPECTED_SUCCESS_SIGNUP) &&
                    getElementText(LoginPageObject.ID_ALERT_MSG).equalsIgnoreCase(LoginPageObject.EXPECTED_SUCCESS_SIGNUP_MSG);
            clickOnElement(LoginPageObject.ID_ALERT_BTN_OK);
        }
        return isSuccess;
    }

    public boolean verifySignUpFail() {
        if (isElementPresent(LoginPageObject.ID_ALERT_BOX_TEMPLATE)) {
            return false;
        }
        return getElementText(LoginPageObject.XPATH_CONFIRM_PW_ERROR).equalsIgnoreCase(LoginPageObject.EXPECTED_CONFIRM_PASSWORD_ER_MSG);
    }
}
