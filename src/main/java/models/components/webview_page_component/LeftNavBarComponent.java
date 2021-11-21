package models.components.webview_page_component;

import environments.Context;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import models.base.PageModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LeftNavBarComponent extends PageModel {

    @FindBy(css = ".navbarSidebarToggle_wkoY")
    private  WebElement viewModeToggleBtn;
    @FindBy(css = ".menu__list")
    private WebElement navBarMenuListElem;
    @FindBy(css = ".menu__list-item > a")
    private WebElement menuItem;

    private final static By menuItemLoc = MobileBy.cssSelector(".menu__list-item > a");

    public LeftNavBarComponent(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
        appiumDriver.context(Context.WEBVIEW.getContext());
    }

    public WebElement viewModeToggleBtn() {
        waitForVisibility(viewModeToggleBtn);
        return viewModeToggleBtn;
    }

    public WebElement navBarMenuListElem() {
        waitForVisibility(navBarMenuListElem);
        return navBarMenuListElem;
    }

    public List<MobileElement> menuItemList() {
        waitForVisibility(menuItem);
        return appiumDriver.findElements(menuItemLoc);
    }

}
