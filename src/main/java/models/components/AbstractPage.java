package models.components;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

import java.util.List;

public abstract class AbstractPage {

    protected AndroidDriver<MobileElement> androidDriver;

    protected void clickOnElement(By locator) {
        MobileElement element = androidDriver.findElement(locator);
        element.click();
    }

    protected void fillTextBox(By locator , String input) {
        MobileElement element = androidDriver.findElement(locator);
        element.sendKeys(input);
    }

    protected void clearTextBox(By locator) {
        MobileElement element = androidDriver.findElement(locator);
        element.clear();
    }

    protected String getElementText(By locator) {
        MobileElement element = androidDriver.findElement(locator);
        return element.getText();
    }

    protected boolean isElementPresent(By locator) {
        List<MobileElement> elements = androidDriver.findElements(locator);
        return elements != null;
    }

    protected boolean isElementNotPresent(By locator) {
        List<MobileElement> elements = androidDriver.findElements(locator);
        return elements == null;
    }

}
