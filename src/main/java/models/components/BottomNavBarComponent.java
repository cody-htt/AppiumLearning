package models.components;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Constant;

public class BottomNavBarComponent {

    @AndroidFindBy(accessibility = "Home")
    private MobileElement homeLabel;
    @AndroidFindBy(accessibility = "Webview")
    private MobileElement webviewLabel;
    @AndroidFindBy(accessibility = "Login")
    private MobileElement loginLabel;
    @AndroidFindBy(accessibility = "Forms")
    private MobileElement formsLabel;
    @AndroidFindBy(accessibility = "Swipe")
    private MobileElement swipeLabel;
    @AndroidFindBy(accessibility = "Drag")
    private MobileElement dragLabel;

    private final WebDriverWait wait;

    public BottomNavBarComponent(AppiumDriver<MobileElement> appiumDriver) {
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
        wait = new WebDriverWait(appiumDriver, Constant.SHORT_WAIT_TIME);
    }

    /* Context: return element mobile only */
    public MobileElement homeLabelELem() {
        waitForVisibility(homeLabel);
        return homeLabel;
    }

    public MobileElement webviewLabelElem() {
        waitForVisibility(webviewLabel);
        return webviewLabel;
    }

    public MobileElement loginLabelElem() {
        waitForVisibility(loginLabel);
        return loginLabel;
    }

    public MobileElement formsLabelElem() {
        waitForVisibility(formsLabel);
        return formsLabel;
    }

    public MobileElement swipeLabelElem() {
        waitForVisibility(swipeLabel);
        return swipeLabel;
    }

    public MobileElement dragLabelElem() {
        waitForVisibility(dragLabel);
        return dragLabel;
    }

    /* Context: provide main interaction method with Mobile Element of this page */

    public void clickOnHomeLabel() {
        waitForVisibility(homeLabel);
        homeLabel.click();
    }

    public void clickOnWebviewLabel() {
        waitForVisibility(webviewLabel);
        webviewLabel.click();
    }

    public void clickOnLoginLabel() {
        waitForVisibility(loginLabel);
        loginLabel.click();
    }

    public void clickOnFormsLabel() {
        waitForVisibility(formsLabel);
        formsLabel.click();
    }

    public void clickOnSwipeLabel() {
        waitForVisibility(swipeLabel);
        swipeLabel.click();
    }

    public void clickOnDragLabel() {
        waitForVisibility(dragLabel);
        dragLabel.click();
    }

    private void waitForVisibility(MobileElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

}
