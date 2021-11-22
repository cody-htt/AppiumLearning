package models.components.login_page_component;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import models.base.PageModel;

public class SignUpFormComponent extends PageModel {

    @AndroidFindBy(accessibility = "input-email")
    private MobileElement emailInputFieldElem;
    @AndroidFindBy(accessibility = "input-password")
    private MobileElement passwordFieldElem;
    @AndroidFindBy(accessibility = "input-repeat-password")
    private MobileElement repeatPasswordFieldElem;
    @AndroidFindBy(accessibility = "button-SIGN UP")
    private MobileElement signUpBtnElem;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='button-SIGN UP']/preceding-sibling::android.widget.TextView[1]")
    private MobileElement errRepeatPwMessageElem;

    public SignUpFormComponent(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
    }

    public MobileElement signUpBtnElem() {
        waitForVisibility(signUpBtnElem);
        return signUpBtnElem;
    }

    public MobileElement errRepeatPwMessageElem() {
        waitForVisibility(errRepeatPwMessageElem);
        return errRepeatPwMessageElem;
    }

    public SignUpFormComponent inputEmailField(String email) {
        clearElementInputField(emailInputFieldElem);
        sendKeysToElement(emailInputFieldElem, email);
        return this;
    }

    public SignUpFormComponent inputPasswordField(String password) {
        clearElementInputField(passwordFieldElem);
        sendKeysToElement(passwordFieldElem, password);
        return this;
    }

    public SignUpFormComponent inputRepeatPasswordField(String password) {
        clearElementInputField(repeatPasswordFieldElem);
        sendKeysToElement(repeatPasswordFieldElem, password);
        return this;
    }

    public DialogComponent clickOnSignUpBtn() {
        clickElement(signUpBtnElem);
        return new DialogComponent(appiumDriver);
    }

}
