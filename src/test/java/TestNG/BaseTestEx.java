package TestNG;

import driver.DriverFactoryEx;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.openqa.selenium.OutputType;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.TestUtils;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BaseTestEx {

    /* Thread-safe in parallel mobile automation testing
     * 1. Using synchronizedList
     * 2. Using LocalThread to isolate appium thread
     * */

    private final static List<DriverFactoryEx> driverThreadPool = Collections.synchronizedList(new ArrayList<>());
    private static ThreadLocal<DriverFactoryEx> driverThread;
    private String udid;
    private String port;
    private String systemPort;
    private String deviceName;

    protected TestUtils testUtils;
    protected JSONObject validCredentials;

    /*
     @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        driverThread = ThreadLocal.withInitial(() -> {
            DriverFactory_Ex driverThread = new DriverFactory_Ex();
            driverThreadPool.add(driverThread);
            return driverThread;
        });
    }
    */
    @BeforeTest(alwaysRun = true)
    @Parameters({ "udid", "port", "systemPort", "deviceName" })
    public void beforeTest(String udid, String port, String systemPort, String deviceName) {
        System.out.println(deviceName + " | " + udid + " | " + port + " | " + systemPort);
        this.udid = udid;
        this.port = port;
        this.systemPort = systemPort;
        this.deviceName = deviceName;
        /* () -> {...} is an anonymous function */
        driverThread = ThreadLocal.withInitial(() -> {
            DriverFactoryEx driverThread = new DriverFactoryEx();
            driverThreadPool.add(driverThread);
            return driverThread;
        });
    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {
        driverThread.get().quitAppiumSession();
    }

    /*
    @AfterSuite(alwaysRun = true)
    public static void afterSuite() {
        driverThreadPool.forEach(DriverFactoryEx :: quitAppiumSession);
    }
    */

    public AppiumDriver<MobileElement> getDriver() {
        return driverThread.get().getAppiumDriver(udid, port, systemPort, deviceName);
    }

    /*
    public static AppiumDriver<MobileElement> getDriver() {
        return driverThread.get().getAppiumDriver();
    }
    */

    @BeforeMethod
    public void beforeMethod() {
        testUtils = new TestUtils();
        /* Initialize loginData basing on invalidLoginCreds.json file */
        String jsonLoginUserFile = "data/authentication/validLoginCreds.json";
        validCredentials = testUtils.readJSONFile(jsonLoginUserFile);
    }

    @AfterMethod(alwaysRun = true)
    public void onTestFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            // Get the name of test method
            String testMethodName = result.getName();

            // Declare file location
            testUtils = new TestUtils();
            String dateTaken = testUtils.getDateTime();
            String fileLocation = System.getProperty("user.dir") + File.separator + "ScreenShot" + File.separator + testMethodName + "_" + dateTaken + ".png";

            // Save screenshot to the system and attach to Allure report
            File screenShot = driverThread.get().getAppiumDriver().getScreenshotAs(OutputType.FILE);

            try {
                FileUtils.copyFile(screenShot, new File(fileLocation));
                Path content = Paths.get(fileLocation);
                try (InputStream is = Files.newInputStream(content)) {
                    Allure.addAttachment(testMethodName, is);
                }
            } catch (Exception ex) { ex.printStackTrace(); }
        }
    }

}
