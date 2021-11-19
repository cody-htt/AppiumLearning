package AppiumLearning;

import utils.SwipeUtils;
import driver.DriverFactoryRD;
import models.pages.FormPageElem;
import models.pages.SwipePageElem;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TestLab_15 {

    public static void main(String[] args) {
        DriverFactoryRD.startAppiumServer();
        AndroidDriver<MobileElement> androidDriver = DriverFactoryRD.getAndroidDriver();

        /* Test Swipe Action on Form Page */
        SwipeUtils swipeUtils = new SwipeUtils(androidDriver);

        androidDriver.findElement(FormPageElem.ACC_ID_LABEL_FORM_BTN).click();
        swipeUtils.swipeToElement(FormPageElem.ACC_ID_SWITCH_TEXT, FormPageElem.ACC_ID_INPUT_FIELD_RESULT);
        swipeUtils.swipeUpUntilElementFound(FormPageElem.XPATH_FORM_LABEL, 0.2, 3);
        swipeUtils.swipeDownUntilElementFound(FormPageElem.ACC_ID_ACTIVE_BTN, 0.2);

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
            swipeUtils.swipeToLeft(0.7);
        });

        AtomicInteger index = new AtomicInteger(0);
        cardTextList.forEach(text -> {
            if (text.equalsIgnoreCase(SwipePageElem.CARD_TEXT.get(index.get()))) {
                result.add("Card " + text + " is equal to expected");
            } else { result.add("Card " + text + " is not equal to expected"); }
            index.incrementAndGet();
        });

        swipeUtils.swipeUpUntilElementFound(SwipePageElem.ACC_ID_LOGO, 0.7);
        MobileElement textUnderLogo = androidDriver.findElement(SwipePageElem.XPATH_TEXT_LOGO);
        if (textUnderLogo.isDisplayed()) {
            result.add("Text [" + textUnderLogo.getText() + "] is found");
        } else {
            result.add("Text is not found");
        }

        DriverFactoryRD.stopAppiumServer();

        result.forEach(System.out :: println);
    }
}
