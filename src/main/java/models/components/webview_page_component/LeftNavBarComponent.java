package models.components.webview_page_component;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.base.WebviewModel;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LeftNavBarComponent extends WebviewModel {

    @FindBy(css = ".menu__list")
    private WebElement menuListItems;
    @FindBy(css = "\".menu__list-item > a\"")
    public WebElement menuItem;

    public LeftNavBarComponent(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
    }

}
