package models.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import models.base.PageModel;

public class WebviewPage extends PageModel {

    public WebviewPage(AndroidDriver<MobileElement> androidDriver) {
        this.appiumDriver = androidDriver;
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
            return "[Label: " + label + " | " + "Href: " + href +  "]";
        }
    }
}
