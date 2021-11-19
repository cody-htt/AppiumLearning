package models.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import models.base.PageModel;
import models.components.BottomNavBarComponent;
import models.components.login_page_component.LoginFormComponent;
import models.components.login_page_component.SignUpFormComponent;

public class LoginPage extends PageModel {

    @AndroidFindBy(accessibility = "button-login-container")
    private MobileElement loginFormLabelElem;
    @AndroidFindBy(accessibility = "button-sign-up-container")
    private MobileElement signUpFormLabelElem;

    public LoginPage(AndroidDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
    }

    public boolean isLoginFormSelect() {
        loginFormLabelElem.click();
        return loginFormComponent().loginBtnElem().isDisplayed();
    }

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
