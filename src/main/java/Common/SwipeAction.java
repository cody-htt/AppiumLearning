package Common;

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

    private AndroidDriver<MobileElement> androidDriver;
    private Dimension mobileScreenSize;
    private TouchAction touchAction;
    private int xStartPoint;
    private int xEndPoint;
    private int yStartPoint;
    private int yEndPoint;

    private SwipeAction(AndroidDriver<MobileElement> androidDriver, double offsetPercentage, boolean isVerticallySwipe) {
        this(androidDriver);
        if (isVerticallySwipe) setVerticalSwipePoint(offsetPercentage);
        else setHorizontalSwipePoint(offsetPercentage);
    }

    private SwipeAction(AndroidDriver<MobileElement> androidDriver) {
        this.androidDriver = androidDriver;
        this.mobileScreenSize = androidDriver.manage().window().getSize();
        this.touchAction = new TouchAction(androidDriver);
    }

    public void SwipeToElement(By fromLocator, By toLocator) {
        ElementOption fromElement = new ElementOption().withElement(androidDriver.findElement(fromLocator));
        ElementOption toElement = new ElementOption().withElement(androidDriver.findElement(toLocator));
        performSwipe(fromElement, toElement);
    }

    public void swipeUntilElementFound(By locator, int maxSwipeTimes) {
        List<MobileElement> elements = androidDriver.findElements(locator);
        int swipeTime = 0;
        PointOption startPoint = getStartPoint();
        PointOption endPoint = getEndPoint();
        while ((swipeTime < maxSwipeTimes) && elements.isEmpty()) {
            touchAction.press(startPoint)
                    .waitAction(new WaitOptions().withDuration(Duration.ofMillis(1500)))
                    .moveTo(endPoint)
                    .release().perform();
            swipeTime++;
        }
    }

    public void swipeUntilElementFound(By locator) {
        List<MobileElement> elements = androidDriver.findElements(locator);
        PointOption startPoint = getStartPoint();
        PointOption endPoint = getEndPoint();
        while (elements.isEmpty()) {
            touchAction.press(startPoint)
                    .waitAction(new WaitOptions().withDuration(Duration.ofMillis(1500)))
                    .moveTo(endPoint)
                    .release().perform();
        }
    }

    private void performSwipe(PointOption startPoint, PointOption endPoint) {
        touchAction.press(startPoint)
                .waitAction(new WaitOptions().withDuration(Duration.ofMillis(1500)))
                .moveTo(endPoint)
                .release().perform();
    }

    /*
     The following methods will return the vertical points for swipe action
     */
    private PointOption getStartPoint() {
        return new PointOption().withCoordinates(this.xStartPoint, this.yStartPoint);
    }

    private PointOption getEndPoint() {
        return new PointOption().withCoordinates(this.xEndPoint, this.yEndPoint);
    }

    /*
     The following methods perform the vertical swiping distance for swipe action
     */
    private void setVerticalSwipePoint(double offsetPercentage) {
        if (0 < offsetPercentage && offsetPercentage < 0.5) calcSwipeDownDistance(offsetPercentage);
        else if (offsetPercentage > 0.5 && offsetPercentage < 1) calcSwipeUpDistance(offsetPercentage);
            /* In case the user might set offset percentage equal to 0.5 or invalid percentage => no swipe at all
             * Programme will assume that this is a swipe up action => swipe up from nearly bottom to top */
        else if (offsetPercentage == 0.5) {
            this.yStartPoint = ( int ) (mobileScreenSize.height * 0.9);
            this.yEndPoint = ( int ) (mobileScreenSize.height * 0.1);
        }
    }

    private void calcSwipeUpDistance(double offsetPercentage) {
        this.xStartPoint = mobileScreenSize.width / 2;
        this.xEndPoint = xStartPoint;
        this.yStartPoint = ( int ) (mobileScreenSize.height * offsetPercentage);
        this.yEndPoint = ( int ) (mobileScreenSize.height * (1 - offsetPercentage));
    }

    private void calcSwipeDownDistance(double offsetPercentage) {
        this.xStartPoint = mobileScreenSize.width / 2;
        this.xEndPoint = xStartPoint;
        this.yStartPoint = ( int ) (mobileScreenSize.height * (1 - offsetPercentage));
        this.yEndPoint = ( int ) (mobileScreenSize.height * offsetPercentage);
    }

    /*
     The following methods perform the horizontal swiping distance for swipe action
     */
    private void setHorizontalSwipePoint(double offsetPercentage) {
        if (0 < offsetPercentage && offsetPercentage < 0.5) calcSwipeToLeftDistance(offsetPercentage);
        else if (offsetPercentage > 0.5 && offsetPercentage < 1) calcSwipeToRightDistance(offsetPercentage);
            /* In case the user might set offset percentage equal to 0.5 or invalid percentage => no swipe at all
             * Programme will assume that this is a swipe to left action => swipe to left from nearly left edge to right edge */
        else {
            this.xStartPoint = ( int ) (mobileScreenSize.height * 0.9);
            this.xEndPoint = ( int ) (mobileScreenSize.height * 0.1);
        }
    }

    private void calcSwipeToLeftDistance(double offsetPercentage) {
        this.yStartPoint = mobileScreenSize.width / 2;
        this.yEndPoint = yStartPoint;
        this.xStartPoint = ( int ) (mobileScreenSize.height * offsetPercentage);
        this.xEndPoint = ( int ) (mobileScreenSize.height * (1 - offsetPercentage));
    }

    private void calcSwipeToRightDistance(double offsetPercentage) {
        this.yStartPoint = mobileScreenSize.width / 2;
        this.yEndPoint = yStartPoint;
        this.xStartPoint = ( int ) (mobileScreenSize.height * (1 - offsetPercentage));
        this.xEndPoint = ( int ) (mobileScreenSize.height * offsetPercentage);
    }

}
