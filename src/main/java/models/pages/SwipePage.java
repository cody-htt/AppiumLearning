package models.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import models.base.PageModel;
import models.components.BottomNavBarComponent;
import org.openqa.selenium.By;
import utils.SwipeUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SwipePage extends PageModel {
    public final List<String> CARD_TEXT = new ArrayList<>(
            Arrays.asList("FULLY OPEN SOURCE", "GREAT COMMUNITY", "JS.FOUNDATION", "SUPPORT VIDEOS", "EXTENDABLE", "COMPATIBLE"));

    @AndroidFindBy(xpath = "(//*[@content-desc='slideTextContainer'])[1]/android.widget.TextView")
    private MobileElement cardElem;
    @AndroidFindBy(accessibility = "WebdriverIO logo")
    private MobileElement webDriverIOLogo;
    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='WebdriverIO logo']/following-sibling::android.widget.TextView")
    private MobileElement getWebDriverIOLogoText;

    private final By webDriverIOLogoLocator = MobileBy.AccessibilityId("WebdriverIO logo");

    private List<MobileElement> cardElemList;
    private final SwipeUtils swipeUtils;

    public SwipePage(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
        swipeUtils = new SwipeUtils(this.appiumDriver);
    }

    public List<MobileElement> cardElemList() {
        getCardElemList();
        return cardElemList;
    }

    private void getCardElemList() {
        CARD_TEXT.forEach(card -> {
            swipeUtils.swipeToLeft(0.7);
            cardElemList.add(cardElem);
        });
    }

    public MobileElement webDriverIOLogo() {
        swipeUtils.swipeUpUntilElementFound(webDriverIOLogoLocator, 0.7);
        return webDriverIOLogo;
    }

    public BottomNavBarComponent bottomNavBarComponent() {
        return new BottomNavBarComponent(appiumDriver);
    }
}
