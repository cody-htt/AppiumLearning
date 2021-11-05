package LoginPage;

import org.openqa.selenium.By;

public class LoginPageObject {
    protected final static By XPATH_PAGE_LOGIN_BTN = By.xpath("//android.widget.Button[@content-desc='Login']");
    protected final static By XPATH_LOGIN_FORM = By.xpath("//android.view.ViewGroup" +
            "[@content-desc='button-login-container']/android.view.ViewGroup/android.widget.TextView");
    protected final static By XPATH_SIGNUP_FORM = By.xpath("//android.view.ViewGroup" +
            "[@content-desc='button-sign-up-container']/android.view.ViewGroup/android.widget.TextView");
    protected final static By XPATH_INPUT_USERNAME = By.xpath("//android.widget.EditText[@content-desc='input-email']");
    protected final static By XPATH_INPUT_PASSWORD = By.xpath("//android.widget.EditText[@content-desc='input-password']");
    protected final static By XPATH_INPUT_CONFIRM_PASSWORD = By.xpath("//android.widget.EditText[@content-desc='input-repeat-password']");
    protected final static By XPATH_BTN_LOGIN = By.xpath("//android.view.ViewGroup[@content-desc='button-LOGIN']");
    protected final static By XPATH_BTN_SIGN_UP = By.xpath("//android.view.ViewGroup[@content-desc='button-SIGN UP']");
    protected final static By XPATH_CONFIRM_PW_ERROR = By.xpath("//android.widget.ScrollView[@content-desc='Login-screen']/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[4]/android.widget.TextView[3]");
    protected final static By ID_ALERT_BOX_TEMPLATE = By.id("android:id/title_template");
    protected final static By ID_ALERT_TITLE = By.id("android:id/alertTitle");
    protected final static By ID_ALERT_MSG = By.id("android:id/message");
    protected final static By ID_ALERT_BTN_OK = By.id("android:id/button1");

    protected final static String EXPECTED_SUCCESS_LOGIN = "success";
    protected final static String EXPECTED_SUCCESS_LOGIN_MSG = "You are logged in!";
    protected final static String EXPECTED_SUCCESS_SIGNUP = "Signed Up!";
    protected final static String EXPECTED_SUCCESS_SIGNUP_MSG = "You successfully signed up!";
    protected final static String EXPECTED_CONFIRM_PASSWORD_ER_MSG = "Please enter the same password";
}
