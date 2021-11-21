package AppiumLearning;

import driver.DriverFactoryRD;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import models.components.forms_page_component.ActiveBtnDialogComponent;
import models.components.forms_page_component.DropdownDialogComponent;
import models.pages.FormPage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TestLab_15 {
    private final static List<String> resultList = new ArrayList<>();
    private final static List<String> dropDownListItem = new ArrayList<>();

    public static void main(String[] args) {
        DriverFactoryRD.startAppiumServer();
        AndroidDriver<MobileElement> androidDriver = DriverFactoryRD.getAndroidDriver();

        /* Test Swipe Action on Form Page */
        FormPage formPage = new FormPage(androidDriver);
        DropdownDialogComponent dropdownDialogComp;
        ActiveBtnDialogComponent activeBtnDialogComp;

        formPage.bottomNavBarComponent().clickOnFormsLabel();

        if (formPage.inputField("Tung").verifyTextResult("Tung")) {
            resultList.add("Test input text field - Pass");
        } else { resultList.add("Test input text field - Fail"); }

        formPage.clickOnSwitchBtn();
        resultList.add(formPage.switchText().getText());
        formPage.clickOnSwitchBtn();
        resultList.add(formPage.switchText().getText());

        dropdownDialogComp = formPage.clickOnDropDownIcon();
        dropdownDialogComp.dialogListItems().forEach(item -> {
            dropDownListItem.add(item.getText());
        });

        AtomicInteger indexOfItem = new AtomicInteger(1);
        dropDownListItem.forEach(item -> {
            dropdownDialogComp.getItemFromList(indexOfItem.get()).click();
            if (formPage.dropDownField().getText().equalsIgnoreCase(item)) {
                resultList.add(item + " is displayed");
            }
            formPage.clickOnDropDownIcon();
            indexOfItem.incrementAndGet();
        });

        activeBtnDialogComp = formPage.clickOnActiveBtn();
        activeBtnDialogComp.okBtn().click();

        /* Test Swipe Action on Swipe Page */

    }
}
