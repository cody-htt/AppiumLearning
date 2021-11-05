package api_learning;

import Caps.MobileCapabilityTypeEx;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class LaunchDemoApp {

    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = null;
        boolean hasDriver = true;

        try {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.PLATFORM_NAME , "Android");
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.AUTOMATION_NAME , "UiAutomator2");
            //            desiredCap.setCapability("avd", "Pixel_4"); // Automatically launch android virtual device
            //            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME , "Pixel_4");
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.UDID , "emulator-5554");
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.APP_PACKAGE , "com.wdiodemoapp");
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.APP_ACTIVITY , "com.wdiodemoapp.MainActivity");
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.AVD_LAUNCH_TIMEOUT , 120_000);
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.NEW_COMMAND_TIMEOUT , 120);

            URL appiumServer = new URL("http://0.0.0.0:4723/wd/hub");
            appiumDriver = new AndroidDriver<>(appiumServer , desiredCapabilities);
        } catch (Exception e) {
            hasDriver = false;
            e.printStackTrace();
        } finally {
            if (hasDriver) {
                System.out.println("Session ID: " + appiumDriver.getSessionId());
                System.out.println("Connected to device successfully");
            } else {
                System.out.println("Error while connecting to android device");
            }
        }

        if (hasDriver) {
            appiumDriver.manage().timeouts().implicitlyWait(3L , TimeUnit.SECONDS);
            MobileElement btnLoginPage = appiumDriver.findElementByAccessibilityId("Login");
            btnLoginPage.click();

            appiumDriver.manage().timeouts().implicitlyWait(3L , TimeUnit.SECONDS);
            MobileElement userNameTxtBox = appiumDriver.findElementByAccessibilityId("input-email");
            userNameTxtBox.sendKeys(Constant.EMAIL);

            MobileElement passwordTxtBox = appiumDriver.findElementByAccessibilityId("input-password");
            passwordTxtBox.sendKeys(Constant.PASSWORD);

            MobileElement loginBtn = appiumDriver.findElementByAccessibilityId("button-LOGIN");
            loginBtn.click();

            appiumDriver.manage().timeouts().implicitlyWait(3L , TimeUnit.SECONDS);
            MobileElement successAlertTitle = appiumDriver.findElementById("android:id/alertTitle");
            String alertTitle = successAlertTitle.getAttribute("text");

            MobileElement successMsg = appiumDriver.findElementById("android:id/message");
            String msgText = successMsg.getAttribute("text");

            MobileElement confirmBtn = appiumDriver.findElementById("android:id/button1");

            if (alertTitle.equalsIgnoreCase("success") &&
                    msgText.equalsIgnoreCase("You are logged in!")) {

                confirmBtn.click();
                System.out.println("Login Successfully!!!\n\tSimple Test Case is PASSED");
            } else {
                System.out.println("Fail to Login!!!\n\tTest case is FAILED");
            }
        } else System.out.println("Appium driver fail to initiate");

    }
}
