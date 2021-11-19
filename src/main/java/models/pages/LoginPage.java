package models.pages;

import data.LoginData;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import models.base.BasePageModel;

public class LoginPage extends BasePageModel {

    public LoginPage(AndroidDriver<MobileElement> androidDriver) {
        this.androidDriver = androidDriver;
    }

    public void navigateToLoginPage() {
        clickOnElement(PageLoginElem.XPATH_LABEL_LOGIN);
    }

    public boolean isLoginForm() {
        clickOnElement(PageLoginElem.XPATH_LOGIN_FORM);
        return getElementText(PageLoginElem.XPATH_LOGIN_FORM).equalsIgnoreCase("login");
    }

    public void fillUsrNameAndPwd() {
        clearTextBox(PageLoginElem.XPATH_INPUT_USERNAME);
        clearTextBox(PageLoginElem.XPATH_INPUT_PASSWORD);
        fillTextBox(PageLoginElem.XPATH_INPUT_USERNAME, LoginData.EMAIL);
        fillTextBox(PageLoginElem.XPATH_INPUT_PASSWORD, LoginData.PASSWORD);
    }

    public void clickLoginBtn() {
        clickOnElement(PageLoginElem.XPATH_BTN_LOGIN);
    }

    public boolean verifyLoginSuccess() {
        boolean isSuccess = false;
        if (isElementPresent(PageLoginElem.ID_ALERT_BOX_TEMPLATE)) {
            isSuccess =
                    getElementText(PageLoginElem.ID_ALERT_TITLE)
                            .equalsIgnoreCase(PageLoginElem.EXPECTED_SUCCESS_LOGIN) &&
                            getElementText(PageLoginElem.ID_ALERT_MSG)
                                    .equalsIgnoreCase(PageLoginElem.EXPECTED_SUCCESS_LOGIN_MSG);

            clickOnElement(PageLoginElem.ID_ALERT_BTN_OK);
        }
        return isSuccess;
    }

    public boolean isSignUpForm() {
        clickOnElement(PageLoginElem.XPATH_SIGNUP_FORM);
        return getElementText(PageLoginElem.XPATH_SIGNUP_FORM).equalsIgnoreCase("Sign up");
    }

    public void fillCorrectRepeatPassword() {
        fillTextBox(PageLoginElem.XPATH_INPUT_CONFIRM_PASSWORD, LoginData.PASSWORD);
    }

    public void fillWrongRepeatPassword() {
        clearTextBox(PageLoginElem.XPATH_INPUT_CONFIRM_PASSWORD);
        fillTextBox(PageLoginElem.XPATH_INPUT_CONFIRM_PASSWORD, LoginData.WRONG_PASSWORD);
    }

    public void clickSignUpBtn() {
        clickOnElement(PageLoginElem.XPATH_BTN_SIGN_UP);
    }

    public boolean verifySignUpSuccess() {
        boolean isSuccess = false;
        if (isElementPresent(PageLoginElem.ID_ALERT_BOX_TEMPLATE)) {
            isSuccess =
                    getElementText(PageLoginElem.ID_ALERT_TITLE)
                            .equalsIgnoreCase(PageLoginElem.EXPECTED_SUCCESS_SIGNUP) &&
                            getElementText(PageLoginElem.ID_ALERT_MSG)
                                    .equalsIgnoreCase(PageLoginElem.EXPECTED_SUCCESS_SIGNUP_MSG);

            clickOnElement(PageLoginElem.ID_ALERT_BTN_OK);
        }
        return isSuccess;
    }

    public boolean verifySignUpFail() {
        return (!isElementNotPresent(PageLoginElem.ID_ALERT_BOX_TEMPLATE)) &&
                getElementText(PageLoginElem.XPATH_CONFIRM_PW_ERROR)
                        .equalsIgnoreCase(PageLoginElem.EXPECTED_CONFIRM_PASSWORD_ER_MSG);
    }
}
