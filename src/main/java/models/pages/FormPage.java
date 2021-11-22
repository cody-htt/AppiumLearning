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
    private MobileElement inputFieldElem;
    @AndroidFindBy(accessibility = "input-text-result")
    private MobileElement inputTextResultElem;
    @AndroidFindBy(accessibility = "switch")
    private MobileElement switchBtnElem;
    @AndroidFindBy(accessibility = "switch-text")
    private MobileElement switchTextElem;
    @AndroidFindBy(accessibility = "Dropdown")
    private MobileElement dropDownFieldContainerElem;
    @AndroidFindBy(xpath = "//*[@resource-id='icon_container']")
    private MobileElement dropDownIconElem;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='Dropdown']//android.widget.EditText")
    private MobileElement dropDownInputFieldElem;
    @AndroidFindBy(accessibility = "button-Active")
    private MobileElement activeBtnElem;

    /* By Locator */
    private final static By activeBtnLoc = MobileBy.AccessibilityId("button-Active");

    private final SwipeUtils swipeUtils;

    public FormPage(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
        swipeUtils = new SwipeUtils(this.appiumDriver);
    }

    public MobileElement inputTextResultElem() {
        waitForVisibility(inputTextResultElem);
        return inputTextResultElem;
    }

    public MobileElement switchTextElem() {
        waitForVisibility(switchTextElem);
        return switchTextElem;
    }

    public MobileElement dropDownInputFieldElem() {
        waitForVisibility(dropDownInputFieldElem);
        return dropDownInputFieldElem;
    }

    public FormPage inputField(String text) {
        sendKeysToElement(inputFieldElem, text);
        return this;
    }

    public FormPage clickOnSwitchBtn() {
        clickElement(switchBtnElem);
        return this;
    }

    public DropdownDialogComponent clickOnDropDownIcon() {
        clickElement(dropDownIconElem);
        return new DropdownDialogComponent(appiumDriver);
    }

    public ActiveBtnDialogComponent clickOnActiveBtn() {
        swipeUtils.swipeUpUntilElementFound(activeBtnLoc, 0.8);
        clickElement(activeBtnElem);
        return new ActiveBtnDialogComponent(appiumDriver);
    }

    public BottomNavBarComponent bottomNavBarComponent() {
        return new BottomNavBarComponent(appiumDriver);
    }

}
