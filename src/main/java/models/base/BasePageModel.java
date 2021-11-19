package models.base;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public abstract class BasePageModel {

    protected AndroidDriver<MobileElement> appiumDriver;

    protected void waitForVisibility(MobileElement element) {
        WebDriverWait wait = new WebDriverWait(appiumDriver, 10);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

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
