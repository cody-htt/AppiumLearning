package models.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import models.base.PageModel;
import models.components.BottomNavBarComponent;
import models.components.login_page_component.LoginFormComponent;
import models.components.login_page_component.SignUpFormComponent;

public class LoginPage extends PageModel {

    @AndroidFindBy(accessibility = "button-login-container")
    private MobileElement loginFormLabelElem;
    @AndroidFindBy(accessibility = "button-sign-up-container")
    private MobileElement signUpFormLabelElem;
    @AndroidFindBy(xpath = "//*[@content-desc='button-sign-up-container']/following-sibling::android.view.ViewGroup/android.widget.TextView[1]")
    private MobileElement errInvalidEmailMsgElem;
    @AndroidFindBy(xpath = "//*[@content-desc='button-sign-up-container']/following-sibling::android.view.ViewGroup/android.widget.TextView[2]")
    private MobileElement errInvalidPasswordMsgElem;

    public LoginPage(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
    }

    public MobileElement errInvalidEmailMsgElem() {
        waitForVisibility(errInvalidEmailMsgElem);
        return errInvalidEmailMsgElem;
    }

    public MobileElement errInvalidPasswordMsgElem() {
        waitForVisibility(errInvalidPasswordMsgElem);
        return errInvalidPasswordMsgElem;
    }

    @Step("Select Login Form")
    public boolean isLoginFormSelect() {
        loginFormLabelElem.click();
        return loginFormComponent().loginBtnElem().isDisplayed();
    }

    @Step("Select Sign Up Form")
    public boolean isSignUpFormSelect() {
        signUpFormLabelElem.click();
        return signUpFormComponent().signUpBtnElem().isDisplayed();
    }

    public LoginFormComponent loginFormComponent() {
        return new LoginFormComponent(appiumDriver);
    }

    public SignUpFormComponent signUpFormComponent() {
        return new SignUpFormComponent(appiumDriver);
    }

    public BottomNavBarComponent bottomNavBarComponent() {
        return new BottomNavBarComponent(appiumDriver);
    }

}
