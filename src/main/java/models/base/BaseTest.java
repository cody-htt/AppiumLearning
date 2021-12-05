package models.base;

import driver.DriverFactoryOld;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.json.JSONObject;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;
import utils.TestUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class BaseTest {

    protected static HashMap<String, String> expectedStringMap = new HashMap<>();
    protected AppiumDriver<MobileElement> appiumDriver;
    protected JSONObject loginData;
    protected SoftAssert softAssert;
    protected TestUtils testUtils;

    @BeforeTest
    public void beforeTest() {
        /* Start appium server automatically and get android driver*/
        DriverFactoryOld.startAppiumServer();
        appiumDriver = DriverFactoryOld.getAndroidDriver();
        softAssert = new SoftAssert();
        testUtils = new TestUtils();
        /* Initialize loginData basing on loginUser.json file */
        String jsonLoginUserFile = "data/authentication/loginUser.json";
        loginData = testUtils.readJSONFile(jsonLoginUserFile);
        /* Initialize HashMap expectedStringMap basing on ExpectedText.xml file */
        String xmlFileName = "static-string/authentication/ExpectedText.xml";
        InputStream isStringMap = getClass().getClassLoader().getResourceAsStream(xmlFileName);
        expectedStringMap = testUtils.xmlStringParser(isStringMap);
        try {
            if (isStringMap != null) { isStringMap.close(); }
        } catch (IOException ex) { ex.printStackTrace(); }
    }

    @AfterTest
    public void afterTest() {
        /* Close app */
        appiumDriver.closeApp();
        /* Stop Appium Server */
        DriverFactoryOld.stopAppiumServer();
    }
}
