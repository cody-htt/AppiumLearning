package AppiumLearning;

import utils.touchUtils.SwipeAction;
import driver.RealDeviceDriverFactory;
import models.locators.FormPageElem;
import models.locators.SwipePageElem;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TestLab_15 {

    public static void main(String[] args) {
        RealDeviceDriverFactory.startAppiumServer();
        AndroidDriver<MobileElement> androidDriver = RealDeviceDriverFactory.getAndroidDriver();

        /* Test Swipe Action on Form Page */
        SwipeAction swipeAction = new SwipeAction(androidDriver);

        androidDriver.findElement(FormPageElem.ACC_ID_LABEL_FORM_BTN).click();
        swipeAction.swipeToElement(FormPageElem.ACC_ID_SWITCH_TEXT, FormPageElem.ACC_ID_INPUT_FIELD_RESULT);
        swipeAction.swipeUpUntilElementFound(FormPageElem.XPATH_FORM_LABEL, 0.2, 3);
        swipeAction.swipeDownUntilElementFound(FormPageElem.ACC_ID_ACTIVE_BTN, 0.2);

        /* Test Swipe Action on Swipe Page */
        androidDriver.findElement(SwipePageElem.ACC_ID_LABEL_SWIPE_BTN).click();
        List<String> result = new ArrayList<>();
        List<String> cardTextList = new ArrayList<>();
        List<By> cardLocatorList = new ArrayList<>();
        cardLocatorList.add(SwipePageElem.XPATH_CARD_1);
        cardLocatorList.add(SwipePageElem.XPATH_CARD_2);
        cardLocatorList.add(SwipePageElem.XPATH_CARD_3);
        cardLocatorList.add(SwipePageElem.XPATH_CARD_4);
        cardLocatorList.add(SwipePageElem.XPATH_CARD_5);
        cardLocatorList.add(SwipePageElem.XPATH_CARD_6);

        cardLocatorList.forEach(locator -> {
            cardTextList.add(androidDriver.findElement(locator).getText());
            swipeAction.swipeToLeft(0.7);
        });

        AtomicInteger index = new AtomicInteger(0);
        cardTextList.forEach(text -> {
            if (text.equalsIgnoreCase(SwipePageElem.CARD_TEXT.get(index.get()))) {
                result.add("Card " + text + " is equal to expected");
            } else { result.add("Card " + text + " is not equal to expected"); }
            index.incrementAndGet();
        });

        swipeAction.swipeUpUntilElementFound(SwipePageElem.ACC_ID_LOGO, 0.7);
        MobileElement textUnderLogo = androidDriver.findElement(SwipePageElem.XPATH_TEXT_LOGO);
        if (textUnderLogo.isDisplayed()) {
            result.add("Text [" + textUnderLogo.getText() + "] is found");
        } else {
            result.add("Text is not found");
        }

        RealDeviceDriverFactory.stopAppiumServer();

        result.forEach(System.out :: println);
    }
}
