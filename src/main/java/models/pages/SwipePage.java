package models.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import models.base.BasePageModel;

public class SwipePage extends BasePageModel {

    public SwipePage(AndroidDriver<MobileElement> androidDriver) {
        this.appiumDriver = androidDriver;
    }
}
