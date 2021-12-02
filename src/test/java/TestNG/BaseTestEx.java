package TestNG;

import driver.DriverFactoryEx;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.json.JSONObject;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;
import utils.TestUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class BaseTestEx {

    /* Thread-safe in parallel mobile automation testing
     * 1. Using synchronizedList
     * 2. Using LocalThread to isolate appium thread
     * */

    private final static List<DriverFactoryEx> driverThreadPool = Collections.synchronizedList(new ArrayList<>());
    private static ThreadLocal<DriverFactoryEx> driverThread;

    protected static HashMap<String, String> expectedStringMap = new HashMap<>();
    protected SoftAssert softAssert;
    protected TestUtils testUtils;
    protected JSONObject loginData;

    @BeforeSuite(alwaysRun = true)
    public static void beforeSuite() {
        /* () -> {} is an anonymous function */
        driverThread = ThreadLocal.withInitial(() -> {
            DriverFactoryEx driverThread = new DriverFactoryEx();
            driverThreadPool.add(driverThread);
            return driverThread;
        });
    }

    @AfterSuite(alwaysRun = true)
    public static void afterSuite() {
        driverThreadPool.forEach(DriverFactoryEx :: quitAppiumSession);
    }

    /* Temporary before test method to resolve error */
    @BeforeTest
    public void beforeTest() {
        softAssert = new SoftAssert();
        testUtils = new TestUtils();
        /* Initialize loginData basing on loginUser.json file */
        String jsonLoginUserFile = "data/loginUser.json";
        loginData = testUtils.readJSONFile(jsonLoginUserFile);
        /* Initialize HashMap expectedStringMap basing on staticStrings.xml file */
        String xmlFileName = "static-string/staticStrings.xml";
        InputStream isStringMap = getClass().getClassLoader().getResourceAsStream(xmlFileName);
        expectedStringMap = testUtils.xmlStringParser(isStringMap);
        try {
            if (isStringMap != null) { isStringMap.close(); }
        } catch (IOException ex) { ex.printStackTrace(); }
    }

    public static AppiumDriver<MobileElement> getDriver() {
        return driverThread.get().getAppiumDriver();
    }
}
