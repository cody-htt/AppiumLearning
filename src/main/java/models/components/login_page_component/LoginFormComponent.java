package models.components.login_page_component;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Constant;

public class LoginFormComponent {

    @AndroidFindBy(accessibility = "input-email")
    private MobileElement emailInputField;
    @AndroidFindBy(accessibility = "input-password")
    private MobileElement passwordField;
    @AndroidFindBy(accessibility = "button-LOGIN")
    private MobileElement loginBtn;

    private final WebDriverWait wait;
    private final AppiumDriver<MobileElement> appiumDriver;

    public LoginFormComponent(AppiumDriver<MobileElement> appiumDriver) {
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
        wait = new WebDriverWait(appiumDriver, Constant.SHORT_WAIT_TIME);
        this.appiumDriver = appiumDriver;
    }

    public LoginFormComponent inputEmailField(String email) {
        waitForVisibility(emailInputField);
        emailInputField.sendKeys(email);
        return this;
    }

    public LoginFormComponent inputPasswordField(String password) {
        waitForVisibility(passwordField);
        emailInputField.sendKeys(password);
        return this;
    }

    public DialogComponent clickOnLoginBtn() {
        waitForVisibility(loginBtn);
        loginBtn.click();
        return new DialogComponent(appiumDriver);
    }

    private void waitForVisibility(MobileElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

}
