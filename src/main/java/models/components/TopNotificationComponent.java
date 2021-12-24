package models.components;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import models.base.PageModel;
import org.openqa.selenium.By;

import java.util.List;

public class TopNotificationComponent extends PageModel {

    private final By notiBannerLoc = MobileBy.id("android:id/status_bar_latest_event_content");
    private final By notiAppNameLoc = MobileBy.id("android:id/app_name_text");
    private final By notiTitleLoc = MobileBy.id("android:id/title");
    private final By notiTextLoc = MobileBy.id("android:id/text");
    private final By notiSystemTextLoc = MobileBy.id("android:id/big_text");

    public TopNotificationComponent(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
    }

    public By notiApp() {
        waitForVisibility(notiAppNameLoc);
        return notiAppNameLoc;
    }

    public By notiTitle() {
        waitForVisibility(notiTitleLoc);
        return notiTitleLoc;
    }

    public By notiText() {
        waitForVisibility(notiTextLoc);
        return notiTextLoc;
    }

    public By notiSystemTextLoc() {
        waitForVisibility(notiSystemTextLoc);
        return notiSystemTextLoc;
    }

    public List<MobileElement> notiBannerList() {
        return this.appiumDriver.findElements(notiBannerLoc);
    }

    public static class notification {
        private final String appName;
        private final String notiTitle;
        private final String notiText;

        public notification(String appName, String notiTitle, String notiText) {
            this.appName = appName;
            this.notiTitle = notiTitle;
            this.notiText = notiText;
        }

        @Override
        public String toString() {
            return "App name: " + appName + "\n\t" +
                   "Title: " + notiTitle + "\n\t" +
                   "Text: " + notiText;
        }
    }

}
