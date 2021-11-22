package AppiumLearning;

import driver.DriverFactoryRD;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import models.components.TopNotificationComponent;
import models.components.login_page_component.DialogComponent;
import models.pages.LoginPage;
import org.openqa.selenium.NoSuchElementException;
import utils.ScreenShotUtils;
import utils.SwipeUtils;

import java.time.Duration;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TestLab_17 {

    private final static HashMap<String, TopNotificationComponent.notification> notificationsMap = new HashMap<>();

    public static void main(String[] args) {
        DriverFactoryRD.startAppiumServer();
        AndroidDriver<MobileElement> androidDriver = DriverFactoryRD.getAndroidDriver();
        SwipeUtils swipeUtils = new SwipeUtils(androidDriver);
        ScreenShotUtils csUtils = new ScreenShotUtils(androidDriver);
        /* Set implicitly wait time to 0s */
        androidDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        /* Get all notifications */
        TopNotificationComponent topNotiComp = new TopNotificationComponent(androidDriver);

        swipeUtils.swipeDown(0.99);

        if (topNotiComp.notiBannerList().isEmpty()) {
            throw new RuntimeException("Notification List is empty");
        }

        AtomicInteger index = new AtomicInteger(0);
        topNotiComp.notiBannerList().forEach(banner -> {
            System.out.println(index.getAndIncrement());
            String appName = banner.findElement(topNotiComp.notiApp()).getText();
            String title = banner.findElement(topNotiComp.notiTitle()).getText();
            String text;
            try {
                text = banner.findElement(topNotiComp.notiText()).getText();
            } catch (NoSuchElementException exception) {
                text = banner.findElement(topNotiComp.notiSystemTextLoc()).getText();
            }
            notificationsMap.put(title, new TopNotificationComponent.notification(appName, title, text));
        });

        swipeUtils.swipeUp(0.8);

        /* Handle multiple app - Running app in background */
        // Let SUT run in background
        LoginPage loginPage = new LoginPage(androidDriver);

        loginPage.bottomNavBarComponent().clickOnLoginLabel();

        DialogComponent dialogComponent = loginPage.loginFormComponent()
                                                   .inputEmailField("tung@email.com")
                                                   .inputPasswordField("12345678")
                                                   .clickOnLoginBtn();

        // Let com.wdiodemoapp run in background
        if (dialogComponent.isDialogTemplateDisplay()) {
            androidDriver.runAppInBackground(Duration.ofSeconds(-1));
        } else { throw new RuntimeException("Slow down bro"); }

        // Launch com.android.settings app
        androidDriver.activateApp("com.android.settings");

        MobileElement wifiLabelElem = androidDriver.findElement(MobileBy.xpath("//*[@text='Wi-Fi']"));
        wifiLabelElem.click();

        MobileElement wifiToggleBtnElem = androidDriver.findElement(MobileBy.id("com.android.settings:id/switch_widget"));
        boolean isWifiOn = wifiToggleBtnElem.getText().equals("ON");

        String messageTurnOffWifi = null;
        if (isWifiOn) {
            wifiToggleBtnElem.click();
            // Turn wi-fi on
            messageTurnOffWifi = androidDriver.findElement(MobileBy.id("android:id/empty")).getText();
            wifiToggleBtnElem.click();
        } else {
            wifiToggleBtnElem.click();
        }

        // Relaunch com.wdiodemoapp
        androidDriver.activateApp("com.wdiodemoapp");
        dialogComponent.clickDialogBtn();

        /* Taking screenshot practise*/

        //Taking whole screenshot
        csUtils.takeWholeScreenShot();

        //Taking login button element screenshot
        csUtils.takeElemScreenShot(loginPage.loginFormComponent().loginBtnElem(), "Login_button");

        //Taking bottom navbar screenshot
        csUtils.takeElemScreenShot(loginPage.bottomNavBarComponent().bottomNavBar(), "bot_nav_bar");


        notificationsMap.keySet().forEach(key -> {
            System.out.println(notificationsMap.get(key));
        });
        System.out.println(messageTurnOffWifi);

        androidDriver.closeApp();
        DriverFactoryRD.stopAppiumServer();
    }
}
