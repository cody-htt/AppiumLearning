package models.components.login_page_component;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import models.base.PageModel;

public class DialogComponent extends PageModel {

    @AndroidFindBy(id = "android:id/title_template")
    private MobileElement dialogTemplate;
    @AndroidFindBy(id = "android:id/alertTitle")
    private MobileElement dialogTitle;
    @AndroidFindBy(id = "android:id/message")
    private MobileElement dialogMessage;
    @AndroidFindBy(id = "android:id/button1")
    private MobileElement dialogBtn;

    public DialogComponent(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
    }

    public boolean isDialogTemplateDisplay() {
        waitForVisibility(dialogTemplate);
        return dialogTemplate.isDisplayed();
    }

    public MobileElement dialogTitleElem() {
        waitForVisibility(dialogTitle);
        return dialogTitle;
    }

    public MobileElement dialogMessageElem() {
        waitForVisibility(dialogMessage);
        return dialogMessage;
    }

    public void clickDialogBtn() {
        clickElement(dialogBtn);
    }

}
