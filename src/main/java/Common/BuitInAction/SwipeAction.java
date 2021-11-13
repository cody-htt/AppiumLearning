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
    private final int SHORT_WAIT_TIME = 800;
    private final int LONG_WAIT_TIME = 1500;

    private AndroidDriver<MobileElement> androidDriver;
    private Dimension mobileScreenSize;
    private TouchAction touchAction;

    public SwipeAction(AndroidDriver<MobileElement> androidDriver) {
        this.androidDriver = androidDriver;
        this.mobileScreenSize = androidDriver.manage().window().getSize();
        this.touchAction = new TouchAction(androidDriver);
    }

    /* The following methods perform swipe from a visible element to another visible element */
    public void swipeToElement(By fromLocator, By toLocator) {
        ElementOption fromElement = new ElementOption().withElement(androidDriver.findElement(fromLocator));
        ElementOption toElement = new ElementOption().withElement(androidDriver.findElement(toLocator));
        performSwipe(fromElement, toElement, LONG_WAIT_TIME);
    }

    /* The following methods perform swipe up action */
    public void swipeUpUntilElementFound(By locator, double percentage, int maxSwipeTimes) {
        PointOption startPoint = getSwipeUpStartPoint(percentage);
        PointOption endPoint = getSwipeUpEndPoint(percentage);
        swipeUntilElementFound(locator, maxSwipeTimes, startPoint, endPoint);
    }

    public void swipeUpUntilElementFound(By locator, double percentage) {
        PointOption startPoint = getSwipeUpStartPoint(percentage);
        PointOption endPoint = getSwipeUpEndPoint(percentage);
        swipeUntilElementFound(locator, startPoint, endPoint);
    }

    /* The following methods will return the vertical points for swipe up action */
    private PointOption getSwipeUpStartPoint(double percentage) {
        int xStartPoint = mobileScreenSize.width / MID_POINT_FACTOR;
        int yStartPoint = ( int ) (mobileScreenSize.height * percentage);
        return new PointOption().withCoordinates(xStartPoint, yStartPoint);
    }
    private PointOption getSwipeUpEndPoint(double percentage) {
        int xEndPoint = mobileScreenSize.width / MID_POINT_FACTOR;
        int yEndPoint = ( int ) (mobileScreenSize.height * (SCREEN_SIZE_PERCENTAGE - percentage));
        return new PointOption().withCoordinates(xEndPoint, yEndPoint);
    }

    /* The following methods perform swipe down action */
    public void swipeDownUntilElementFound(By locator, double percentage, int maxSwipeTimes) {
        PointOption startPoint = getSwipeDownStartPoint(percentage);
        PointOption endPoint = getSwipeDownEndPoint(percentage);
        swipeUntilElementFound(locator, maxSwipeTimes, startPoint, endPoint);
    }

    public void swipeDownUntilElementFound(By locator, double percentage) {
        PointOption startPoint = getSwipeDownStartPoint(percentage);
        PointOption endPoint = getSwipeDownEndPoint(percentage);
        swipeUntilElementFound(locator, startPoint, endPoint);
    }

    /* The following methods will return the vertical points for swipe up action */
    private PointOption getSwipeDownStartPoint(double percentage) {
        int xStartPoint = mobileScreenSize.width / MID_POINT_FACTOR;
        int yStartPoint = ( int ) (mobileScreenSize.height * (SCREEN_SIZE_PERCENTAGE - percentage));
        return new PointOption().withCoordinates(xStartPoint, yStartPoint);
    }

    private PointOption getSwipeDownEndPoint(double percentage) {
        int xEndPoint = mobileScreenSize.width / MID_POINT_FACTOR;
        int yEndPoint = ( int ) (mobileScreenSize.height * percentage);
        return new PointOption().withCoordinates(xEndPoint, yEndPoint);
    }

    /* The following methods perform swipe to left action */
    public void swipeLeftUntilElementFound(By locator, double percentage, int maxSwipeTimes) {
        PointOption startPoint = getSwipeToLeftStartPoint(percentage);
        PointOption endPoint = getSwipeToLeftEndPoint(percentage);
        swipeUntilElementFound(locator, maxSwipeTimes, startPoint, endPoint);
    }

    public void swipeLeftUntilElementFound(By locator, double percentage) {
        PointOption startPoint = getSwipeToLeftStartPoint(percentage);
        PointOption endPoint = getSwipeToLeftEndPoint(percentage);
        swipeUntilElementFound(locator, startPoint, endPoint);
    }

    /* The following methods will return the vertical points for swipe up action */
    private PointOption getSwipeToLeftStartPoint(double percentage) {
        int xStartPoint = ( int ) (mobileScreenSize.height * percentage);
        int yStartPoint = mobileScreenSize.width / MID_POINT_FACTOR;
        return new PointOption().withCoordinates(xStartPoint, yStartPoint);
    }

    private PointOption getSwipeToLeftEndPoint(double percentage) {
        int xEndPoint = ( int ) (mobileScreenSize.height * (SCREEN_SIZE_PERCENTAGE - percentage));
        int yEndPoint = mobileScreenSize.width / MID_POINT_FACTOR;
        return new PointOption().withCoordinates(xEndPoint, yEndPoint);
    }

    /* The following methods perform swipe to right action */
    public void swipeRightUntilElementFound(By locator, double percentage, int maxSwipeTimes) {
        PointOption startPoint = getSwipeToRightStartPoint(percentage);
        PointOption endPoint = getSwipeToRightEndPoint(percentage);
        swipeUntilElementFound(locator, maxSwipeTimes, startPoint, endPoint);
    }

    public void swipeRightUntilElementFound(By locator, double percentage) {
        PointOption startPoint = getSwipeToRightStartPoint(percentage);
        PointOption endPoint = getSwipeToRightEndPoint(percentage);
        swipeUntilElementFound(locator, startPoint, endPoint);
    }

    private void swipeUntilElementFound(By locator, PointOption startPoint, PointOption endPoint) {
        boolean continueToSwipe = true;
        while (continueToSwipe) {
            continueToSwipe = isElementFound(locator);
            performSwipe(startPoint, endPoint, SHORT_WAIT_TIME);
        }
    }

    private void swipeUntilElementFound(By locator, int maxSwipeTimes, PointOption startPoint, PointOption endPoint) {
        int swipeTime = 0;
        boolean continueToSwipe = true;
        while ((swipeTime < maxSwipeTimes) && continueToSwipe) {
            continueToSwipe = isElementFound(locator);
            performSwipe(startPoint, endPoint, LONG_WAIT_TIME);
            swipeTime++;
        }
    }

    private boolean isElementFound(By locator) {
        List<MobileElement> elements = androidDriver.findElements(locator);
        return elements.isEmpty();
    }

    /* The following methods will return the vertical points for swipe to right action */
    private PointOption getSwipeToRightStartPoint(double percentage) {
        int xStartPoint = ( int ) (mobileScreenSize.height * (SCREEN_SIZE_PERCENTAGE - percentage));
        int yStartPoint = mobileScreenSize.width / MID_POINT_FACTOR;
        return new PointOption().withCoordinates(xStartPoint, yStartPoint);
    }

    private PointOption getSwipeToRightEndPoint(double percentage) {
        int xEndPoint = ( int ) (mobileScreenSize.height * percentage);
        int yEndPoint = mobileScreenSize.width / MID_POINT_FACTOR;
        return new PointOption().withCoordinates(xEndPoint, yEndPoint);
    }

    /* This method will simply perform a swipe action */
    private void performSwipe(PointOption startPoint, PointOption endPoint, int waitTime) {
        touchAction.press(startPoint)
                .waitAction(new WaitOptions().withDuration(Duration.ofMillis(waitTime)))
                .moveTo(endPoint)
                .release().perform();
    }
}
