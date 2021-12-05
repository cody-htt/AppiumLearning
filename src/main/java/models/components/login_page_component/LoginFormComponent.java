package models.components.login_page_component;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import models.base.PageModel;

public class LoginFormComponent extends PageModel {

    @AndroidFindBy(accessibility = "input-email")
    private MobileElement emailInputFieldElem;
    @AndroidFindBy(accessibility = "input-password")
    private MobileElement passwordFieldElem;
    @AndroidFindBy(accessibility = "button-LOGIN")
    private MobileElement loginBtnElem;
    @AndroidFindBy(xpath = "//*[@content-desc='button-LOGIN']/preceding-sibling::android.widget.TextView[3]")
    private MobileElement wrongEmailTextElem;
    @AndroidFindBy(xpath = "//*[@content-desc='button-LOGIN']/preceding-sibling::android.widget.TextView[2]")
    private MobileElement wrongPasswordTextElem;

    public LoginFormComponent(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
    }

    public MobileElement loginBtnElem() {
        waitForVisibility(loginBtnElem);
        return loginBtnElem;
    }

    public MobileElement wrongEmailTextElem() {
        waitForVisibility(wrongEmailTextElem);
        return wrongEmailTextElem;
    }

    public MobileElement wrongPasswordTextElem() {
        waitForVisibility(wrongPasswordTextElem);
        return wrongPasswordTextElem;
    }

    @Step("Input email as {email}")
    public LoginFormComponent inputEmailField(String email) {
        clearElementInputField(emailInputFieldElem);
        sendKeysToElement(emailInputFieldElem, email);
        return this;
    }

    @Step("Input password as {password}")
    public LoginFormComponent inputPasswordField(String password) {
        clearElementInputField(passwordFieldElem);
        sendKeysToElement(passwordFieldElem, password);
        return this;
    }

    @Step("Click on Login button")
    public DialogComponent clickOnLoginBtn() {
        clickElement(loginBtnElem);
        return new DialogComponent(appiumDriver);
    }

}
