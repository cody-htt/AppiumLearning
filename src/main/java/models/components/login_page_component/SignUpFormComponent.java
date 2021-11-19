package models.components.login_page_component;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import models.base.PageModel;

public class SignUpFormComponent extends PageModel {

    @AndroidFindBy(accessibility = "input-email")
    private MobileElement emailInputField;
    @AndroidFindBy(accessibility = "input-password")
    private MobileElement passwordField;
    @AndroidFindBy(accessibility = "input-repeat-password")
    private MobileElement repeatPasswordField;
    @AndroidFindBy(accessibility = "button-SIGN UP")
    private MobileElement signUpBtn;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='button-SIGN UP']/preceding-sibling::android.widget.TextView[1]")
    private MobileElement errRepeatPwMessage;

    public SignUpFormComponent(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
    }

    public SignUpFormComponent inputEmailField(String email) {
        clearElementInputField(emailInputField);
        sendKeysToElement(emailInputField, email);
        return this;
    }

    public SignUpFormComponent inputPasswordField(String password) {
        clearElementInputField(passwordField);
        sendKeysToElement(passwordField, password);
        return this;
    }

    public SignUpFormComponent inputRepeatPwField(String password) {
        clearElementInputField(repeatPasswordField);
        sendKeysToElement(repeatPasswordField, password);
        return this;
    }

    public DialogComponent clickOnSignUpBtn() {
        clickElement(signUpBtn);
        return new DialogComponent(appiumDriver);
    }

    public MobileElement signUpBtnElem() {
        waitForVisibility(signUpBtn);
        return signUpBtn;
    }

    public MobileElement errRepeatPwMessageElem() {
        waitForVisibility(errRepeatPwMessage);
        return errRepeatPwMessage;
    }

}
