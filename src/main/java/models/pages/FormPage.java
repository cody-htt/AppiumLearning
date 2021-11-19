package models.pages;

import models.components.AbstractPage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class FormPage extends AbstractPage {

    public FormPage(AndroidDriver<MobileElement> androidDriver) {
        this.androidDriver = androidDriver;
    }

}
