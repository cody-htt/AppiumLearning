package models.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.base.PageModel;

public class WebviewPage extends PageModel {

    public WebviewPage(AppiumDriver<MobileElement> appiumDriver) { super(appiumDriver); }

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
