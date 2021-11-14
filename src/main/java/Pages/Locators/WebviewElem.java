package Pages.Locators;

import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

public class WebviewElem {

    public final static By WEBVIEW_LABEL_BTN = MobileBy.AccessibilityId("Webview");
    public final static By WEBVIEW_ROBOT_IMAGE = MobileBy.xpath("//android.view.View[@resource-id='__docusaurus']/android.view" +
            ".View[3]/android.view.View");

    /* Web view locator */
    public final static By ROBOT_IMAGE = By.cssSelector("h1.hero__title");
    public final static By TOP_LEFT_NAVBAR_TOGGLE = By.cssSelector(".navbar__toggle > svg");
    public final static By NAVBAR_MENU_LIST = By.cssSelector(".menu__list");
    public final static By ITEM_LINKS = By.cssSelector(".menu__list-item > a");

    public final static String ROBOT_IMAGE_HEADING_TEXT = "WebdriverIO";

}
