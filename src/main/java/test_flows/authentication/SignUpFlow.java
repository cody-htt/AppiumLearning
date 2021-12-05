package test_flows.authentication;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.pages.LoginPage;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;

public class SignUpFlow {
    private AppiumDriver<MobileElement> appiumDriver;
    private LoginPage loginPage;
    private HashMap<String, String> expectedStringMap;
    private SoftAssert softAssert;

    public SignUpFlow(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
        softAssert = new SoftAssert();
    }
}
