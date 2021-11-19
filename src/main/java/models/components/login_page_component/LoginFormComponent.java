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
        waitForVisibility(emailInputField);
        clearElementInputField(emailInputField);
        emailInputField.sendKeys(email);
        return this;
    }

    public LoginFormComponent inputPasswordField(String password) {
        waitForVisibility(passwordField);
        clearElementInputField(passwordField);
        passwordField.sendKeys(password);
        return this;
    }

    public DialogComponent clickOnLoginBtn() {
        waitForVisibility(loginBtn);
        loginBtn.click();
        return new DialogComponent(appiumDriver);
    }

    public MobileElement loginBtnElem() {
        waitForVisibility(loginBtn);
        return loginBtn;
    }

}
