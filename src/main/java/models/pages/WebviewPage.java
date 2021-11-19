package models.pages;

import models.base.BasePageModel;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class WebviewPage extends BasePageModel {

    public WebviewPage(AndroidDriver<MobileElement> androidDriver) {
        this.androidDriver = androidDriver;
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
