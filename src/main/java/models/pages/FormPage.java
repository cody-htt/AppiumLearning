package models.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import models.base.BasePageModel;

public class FormPage extends BasePageModel {

    public FormPage(AndroidDriver<MobileElement> androidDriver) {
        this.appiumDriver = androidDriver;
    }

}
