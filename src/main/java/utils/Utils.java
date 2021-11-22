package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.io.FileHandler;

import java.io.File;

public class Utils {

    AppiumDriver<MobileElement> appiumDriver;

    public Utils(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public void takeScreenShot() {
        String imagePath = System.getProperty("user.dir") + "/ScreenShot";
        File image = appiumDriver.getScreenshotAs(OutputType.FILE);

        try {
            FileHandler.copy(image, new File(imagePath + "/SampleImage.png"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
