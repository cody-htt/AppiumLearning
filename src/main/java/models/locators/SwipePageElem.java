package models.locators;

import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.Arrays;

public class SwipePageElem {

    public final static By ACC_ID_LABEL_SWIPE_BTN = MobileBy.AccessibilityId("Swipe");
    public final static By XPATH_CARD_1 = MobileBy.xpath("(//*[@content-desc='slideTextContainer'])[1]/android.widget.TextView[1]");
    public final static By XPATH_CARD_2 = MobileBy.xpath("(//*[@content-desc='slideTextContainer'])[2]/android.widget.TextView[1]");
    public final static By XPATH_CARD_3 = MobileBy.xpath("(//*[@content-desc='slideTextContainer'])[2]/android.widget.TextView[1]");
    public final static By XPATH_CARD_4 = MobileBy.xpath("(//*[@content-desc='slideTextContainer'])[2]/android.widget.TextView[1]");
    public final static By XPATH_CARD_5 = MobileBy.xpath("(//*[@content-desc='slideTextContainer'])[2]/android.widget.TextView[1]");
    public final static By XPATH_CARD_6 = MobileBy.xpath("(//*[@content-desc='slideTextContainer'])[2]/android.widget.TextView[1]");
    public final static By ACC_ID_LOGO = MobileBy.AccessibilityId("WebdriverIO logo");
    public final static By XPATH_TEXT_LOGO = MobileBy.xpath("//*[@content-desc='Swipe-screen']/android.view.ViewGroup/android.widget.TextView");

    public final static ArrayList<String> CARD_TEXT = new ArrayList<>(Arrays.asList("FULLY OPEN SOURCE",
                                                                                        "GREAT COMMUNITY",
                                                                                        "JS.FOUNDATION",
                                                                                        "SUPPORT VIDEOS",
                                                                                        "EXTENDABLE",
                                                                                        "COMPATIBLE"));
}
