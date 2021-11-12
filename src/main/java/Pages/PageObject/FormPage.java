package Pages.PageObject;

import Pages.Abstract.AbstractPage;
import Common.GestureActionSwipe;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class FormPage  extends AbstractPage {

    private final GestureActionSwipe actionSwipe = new GestureActionSwipe(this.androidDriver);

    public FormPage(AndroidDriver<MobileElement> androidDriver) {
        this.androidDriver = androidDriver;
    }

}
