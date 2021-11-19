package AppiumLearning;

import environments.Context;
import driver.DriverFactoryRD;
import models.pages.WebviewElem;
import models.pages.WebviewPage;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class TestLab_16 {

    public static void main(String[] args) {
        DriverFactoryRD.startAppiumServer();
        AndroidDriver<MobileElement> androidDriver = DriverFactoryRD.getAndroidDriver();

        WebDriverWait wait = new WebDriverWait(androidDriver, 10);

        MobileElement Webview = androidDriver.findElement(WebviewElem.WEBVIEW_LABEL_BTN);
        Webview.click();

        /*
         Set<String> contextHandles = androidDriver.getContextHandles();
         contextHandles.forEach(System.out :: println);
        */

        MobileElement nativeImage = androidDriver.findElement(WebviewElem.WEBVIEW_ROBOT_IMAGE);
        boolean isWebviewImage = nativeImage.getText().equalsIgnoreCase(WebviewElem.ROBOT_IMAGE_HEADING_TEXT);

        /* Check if loading image is disappear and switch androidDriver to WEBVIEW context */
        if (isWebviewImage) androidDriver.context(Context.WEBVIEW.getContext());

        MobileElement topLeftNavBar = androidDriver.findElement(WebviewElem.TOP_LEFT_NAVBAR_TOGGLE);
        topLeftNavBar.click();

        List<MobileElement> menuList = androidDriver.findElements(WebviewElem.ITEM_LINKS);
        List<WebviewPage.MenuItem> menuItemList = new ArrayList<>();

        if (!menuList.isEmpty()) {
            menuList.forEach(item -> {
                if (StringUtils.isEmpty(item.getText())) {
                    menuItemList.add(new WebviewPage.MenuItem("GitHub Logo", item.getAttribute("href")));
                } else {
                    menuItemList.add(new WebviewPage.MenuItem(item.getText(), item.getAttribute("href")));
                }
            });
        }

        /* Switch androidDriver to NATIVE_APP context */
        androidDriver.context(Context.NATIVE.getContext());
        MobileElement homeLabel = androidDriver.findElement(MobileBy.AccessibilityId("Home"));
        homeLabel.click();

        /* Let the app run in background */
        androidDriver.runAppInBackground(Duration.ofSeconds(3L));

        menuItemList.forEach(System.out :: println);

        DriverFactoryRD.stopAppiumServer();
    }
}