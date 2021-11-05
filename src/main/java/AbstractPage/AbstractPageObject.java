package AbstractPage;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractPageObject {

    private final AndroidDriver<MobileElement> androidDriver;

    protected AbstractPageObject(AndroidDriver<MobileElement> androidDriver) {
        this.androidDriver = androidDriver;
    }

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
        WebDriverWait wait = new WebDriverWait(androidDriver , 15);
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            MobileElement element = androidDriver.findElement(locator);
            return true;
        } catch (NoSuchElementException | TimeoutException ex) {
            return false;
        }
    }

}
