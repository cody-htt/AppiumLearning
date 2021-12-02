package AppiumLearning;

import driver.DriverFactoryOld;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import models.components.TopNotificationComponent;
import models.components.login_page_component.DialogComponent;
import models.pages.LoginPage;
import org.json.JSONObject;
import org.openqa.selenium.NoSuchElementException;
import utils.ScreenShotUtils;
import utils.SwipeUtils;
import utils.TestUtils;

import java.time.Duration;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TestLab_17 {

    private final static HashMap<String, TopNotificationComponent.notification> notificationsMap = new HashMap<>();

    public static void main(String[] args) {
        DriverFactoryOld.startAppiumServer();
        AndroidDriver<MobileElement> androidDriver = DriverFactoryOld.getAndroidDriver();
        /* Initialize JSONReader object */
        TestUtils testUtils = new TestUtils();
        /* Initialize loginData basing loginUser.json file */
        JSONObject loginData = testUtils.readJSONFile("data/loginUser.json");
        SwipeUtils swipeUtils = new SwipeUtils(androidDriver);
        ScreenShotUtils screenShotUtils = new ScreenShotUtils(androidDriver);
        /* Set implicitly wait time to 0s */
        androidDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        /* Get all notifications */
        TopNotificationComponent topNotiComp = new TopNotificationComponent(androidDriver);

        /* Swipe down from the top to open notification widget */
        swipeUtils.swipeDown(0.99);

        if (topNotiComp.notiBannerList().isEmpty()) {
            throw new RuntimeException("Notification List is empty");
        }

        /* Retrieve App name, Notification title, Notification message and put into a Hashmap */
        AtomicInteger index = new AtomicInteger(0);
        topNotiComp.notiBannerList().forEach(banner -> {
            System.out.println(index.getAndIncrement());
            String appName = banner.findElement(topNotiComp.notiApp()).getText();
            String title = banner.findElement(topNotiComp.notiTitle()).getText();
            /* Need to use try catch because the text element ID
             * of system notification is different from others */
            String text;
            try {
                text = banner.findElement(topNotiComp.notiText()).getText();
            } catch (NoSuchElementException exception) {
                text = banner.findElement(topNotiComp.notiSystemTextLoc()).getText();
            }
            notificationsMap.put(title, new TopNotificationComponent.notification(appName, title, text));
        });

        /* Swipe up to close the notification widget */
        swipeUtils.swipeUp(0.8);

        /* Handle multiple app - Running app in background */
        LoginPage loginPage = new LoginPage(androidDriver);

        /* Navigate to the Login Page */
        loginPage.bottomNavBarComponent().clickOnLoginLabel();

        /* Enter some information in the Login Form */
        DialogComponent dialogComponent = loginPage.loginFormComponent()
                                                   .inputEmailField(loginData.getJSONObject("validUser").getString("username"))
                                                   .inputPasswordField(loginData.getJSONObject("validUser").getString("password"))
                                                   .clickOnLoginBtn();

        /* Let com.wdiodemoapp run in background */
        if (dialogComponent.isDialogTemplateDisplay()) {
            androidDriver.runAppInBackground(Duration.ofSeconds(-1));
        } else { throw new RuntimeException("Slow down bro"); }

        /* Launch com.android.settings app */
        androidDriver.activateApp("com.android.settings");

        MobileElement wifiLabelElem = androidDriver.findElement(MobileBy.xpath("//*[@text='Wi-Fi']"));
        wifiLabelElem.click(); // Open Wi-Fi settings

        MobileElement wifiToggleBtnElem = androidDriver.findElement(MobileBy.id("com.android.settings:id/switch_widget"));
        boolean isWifiOn = wifiToggleBtnElem.getText().equals("ON"); // Check if Wi-Fi is turn on

        String messageTurnOffWifi = null;
        if (isWifiOn) {
            // Turn wi-fi off
            wifiToggleBtnElem.click();
            // Get the message and turn wi-fi on
            messageTurnOffWifi = androidDriver.findElement(MobileBy.id("android:id/empty")).getText();
            wifiToggleBtnElem.click();
        } else {
            wifiToggleBtnElem.click();
        }

        /* Re-active app com.wdiodemoapp */
        androidDriver.activateApp("com.wdiodemoapp");
        dialogComponent.clickDialogBtn();

        /* Taking screenshot practise */
        loginPage.bottomNavBarComponent().clickOnLoginLabel(); // Make sure the form is load completely
        //Taking whole screenshot
        screenShotUtils.takeWholeScreenShot();
        //Taking login button element screenshot
        screenShotUtils.takeElemScreenShot(loginPage.loginFormComponent().loginBtnElem(), "Login_button");
        //Taking bottom navbar screenshot
        screenShotUtils.takeElemScreenShot(loginPage.bottomNavBarComponent().bottomNavBarElem(), "bot_nav_bar");

        androidDriver.closeApp();
        DriverFactoryOld.stopAppiumServer();

        notificationsMap.keySet().forEach(key -> System.out.println(notificationsMap.get(key)));
        System.out.println(messageTurnOffWifi);
    }
}
