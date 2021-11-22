package models.components.forms_page_component;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import models.base.PageModel;

import java.util.List;

public class DropdownDialogComponent extends PageModel {
    @AndroidFindBy(xpath = "//*[@resource-id='com.wdiodemoapp:id/select_dialog_listview']")
    private MobileElement dropDownListViewDialog;
    @AndroidFindBy(xpath = "//*[@resource-id='com.wdiodemoapp:id/select_dialog_listview']/android.widget.CheckedTextView")
    private List<MobileElement> dialogListItems;

    public DropdownDialogComponent(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
    }

    public MobileElement getItemFromList(int index) {
        return dialogListItems.get(index);
    }

    public MobileElement dropDownListViewDialog() {
        waitForVisibility(dropDownListViewDialog);
        return dropDownListViewDialog;
    }

    public List<MobileElement> dialogListItems() {
        return dialogListItems;
    }

}
