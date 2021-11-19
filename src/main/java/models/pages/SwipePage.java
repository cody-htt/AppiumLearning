package models.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import models.base.PageModel;

public class SwipePage extends PageModel {

    public SwipePage(AndroidDriver<MobileElement> androidDriver) {
        this.appiumDriver = androidDriver;
    }
}
