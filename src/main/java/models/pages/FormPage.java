package models.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import models.base.PageModel;

public class FormPage extends PageModel {

    public FormPage(AndroidDriver<MobileElement> androidDriver) {
        this.appiumDriver = androidDriver;
    }

}
