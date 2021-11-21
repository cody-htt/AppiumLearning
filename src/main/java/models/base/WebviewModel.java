package models.base;

import environments.Context;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.WebDriver;

public class WebviewModel extends PageModel {

    protected WebDriver webDriver;

    public WebviewModel(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
        this.webDriver = appiumDriver.context(Context.WEBVIEW.getContext());
    }
}
