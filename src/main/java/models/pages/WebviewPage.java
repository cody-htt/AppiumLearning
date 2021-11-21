package models.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import models.base.PageModel;
import models.components.webview_page_component.LeftNavBarComponent;

public class WebviewPage extends PageModel {

    @AndroidFindBy(xpath = "//android.view.View[@content-desc='WebdriverIO']/preceding-sibling::android.widget.Button")
    private MobileElement leftNavBarToggleBtn;
    @AndroidFindBy(xpath = "/android.view.View/android.view.View[3]/android.view.View")
    private MobileElement robotLogoElem;
    @AndroidFindBy(xpath = "/android.view.View/android.view.View[3]/android.widget.TextView")
    private MobileElement logoTextFieldElem;

    public WebviewPage(AppiumDriver<MobileElement> appiumDriver) { super(appiumDriver); }

    public MobileElement robotLogoElem() {
        waitForVisibility(robotLogoElem);
        return robotLogoElem;
    }

    public MobileElement logoTextFieldElem() {
        waitForVisibility(logoTextFieldElem);
        return logoTextFieldElem;
    }

    public LeftNavBarComponent clickOnLeftNavBarToggleBtn() {
        clickElement(leftNavBarToggleBtn);
        return new LeftNavBarComponent(appiumDriver);
    }

    public static class MenuItem {
        private final String label;
        private final String href;

        public MenuItem(String label, String href) {
            this.label = label;
            this.href = href;
        }

        @Override
        public String toString() {
            return "[Label: " + label + " | " + "Href: " + href + "]";
        }
    }
}
