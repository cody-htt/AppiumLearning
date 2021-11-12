package Common.BuitInAction;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import java.time.Duration;
import java.util.List;

public class SwipeAction {

    private final double SCREEN_SIZE_PERCENTAGE = 1.0D;
    private final int MID_POINT_FACTOR = 2;

    private AndroidDriver<MobileElement> androidDriver;
    private Dimension mobileScreenSize;
    private TouchAction touchAction;
    private double percentage;

    public SwipeAction(AndroidDriver<MobileElement> androidDriver, double percentage, boolean isVerticallySwipe) {
        this(androidDriver);
        this.percentage = percentage;
    }

    public SwipeAction(AndroidDriver<MobileElement> androidDriver) {
        this.androidDriver = androidDriver;
        this.mobileScreenSize = androidDriver.manage().window().getSize();
        this.touchAction = new TouchAction(androidDriver);
    }

    /* The following methods perform swipe from a visible element to another visible element */
    public void swipeToElement(By fromLocator, By toLocator) {
        ElementOption fromElement = new ElementOption().withElement(androidDriver.findElement(fromLocator));
        ElementOption toElement = new ElementOption().withElement(androidDriver.findElement(toLocator));
        performSwipe(fromElement, toElement);
    }

    /* The following methods perform swipe up action */
    public void swipeUpUntilElementFound(By locator, int maxSwipeTimes) {
        List<MobileElement> elements = androidDriver.findElements(locator);
        int swipeTime = 0;
        PointOption startPoint = getSwipeUpStartPoint();
        PointOption endPoint = getSwipeUpEndPoint();
        while ((swipeTime < maxSwipeTimes) && elements.isEmpty()) {
            performSwipe(startPoint, endPoint);
            swipeTime++;
        }
    }

    public void swipeUpUntilElementFound(By locator) {
        List<MobileElement> elements = androidDriver.findElements(locator);
        PointOption startPoint = getSwipeUpStartPoint();
        PointOption endPoint = getSwipeUpEndPoint();
        while (elements.isEmpty()) {
            performSwipe(startPoint, endPoint);
        }
    }

    /* The following methods will return the vertical points for swipe up action */
    private PointOption getSwipeUpStartPoint() {
        int xStartPoint = mobileScreenSize.width / MID_POINT_FACTOR;
        int yStartPoint = ( int ) (mobileScreenSize.height * percentage);
        return new PointOption().withCoordinates(xStartPoint, yStartPoint);
    }

    private PointOption getSwipeUpEndPoint() {
        int xEndPoint = mobileScreenSize.width / MID_POINT_FACTOR;
        int yEndPoint = ( int ) (mobileScreenSize.height * (SCREEN_SIZE_PERCENTAGE - percentage));
        return new PointOption().withCoordinates(xEndPoint, yEndPoint);
    }

    /* The following methods perform swipe down action */
    public void swipeDownUntilElementFound(By locator, int maxSwipeTimes) {
        List<MobileElement> elements = androidDriver.findElements(locator);
        int swipeTime = 0;
        PointOption startPoint = getSwipeDownStartPoint();
        PointOption endPoint = getSwipeDownEndPoint();
        while ((swipeTime < maxSwipeTimes) && elements.isEmpty()) {
            performSwipe(startPoint, endPoint);
            swipeTime++;
        }
    }

    public void swipeDownUntilElementFound(By locator) {
        List<MobileElement> elements = androidDriver.findElements(locator);
        PointOption startPoint = getSwipeDownStartPoint();
        PointOption endPoint = getSwipeDownEndPoint();
        while (elements.isEmpty()) {
            performSwipe(startPoint, endPoint);
        }
    }

    /* The following methods will return the vertical points for swipe up action */
    private PointOption getSwipeDownStartPoint() {
        int xStartPoint = mobileScreenSize.width / MID_POINT_FACTOR;
        int yStartPoint = ( int ) (mobileScreenSize.height * (SCREEN_SIZE_PERCENTAGE - percentage));
        return new PointOption().withCoordinates(xStartPoint, yStartPoint);
    }

    private PointOption getSwipeDownEndPoint() {
        int xEndPoint = mobileScreenSize.width / MID_POINT_FACTOR;
        int yEndPoint = ( int ) (mobileScreenSize.height * percentage);
        return new PointOption().withCoordinates(xEndPoint, yEndPoint);
    }

    /* The following methods perform swipe to left action */
    public void swipeLeftUntilElementFound(By locator, int maxSwipeTimes) {
        List<MobileElement> elements = androidDriver.findElements(locator);
        int swipeTime = 0;
        PointOption startPoint = getSwipeToLeftStartPoint();
        PointOption endPoint = getSwipeToLeftEndPoint();
        while ((swipeTime < maxSwipeTimes) && elements.isEmpty()) {
            performSwipe(startPoint, endPoint);
            swipeTime++;
        }
    }

    public void swipeLeftUntilElementFound(By locator) {
        List<MobileElement> elements = androidDriver.findElements(locator);
        PointOption startPoint = getSwipeToLeftStartPoint();
        PointOption endPoint = getSwipeToLeftEndPoint();
        while (elements.isEmpty()) {
            performSwipe(startPoint, endPoint);
        }
    }

    /* The following methods will return the vertical points for swipe up action */
    private PointOption getSwipeToLeftStartPoint() {
        int xStartPoint = ( int ) (mobileScreenSize.height * percentage);
        int yStartPoint = mobileScreenSize.width / MID_POINT_FACTOR;
        return new PointOption().withCoordinates(xStartPoint, yStartPoint);
    }

    private PointOption getSwipeToLeftEndPoint() {
        int xEndPoint = ( int ) (mobileScreenSize.height * (SCREEN_SIZE_PERCENTAGE - percentage));
        int yEndPoint = mobileScreenSize.width / MID_POINT_FACTOR;
        return new PointOption().withCoordinates(xEndPoint, yEndPoint);
    }

    /* The following methods perform swipe to right action */
    public void swipeRightUntilElementFound(By locator, int maxSwipeTimes) {
        List<MobileElement> elements = androidDriver.findElements(locator);
        int swipeTime = 0;
        PointOption startPoint = getSwipeToRightStartPoint();
        PointOption endPoint = getSwipeToRightEndPoint();
        while ((swipeTime < maxSwipeTimes) && elements.isEmpty()) {
            performSwipe(startPoint, endPoint);
            swipeTime++;
        }
    }

    public void swipeRightUntilElementFound(By locator) {
        List<MobileElement> elements = androidDriver.findElements(locator);
        PointOption startPoint = getSwipeToRightStartPoint();
        PointOption endPoint = getSwipeToRightEndPoint();
        while (elements.isEmpty()) {
            performSwipe(startPoint, endPoint);
        }
    }

    /* The following methods will return the vertical points for swipe to right action */
    private PointOption getSwipeToRightStartPoint() {
        int xStartPoint = ( int ) (mobileScreenSize.height * (SCREEN_SIZE_PERCENTAGE - percentage));
        int yStartPoint = mobileScreenSize.width / MID_POINT_FACTOR;
        return new PointOption().withCoordinates(xStartPoint, yStartPoint);
    }

    private PointOption getSwipeToRightEndPoint() {
        int xEndPoint = ( int ) (mobileScreenSize.height * percentage);
        int yEndPoint = mobileScreenSize.width / MID_POINT_FACTOR;
        return new PointOption().withCoordinates(xEndPoint, yEndPoint);
    }

    /* This method will simply perform a swipe action */
    private void performSwipe(PointOption startPoint, PointOption endPoint) {
        touchAction.press(startPoint)
                .waitAction(new WaitOptions().withDuration(Duration.ofMillis(1500)))
                .moveTo(endPoint)
                .release().perform();
    }
}
