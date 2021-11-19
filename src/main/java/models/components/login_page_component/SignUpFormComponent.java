package models.components.login_page_component;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Constant;

public class SignUpFormComponent {

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

    private final WebDriverWait wait;
    private final AppiumDriver<MobileElement> appiumDriver;

    public SignUpFormComponent(AppiumDriver<MobileElement> appiumDriver) {
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
        wait = new WebDriverWait(appiumDriver, Constant.SHORT_WAIT_TIME);
        this.appiumDriver = appiumDriver;
    }

    public SignUpFormComponent inputEmailField(String email) {
        waitForVisibility(emailInputField);
        emailInputField.sendKeys(email);
        return this;
    }

    public SignUpFormComponent inputPasswordField(String password) {
        waitForVisibility(passwordField);
        passwordField.sendKeys(password);
        return this;
    }

    public DialogComponent clickOnSignUpBtn() {
        waitForVisibility(signUpBtn);
        signUpBtn.click();
        return new DialogComponent(appiumDriver);
    }

    public MobileElement errRepeatPwMessageElem() {
        return errRepeatPwMessage;
    }

    private void waitForVisibility(MobileElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}
