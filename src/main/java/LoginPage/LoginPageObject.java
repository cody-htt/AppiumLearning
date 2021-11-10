package LoginPage;

import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

public class LoginPageObject {
    protected final static By XPATH_PAGE_LOGIN_BTN = MobileBy.xpath("//android.widget.Button[@content-desc='Login']");
    protected final static By XPATH_LOGIN_FORM = MobileBy.xpath("//android.view.ViewGroup" +
            "[@content-desc='button-login-container']/android.view.ViewGroup/android.widget.TextView");
    protected final static By XPATH_SIGNUP_FORM = MobileBy.xpath("//android.view.ViewGroup" +
            "[@content-desc='button-sign-up-container']/android.view.ViewGroup/android.widget.TextView");
    protected final static By XPATH_INPUT_USERNAME = MobileBy.xpath("//android.widget.EditText[@content-desc='input-email']");
    protected final static By XPATH_INPUT_PASSWORD = MobileBy.xpath("//android.widget.EditText[@content-desc='input-password']");
    protected final static By XPATH_INPUT_CONFIRM_PASSWORD = MobileBy.xpath("//android.widget.EditText[@content-desc='input-repeat-password']");
    protected final static By XPATH_BTN_LOGIN = MobileBy.xpath("//android.view.ViewGroup[@content-desc='button-LOGIN']");
    protected final static By XPATH_BTN_SIGN_UP = MobileBy.xpath("//android.view.ViewGroup[@content-desc='button-SIGN UP']");
    protected final static By XPATH_CONFIRM_PW_ERROR = MobileBy.xpath("//android.widget.ScrollView[@content-desc='Login-screen']/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[4]/android.widget.TextView[3]");
    protected final static By ID_ALERT_BOX_TEMPLATE = MobileBy.id("android:id/title_template");
    protected final static By ID_ALERT_TITLE = MobileBy.id("android:id/alertTitle");
    protected final static By ID_ALERT_MSG = MobileBy.id("android:id/message");
    protected final static By ID_ALERT_BTN_OK = MobileBy.id("android:id/button1");

    protected final static String EXPECTED_SUCCESS_LOGIN = "success";
    protected final static String EXPECTED_SUCCESS_LOGIN_MSG = "You are logged in!";
    protected final static String EXPECTED_SUCCESS_SIGNUP = "Signed Up!";
    protected final static String EXPECTED_SUCCESS_SIGNUP_MSG = "You successfully signed up!";
    protected final static String EXPECTED_CONFIRM_PASSWORD_ER_MSG = "Please enter the same password";
}
