package models.components.forms_page_component;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import models.base.PageModel;

public class ActiveBtnDialogComponent extends PageModel {

    @AndroidFindBy(xpath = "//*[@resource-id='android:id/parentPanel']")
    private MobileElement activeParentPanel;
    @AndroidFindBy(id = "android:id/alertTitle")
    private MobileElement alertTitle;
    @AndroidFindBy(id = "android:id/message")
    private MobileElement alertMessage;
    @AndroidFindBy(id = "android:id/button3")
    private MobileElement askMeLaterBtn;
    @AndroidFindBy(id = "android:id/button2")
    private MobileElement cancelBtn;
    @AndroidFindBy(id = "android:id/button1")
    private MobileElement okBtn;

    public ActiveBtnDialogComponent(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
    }

    public MobileElement activeParentPanel() {
        waitForVisibility(activeParentPanel);
        return activeParentPanel;
    }

    public MobileElement alertTitle() {
        waitForVisibility(alertTitle);
        return alertTitle;
    }

    public MobileElement alertMessage() {
        waitForVisibility(alertMessage);
        return alertMessage;
    }

    public MobileElement askMeLaterBtn() {
        waitForVisibility(askMeLaterBtn);
        return askMeLaterBtn;
    }

    public MobileElement cancelBtn() {
        waitForVisibility(cancelBtn);
        return cancelBtn;
    }

    public MobileElement okBtn() {
        waitForVisibility(okBtn);
        return okBtn;
    }
}
