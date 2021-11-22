package models.components.login_page_component;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import models.base.PageModel;

public class DialogComponent extends PageModel {

    @AndroidFindBy(id = "android:id/title_template")
    private MobileElement dialogTemplateElem;
    @AndroidFindBy(id = "android:id/alertTitle")
    private MobileElement dialogTitleElem;
    @AndroidFindBy(id = "android:id/message")
    private MobileElement dialogMessageElem;
    @AndroidFindBy(id = "android:id/button1")
    private MobileElement dialogBtnElem;

    public DialogComponent(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
    }

    public MobileElement dialogTitleElem() {
        waitForVisibility(dialogTitleElem);
        return dialogTitleElem;
    }

    public MobileElement dialogMessageElem() {
        waitForVisibility(dialogMessageElem);
        return dialogMessageElem;
    }

    public boolean isDialogTemplateDisplay() {
        waitForVisibility(dialogTemplateElem);
        return dialogTemplateElem.isDisplayed();
    }

    public void clickDialogBtn() {
        clickElement(dialogBtnElem);
    }

}
