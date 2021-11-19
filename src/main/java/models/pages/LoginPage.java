package models.pages;

import data.LoginData;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import models.base.PageModel;
import models.components.login_page_component.LoginFormComponent;
import models.components.login_page_component.SignUpFormComponent;

public class LoginPage extends PageModel {

    @AndroidFindBy(accessibility = "button-login-container")
    private MobileElement loginForm;
    @AndroidFindBy(accessibility = "button-sign-up-container")
    private MobileElement signUpForm;

    private LoginFormComponent loginFormComponent;
    private SignUpFormComponent signUpFormComponent;

    public LoginPage(AndroidDriver<MobileElement> androidDriver) { this.appiumDriver = androidDriver; }


    /* ------------------------- Old code ------------------------- */
    public void navigateToLoginPage() {
        clickElementBy(PageLoginElem.XPATH_LABEL_LOGIN);
    }

    public boolean isLoginForm() {
        clickElementBy(PageLoginElem.XPATH_LOGIN_FORM);
        return getElementTextBy(PageLoginElem.XPATH_LOGIN_FORM).equalsIgnoreCase("login");
    }

    public void fillUsrNameAndPwd() {
        clearElementInputFieldBy(PageLoginElem.XPATH_INPUT_USERNAME);
        clearElementInputFieldBy(PageLoginElem.XPATH_INPUT_PASSWORD);
        sendKeysToElementBy(PageLoginElem.XPATH_INPUT_USERNAME, LoginData.EMAIL);
        sendKeysToElementBy(PageLoginElem.XPATH_INPUT_PASSWORD, LoginData.PASSWORD);
    }

    public void clickLoginBtn() {
        clickElementBy(PageLoginElem.XPATH_BTN_LOGIN);
    }

    public boolean verifyLoginSuccess() {
        boolean isSuccess = false;
        if (isElementPresentBy(PageLoginElem.ID_ALERT_BOX_TEMPLATE)) {
            isSuccess =
                    getElementTextBy(PageLoginElem.ID_ALERT_TITLE)
                            .equalsIgnoreCase(PageLoginElem.EXPECTED_SUCCESS_LOGIN) &&
                            getElementTextBy(PageLoginElem.ID_ALERT_MSG)
                                    .equalsIgnoreCase(PageLoginElem.EXPECTED_SUCCESS_LOGIN_MSG);

            clickElementBy(PageLoginElem.ID_ALERT_BTN_OK);
        }
        return isSuccess;
    }

    public boolean isSignUpForm() {
        clickElementBy(PageLoginElem.XPATH_SIGNUP_FORM);
        return getElementTextBy(PageLoginElem.XPATH_SIGNUP_FORM).equalsIgnoreCase("Sign up");
    }

    public void fillCorrectRepeatPassword() {
        sendKeysToElementBy(PageLoginElem.XPATH_INPUT_CONFIRM_PASSWORD, LoginData.PASSWORD);
    }

    public void fillWrongRepeatPassword() {
        clearElementInputFieldBy(PageLoginElem.XPATH_INPUT_CONFIRM_PASSWORD);
        sendKeysToElementBy(PageLoginElem.XPATH_INPUT_CONFIRM_PASSWORD, LoginData.WRONG_PASSWORD);
    }

    public void clickSignUpBtn() {
        clickElementBy(PageLoginElem.XPATH_BTN_SIGN_UP);
    }

    public boolean verifySignUpSuccess() {
        boolean isSuccess = false;
        if (isElementPresentBy(PageLoginElem.ID_ALERT_BOX_TEMPLATE)) {
            isSuccess =
                    getElementTextBy(PageLoginElem.ID_ALERT_TITLE)
                            .equalsIgnoreCase(PageLoginElem.EXPECTED_SUCCESS_SIGNUP) &&
                            getElementTextBy(PageLoginElem.ID_ALERT_MSG)
                                    .equalsIgnoreCase(PageLoginElem.EXPECTED_SUCCESS_SIGNUP_MSG);

            clickElementBy(PageLoginElem.ID_ALERT_BTN_OK);
        }
        return isSuccess;
    }

    public boolean verifySignUpFail() {
        return (!isElementNotPresentBy(PageLoginElem.ID_ALERT_BOX_TEMPLATE)) &&
                getElementTextBy(PageLoginElem.XPATH_CONFIRM_PW_ERROR)
                        .equalsIgnoreCase(PageLoginElem.EXPECTED_CONFIRM_PASSWORD_ER_MSG);
    }
}
