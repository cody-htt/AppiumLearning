package Pages;

import AbstractPage.AbstractPageObject;
import Data.Constant;
import Locators.PageLoginLocator;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class AppDemoLoginPage extends AbstractPageObject {

    public AppDemoLoginPage(AndroidDriver<MobileElement> androidDriver) {
        super(androidDriver);
    }

    public void navigateToLoginPage() {
        clickOnElement(PageLoginLocator.XPATH_PAGE_LOGIN_BTN);
    }

    public boolean isLoginForm() {
        clickOnElement(PageLoginLocator.XPATH_LOGIN_FORM);
        return getElementText(PageLoginLocator.XPATH_LOGIN_FORM).equalsIgnoreCase("login");
    }

    public void fillUsrNameAndPwd() {
        clearTextBox(PageLoginLocator.XPATH_INPUT_USERNAME);
        clearTextBox(PageLoginLocator.XPATH_INPUT_PASSWORD);
        fillTextBox(PageLoginLocator.XPATH_INPUT_USERNAME , Constant.EMAIL);
        fillTextBox(PageLoginLocator.XPATH_INPUT_PASSWORD , Constant.PASSWORD);
    }

    public void clickLoginBtn() {
        clickOnElement(PageLoginLocator.XPATH_BTN_LOGIN);
    }

    public boolean verifyLoginSuccess() {
        boolean isSuccess = false;
        if (isElementPresent(PageLoginLocator.ID_ALERT_BOX_TEMPLATE)) {
            isSuccess = getElementText(PageLoginLocator.ID_ALERT_TITLE).equalsIgnoreCase(PageLoginLocator.EXPECTED_SUCCESS_LOGIN) &&
                    getElementText(PageLoginLocator.ID_ALERT_MSG).equalsIgnoreCase(PageLoginLocator.EXPECTED_SUCCESS_LOGIN_MSG);
            clickOnElement(PageLoginLocator.ID_ALERT_BTN_OK);
        }
        return isSuccess;
    }

    public boolean isSignUpForm() {
        clickOnElement(PageLoginLocator.XPATH_SIGNUP_FORM);
        return getElementText(PageLoginLocator.XPATH_SIGNUP_FORM).equalsIgnoreCase("Sign up");
    }

    public void fillCorrectRepeatPassword() {
        fillTextBox(PageLoginLocator.XPATH_INPUT_CONFIRM_PASSWORD , Constant.PASSWORD);
    }

    public void fillWrongRepeatPassword() {
        clearTextBox(PageLoginLocator.XPATH_INPUT_CONFIRM_PASSWORD);
        fillTextBox(PageLoginLocator.XPATH_INPUT_CONFIRM_PASSWORD , Constant.WRONG_PASSWORD);
    }

    public void clickSignUpBtn() {
        clickOnElement(PageLoginLocator.XPATH_BTN_SIGN_UP);
    }

    public boolean verifySignUpSuccess() {
        boolean isSuccess = false;
        if (isElementPresent(PageLoginLocator.ID_ALERT_BOX_TEMPLATE)) {
            isSuccess = getElementText(PageLoginLocator.ID_ALERT_TITLE).equalsIgnoreCase(PageLoginLocator.EXPECTED_SUCCESS_SIGNUP) &&
                    getElementText(PageLoginLocator.ID_ALERT_MSG).equalsIgnoreCase(PageLoginLocator.EXPECTED_SUCCESS_SIGNUP_MSG);
            clickOnElement(PageLoginLocator.ID_ALERT_BTN_OK);
        }
        return isSuccess;
    }

    public boolean verifySignUpFail() {
        if (isElementPresent(PageLoginLocator.ID_ALERT_BOX_TEMPLATE)) {
            return false;
        }
        return getElementText(PageLoginLocator.XPATH_CONFIRM_PW_ERROR).equalsIgnoreCase(PageLoginLocator.EXPECTED_CONFIRM_PASSWORD_ER_MSG);
    }
}
