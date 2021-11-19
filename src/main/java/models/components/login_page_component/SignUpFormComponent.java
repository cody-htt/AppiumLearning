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
        waitForVisibility(emailInputField);
        clearElementInputField(emailInputField);
        emailInputField.sendKeys(email);
        return this;
    }

    public SignUpFormComponent inputPasswordField(String password) {
        waitForVisibility(passwordField);
        clearElementInputField(passwordField);
        passwordField.sendKeys(password);
        return this;
    }

    public SignUpFormComponent inputRepeatPwField(String password) {
        waitForVisibility(repeatPasswordField);
        clearElementInputField(repeatPasswordField);
        repeatPasswordField.sendKeys(password);
        return this;
    }

    public DialogComponent clickOnSignUpBtn() {
        waitForVisibility(signUpBtn);
        signUpBtn.click();
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
