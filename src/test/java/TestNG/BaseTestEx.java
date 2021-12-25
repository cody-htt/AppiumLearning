package TestNG;

import driver.DriverFactoryEx;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
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

    @BeforeTest(alwaysRun = true)
    @Parameters({ "udid", "port", "systemPort" })
    public void beforeTest(String udid, String port, String systemPort) {
        System.out.println(udid + "|" + port + "|" + systemPort);
        this.udid = udid;
        this.port = port;
        this.systemPort = systemPort;
        /* () -> {} is an anonymous function */
        driverThread = ThreadLocal.withInitial(() -> {
            DriverFactoryEx driverThread = new DriverFactoryEx();
            driverThreadPool.add(driverThread);
            return driverThread;
        });
    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {
        driverThread.get().quitAppiumSession();
//        driverThreadPool.forEach(DriverFactoryEx :: quitAppiumSession);
    }

    public AppiumDriver<MobileElement> getDriver() {
        return driverThread.get().getAppiumDriver(udid, port, systemPort);
    }

    @AfterMethod(alwaysRun = true)
    public void onTestFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            // Get the name of test method
            String testMethodName = result.getName();
            // 2. Declare the file location
//            Calendar calendar = new GregorianCalendar();
//            int y = calendar.get(Calendar.YEAR);
//            int m = calendar.get(Calendar.MONTH);
//            int d = calendar.get(Calendar.DATE);
//            int hr = calendar.get(Calendar.HOUR_OF_DAY);
//            int min = calendar.get(Calendar.MINUTE);
//            int sec = calendar.get(Calendar.SECOND);
//            String dateTaken = y + "-" + (m + 1) + "-" + d + "-" + hr + "-" + min + "-" + sec;
//            String fileLocation = System.getProperty("user.dir") + "/screenshot/" + testMethodName + "_" + dateTaken + ".png";

            // Declare file location
            TestUtils testUtils = new TestUtils();
            String dateTaken = testUtils.getDateTime();
            String fileLocation = System.getProperty("user.dir") + File.separator + "ScreenShot" + File.separator + testMethodName + "_" + dateTaken + ".png";

            // Save screenshot to the system and attach to Allure report
            File screenShot = getDriver().getScreenshotAs(OutputType.FILE);

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
