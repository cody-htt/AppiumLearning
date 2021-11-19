package models.PageObject;

import models.Abstract.AbstractPage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class SwipePage extends AbstractPage {

    public SwipePage(AndroidDriver<MobileElement> androidDriver) {
        this.androidDriver = androidDriver;
    }
}
