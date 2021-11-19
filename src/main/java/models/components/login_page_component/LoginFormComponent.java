package models.components.login_page_component;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import models.base.PageModel;

public class LoginFormComponent extends PageModel {

    @AndroidFindBy(accessibility = "input-email")
    private MobileElement emailInputField;
    @AndroidFindBy(accessibility = "input-password")
    private MobileElement passwordField;
    @AndroidFindBy(accessibility = "button-LOGIN")
    private MobileElement loginBtn;

    public LoginFormComponent(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
    }

    public LoginFormComponent inputEmailField(String email) {
        clearElementInputField(emailInputField);
        sendKeysToElement(emailInputField, email);
        return this;
    }

    public LoginFormComponent inputPasswordField(String password) {
        clearElementInputField(passwordField);
        sendKeysToElement(passwordField, password);
        return this;
    }

    public DialogComponent clickOnLoginBtn() {
        clickElement(loginBtn);
        return new DialogComponent(appiumDriver);
    }

    public MobileElement loginBtnElem() {
        waitForVisibility(loginBtn);
        return loginBtn;
    }
}
