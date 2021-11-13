package AppiumLearning;

import Common.BuitInAction.SwipeAction;
import MobileDriver.RealDeviceDriverFactory;
import Pages.Locators.FormPageElem;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class TestFormPage {

    public static void main(String[] args) {
        RealDeviceDriverFactory.startAppiumServer();
        AndroidDriver<MobileElement> androidDriver = RealDeviceDriverFactory.getAndroidDriver();

        SwipeAction swipeAction = new SwipeAction(androidDriver);

        androidDriver.findElement(FormPageElem.ACC_ID_LABEL_FORM_BTN).click();
        swipeAction.swipeToElement(FormPageElem.ACC_ID_SWITCH_TEXT, FormPageElem.ACC_ID_INPUT_FIELD_RESULT);
        swipeAction.swipeUpUntilElementFound(FormPageElem.XPATH_FORM_LABEL, 0.2);
        swipeAction.swipeDownUntilElementFound(FormPageElem.ACC_ID_ACTIVE_BTN, 0.2);

        RealDeviceDriverFactory.stopAppiumServer();
    }
}
