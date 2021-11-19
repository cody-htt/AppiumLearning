package models.components.login_page_component;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Constant;

public class DialogComponent {

    @AndroidFindBy(id = "android:id/title_template")
    private MobileElement dialogTemplate;
    @AndroidFindBy(id = "android:id/alertTitle")
    private MobileElement dialogTitle;
    @AndroidFindBy(id = "android:id/message")
    private MobileElement dialogMessage;
    @AndroidFindBy(id = "android:id/button1")
    private MobileElement dialogBtn;

    private final WebDriverWait wait;

    public DialogComponent(AppiumDriver<MobileElement> appiumDriver) {
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
        wait = new WebDriverWait(appiumDriver, Constant.SHORT_WAIT_TIME);
    }

    public MobileElement dialogTemplateElem() {
        waitForVisibility(dialogTemplate);
        return dialogTemplate;
    }

    public MobileElement dialogTitleElem() {
        waitForVisibility(dialogTitle);
        return dialogTitle;
    }

    public MobileElement dialogMessageElem() {
        waitForVisibility(dialogMessage);
        return dialogMessage;
    }

    public MobileElement dialogBtnElem() {
        waitForVisibility(dialogBtn);
        return dialogBtn;
    }

    private void waitForVisibility(MobileElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}
