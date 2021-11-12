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

public class GestureActionSwipe {

    private final AndroidDriver<MobileElement> androidDriver;
    private final Dimension mobileScreenSize;
    private final TouchAction touchAction;

    public GestureActionSwipe(AndroidDriver<MobileElement> androidDriver) {
        this.androidDriver = androidDriver;
        this.mobileScreenSize = androidDriver.manage().window().getSize();
        this.touchAction = new TouchAction(androidDriver);
    }

    /* Only swipe 1 time from a visible element to another visible element */
    public void SwipeToElement(By fromLocator, By toLocator) {
        Helper helper = new Helper();

        ElementOption fromElement = new ElementOption().withElement(androidDriver.findElement(fromLocator));
        ElementOption toElement = new ElementOption().withElement(androidDriver.findElement(toLocator));

        helper.performSwipe(fromElement, toElement);
    }

    /* swipe until the element is visible to the driver */
    public void verticallySwipeUtilElementVisible(By locator) {
        Helper helper = new Helper(0.5, 0.5, 0.8, 0.1);

        PointOption startPoint = new PointOption().withCoordinates(helper.xStartPoint, helper.yStartPoint);
        PointOption endPoint = new PointOption().withCoordinates(helper.xEndPoint, helper.yEndPoint);
        helper.performSwipe(startPoint, endPoint);
    }

    /* swipe until the element is visible to the driver But with a max swipe times and modified wait time for action */
    public void verticallySwipeUtilElementVisible(By locator, int maxSwipeTimes, int waitTime) {
        Helper helper = new Helper(0.5, 0.5, 0.8, 0.1, maxSwipeTimes);

        PointOption startPoint = new PointOption().withCoordinates(helper.xStartPoint, helper.yStartPoint);
        PointOption endPoint = new PointOption().withCoordinates(helper.xEndPoint, helper.yEndPoint);
        helper.performSwipe(startPoint, endPoint, waitTime);
    }

    private class Helper {
        private int xStartPoint;
        private int xEndPoint;
        private int yStartPoint;
        private int yEndPoint;
        private int maxSwipeTimes;

        public Helper() {
        }

        private Helper(double xStartPercent, double xEndPercent, double yStartPercent, double yEndPercent, int maxSwipeTimes) {
            calcOffset(xStartPercent,
                    xEndPercent,
                    yStartPercent,
                    yEndPercent);
            this.maxSwipeTimes = maxSwipeTimes;
        }

        private Helper(double xStartPercent, double xEndPercent, double yStartPercent, double yEndPercent) {
            calcOffset(xStartPercent,
                    xEndPercent,
                    yStartPercent,
                    yEndPercent);
        }

        private void calcOffset(double xStartPercent, double xEndPercent, double yStartPercent, double yEndPercent) {
            this.xStartPoint = ( int ) (mobileScreenSize.width * xStartPercent);
            this.xEndPoint = ( int ) (mobileScreenSize.width * xEndPercent);
            this.yStartPoint = ( int ) (mobileScreenSize.width * yStartPercent);
            this.yEndPoint = ( int ) (mobileScreenSize.width * yEndPercent);
        }

        private void performSwipe(PointOption startPoint, PointOption endPoint) {
            touchAction.press(startPoint)
                    .waitAction(new WaitOptions().withDuration(Duration.ofMillis(1500)))
                    .moveTo(endPoint)
                    .release().perform();
        }

        private void performSwipe(PointOption startPoint, PointOption endPoint, int waitTime) {
            touchAction.press(startPoint)
                    .waitAction(new WaitOptions().withDuration(Duration.ofMillis(waitTime)))
                    .moveTo(endPoint)
                    .release().perform();
        }

    }

}
