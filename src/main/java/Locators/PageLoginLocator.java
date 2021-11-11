package Locators;

import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

public class PageLoginLocator {
    public final static By XPATH_LABEL_LOGIN = MobileBy.xpath("//android.widget.Button[@content-desc='Login']");
    public final static By XPATH_LOGIN_FORM = MobileBy.xpath("//android.view.ViewGroup" +
            "[@content-desc='button-login-container']/android.view.ViewGroup/android.widget.TextView");
    public final static By XPATH_SIGNUP_FORM = MobileBy.xpath("//android.view.ViewGroup" +
            "[@content-desc='button-sign-up-container']/android.view.ViewGroup/android.widget.TextView");
    public final static By XPATH_INPUT_USERNAME = MobileBy.xpath("//android.widget.EditText[@content-desc='input-email']");
    public final static By XPATH_INPUT_PASSWORD = MobileBy.xpath("//android.widget.EditText[@content-desc='input-password']");
    public final static By XPATH_INPUT_CONFIRM_PASSWORD = MobileBy.xpath("//android.widget.EditText[@content-desc='input-repeat-password']");
    public final static By XPATH_BTN_LOGIN = MobileBy.xpath("//android.view.ViewGroup[@content-desc='button-LOGIN']");
    public final static By XPATH_BTN_SIGN_UP = MobileBy.xpath("//android.view.ViewGroup[@content-desc='button-SIGN UP']");
    public final static By XPATH_CONFIRM_PW_ERROR = MobileBy.xpath("//android.widget.ScrollView[@content-desc='Login-screen']/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[4]/android.widget.TextView[3]");
    public final static By ID_ALERT_BOX_TEMPLATE = MobileBy.id("android:id/title_template");
    public final static By ID_ALERT_TITLE = MobileBy.id("android:id/alertTitle");
    public final static By ID_ALERT_MSG = MobileBy.id("android:id/message");
    public final static By ID_ALERT_BTN_OK = MobileBy.id("android:id/button1");

    public final static String EXPECTED_SUCCESS_LOGIN = "success";
    public final static String EXPECTED_SUCCESS_LOGIN_MSG = "You are logged in!";
    public final static String EXPECTED_SUCCESS_SIGNUP = "Signed Up!";
    public final static String EXPECTED_SUCCESS_SIGNUP_MSG = "You successfully signed up!";
    public final static String EXPECTED_CONFIRM_PASSWORD_ER_MSG = "Please enter the same password";
}
