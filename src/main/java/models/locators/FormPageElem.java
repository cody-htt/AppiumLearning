package models.locators;

import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

public class FormPageElem {
    public final static By ACC_ID_LABEL_FORM_BTN = MobileBy.AccessibilityId("Forms");
    public final static By ACC_ID_INPUT_FIELD = MobileBy.AccessibilityId("text-input");
    public final static By ACC_ID_INPUT_FIELD_RESULT = MobileBy.AccessibilityId("input-text-result");
    public final static By ACC_ID_SWITCH_BTN = MobileBy.AccessibilityId("switch");
    public final static By ACC_ID_SWITCH_TEXT = MobileBy.AccessibilityId("switch-text");
    public final static By ACC_ID_ACTIVE_BTN = MobileBy.AccessibilityId("button-Active");
    public final static By XPATH_DROPDOWN_ITEM = MobileBy.xpath("//android.view.ViewGroup" +
            "[@content-desc='Dropdown']/android.view.ViewGroup/android.widget.EditText");
    public final static By XPATH_FORM_LABEL = MobileBy.xpath("//*[@text='Form components']");
    public final static By ID_DROPDOWN_VALUES = MobileBy.id("android:id/text1");
    public final static By ID_ACTIVE_BTN_MSG = MobileBy.id("android:id/message");
    public final static By CLASS_ACTIVE_MODAL_WINDOW_BTN = MobileBy.className("android.widget.Button");

    public final static String DROPDOWN_VALUES_1 = "Select an item...";
    public final static String DROPDOWN_VALUES_2 = "webdriver.io is awesome";
    public final static String DROPDOWN_VALUES_3 = "Appium is awesome";
    public final static String DROPDOWN_VALUES_4 = "This app is awesome";
}
