package models.base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Constant;

import java.time.Duration;
import java.util.List;

public class PageModel {

    protected AppiumDriver<MobileElement> appiumDriver;

    protected PageModel(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
        PageFactory.initElements(new AppiumFieldDecorator(this.appiumDriver, Duration.ofSeconds(5L)), this);
    }

    protected void waitForVisibility(MobileElement element) {
        WebDriverWait wait = new WebDriverWait(appiumDriver, Constant.SHORT_WAIT_TIME);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(appiumDriver, Constant.SHORT_WAIT_TIME);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForVisibility(By locator) {
        WebElement element = appiumDriver.findElement(locator);
        waitForVisibility(element);
    }

    protected void waitForVisibility(MobileBy locator) {
        MobileElement element = appiumDriver.findElement(locator);
        waitForVisibility(element);
    }

    /* Interaction with element using MobileElement type */
    protected String getElementAttribute(MobileElement element, String attribute) {
        waitForVisibility(element);
        return element.getAttribute(attribute);
    }

    protected String getElementAttribute(By locator, String attribute) {
        MobileElement element = appiumDriver.findElement(locator);
        return getElementAttribute(element, attribute);
    }

    protected void clickElement(MobileElement element) {
        waitForVisibility(element);
        element.click();
    }

    protected void clickElement(By locator) {
        MobileElement element = appiumDriver.findElement(locator);
        clickElement(element);
    }

    protected void sendKeysToElement(MobileElement element, String text) {
        waitForVisibility(element);
        element.sendKeys(text);
    }

    protected void sendKeysToElement(By locator, String input) {
        MobileElement element = appiumDriver.findElement(locator);
        sendKeysToElement(element, input);
    }

    protected void clearElementInputField(MobileElement element) {
        waitForVisibility(element);
        element.clear();
    }

    protected void clearElementInputField(By locator) {
        MobileElement element = appiumDriver.findElement(locator);
        clearElementInputField(element);
    }

    protected String getElementText(MobileElement element) {
        waitForVisibility(element);
        return element.getText();
    }

    protected String getElementText(By locator) {
        MobileElement element = appiumDriver.findElement(locator);
        return getElementText(element);
    }

    /* Interaction with element using By type */

    private List<MobileElement> fetchElements(By locator) {
        return appiumDriver.findElements(locator);
    }

    protected boolean isElementPresent(By locator) {
        List<MobileElement> elements = appiumDriver.findElements(locator);
        return elements.size() > 0;
    }

}
