package models.pages;

import models.base.BasePageModel;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class SwipePage extends BasePageModel {

    public SwipePage(AndroidDriver<MobileElement> androidDriver) {
        this.appiumDriver = androidDriver;
    }
}
