package TestNG.TestLoginPage;

import TestNG.BaseTestEx;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Description;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test_data.authentication.LoginCreds;
import test_data.authentication.DataObjectBuilder;
import test_flows.authentication.LoginFlow;

public class LoginTest extends BaseTestEx {

    @Description("Verify Login with valid credentials")
    @Test(dataProvider = "invalidLoginCredsData", description = "Login Test", priority = 1)
    public void loginWithInvalidCreds(LoginCreds loginCreds) {
        // Init Appium driver
        AppiumDriver<MobileElement> androidDriver = getDriver();
        LoginFlow loginFlow = new LoginFlow(androidDriver);
        loginFlow.navigateToLoginPage()
                 .login(loginCreds)
                 .verifyLoginWithIncorrectCreds(loginCreds);
    }

    @Description("Verify Login with invalid credentials")
    @Test(description = "Login Test", priority = 2)
    public void loginWithValidCreds() {
        LoginCreds validLoginCreds = new LoginCreds(validCredentials.getString("email"), validCredentials.getString("password"));

        // Init Appium driver
        AppiumDriver<MobileElement> androidDriver = getDriver();
        LoginFlow loginFlow = new LoginFlow(androidDriver);
        loginFlow.navigateToLoginPage()
                 .login(validLoginCreds)
                 .verifyLoginWithCorrectCreds();
    }

    @DataProvider
    public LoginCreds[] invalidLoginCredsData() {
        String jsonLoc = "src/test/resources/data/authentication/invalidLoginCreds.json";
        return DataObjectBuilder.buildDataObject(jsonLoc, LoginCreds[].class);
    }

}
