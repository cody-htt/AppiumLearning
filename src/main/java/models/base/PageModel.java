package models.base;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Constant;

import java.util.List;

public class PageModel {

    protected AndroidDriver<MobileElement> appiumDriver;

    public PageModel() {
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
    }

    protected void waitForVisibility(MobileElement element) {
        WebDriverWait wait = new WebDriverWait(appiumDriver, Constant.SHORT_WAIT_TIME);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /* Interaction with element using MobileElement type */
    protected String getElementAttribute(MobileElement element, String attribute) {
        waitForVisibility(element);
        return element.getAttribute(attribute);
    }

    protected void clickElement(MobileElement element) {
        waitForVisibility(element);
        element.click();
    }

    protected void sendKeysToElement(MobileElement element, String text) {
        waitForVisibility(element);
        element.sendKeys(text);
    }

    protected void clearElementInputField(MobileElement element) {
        waitForVisibility(element);
        element.clear();
    }

    protected String getElementText(MobileElement element) {
        waitForVisibility(element);
        return element.getText();
    }

    /* Interaction with element using By type */
    protected void clickElementBy(By locator) {
        MobileElement element = appiumDriver.findElement(locator);
        waitForVisibility(element);
        element.click();
    }

    protected void sendKeysToElementBy(By locator , String input) {
        MobileElement element = appiumDriver.findElement(locator);
        waitForVisibility(element);
        element.sendKeys(input);
    }

    protected void clearElementInputFieldBy(By locator) {
        MobileElement element = appiumDriver.findElement(locator);
        waitForVisibility(element);
        element.clear();
    }

    protected String getElementTextBy(By locator) {
        MobileElement element = appiumDriver.findElement(locator);
        waitForVisibility(element);
        return element.getText();
    }

    protected boolean isElementPresentBy(By locator) {
        List<MobileElement> elements = appiumDriver.findElements(locator);
        return elements != null;
    }

    protected boolean isElementNotPresentBy(By locator) {
        List<MobileElement> elements = appiumDriver.findElements(locator);
        return elements == null;
    }

}
