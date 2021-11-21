package models.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import models.base.PageModel;
import models.components.BottomNavBarComponent;
import models.components.forms_page_component.ActiveBtnDialogComponent;
import models.components.forms_page_component.DropdownDialogComponent;
import org.openqa.selenium.By;
import utils.SwipeUtils;

public class FormPage extends PageModel {

    @AndroidFindBy(accessibility = "text-input")
    private MobileElement inputField;
    @AndroidFindBy(accessibility = "input-text-result")
    private MobileElement inputTextResult;
    @AndroidFindBy(accessibility = "switch")
    private MobileElement switchBtn;
    @AndroidFindBy(accessibility = "switch-text")
    private MobileElement switchText;
    @AndroidFindBy(accessibility = "Dropdown")
    private MobileElement dropDownFieldContainer;
    @AndroidFindBy(xpath = "//*[@resource-id='icon_container']")
    private MobileElement dropDownIcon;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Dropdown']//android.widget.EditText")
    private MobileElement dropDownInputField;
    @AndroidFindBy(accessibility = "button-Active")
    private MobileElement activeBtn;

    /* By Locator */
    private final static By activeBtnAccId = MobileBy.AccessibilityId("button-Active");

    private final SwipeUtils swipeUtils;

    public FormPage(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
        swipeUtils = new SwipeUtils(this.appiumDriver);
    }

    public MobileElement dropDownField() {
        waitForVisibility(dropDownInputField);
        return dropDownInputField;
    }

    public MobileElement switchText() {
        waitForVisibility(switchText);
        return switchText;
    }

    public MobileElement inputTextResult() {
        waitForVisibility(inputTextResult);
        return inputTextResult;
    }

    public FormPage inputField(String text) {
        sendKeysToElement(inputField, text);
        return this;
    }

    public FormPage clickOnSwitchBtn() {
        clickElement(switchBtn);
        return this;
    }

    public DropdownDialogComponent clickOnDropDownIcon() {
        clickElement(dropDownIcon);
        return new DropdownDialogComponent(appiumDriver);
    }

    public ActiveBtnDialogComponent clickOnActiveBtn() {
        swipeUtils.swipeUpUntilElementFound(activeBtnAccId, 0.7);
        clickElement(activeBtn);
        return new ActiveBtnDialogComponent(appiumDriver);
    }

    public BottomNavBarComponent bottomNavBarComponent() {
        return new BottomNavBarComponent(appiumDriver);
    }

}
