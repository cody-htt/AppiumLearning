package AppiumLearning;

import driver.DriverFactoryRD;
import environments.Context;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import models.components.BottomNavBarComponent;
import models.components.webview_page_component.LeftNavBarComponent;
import models.pages.WebviewPage;
import org.apache.commons.lang3.StringUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class TestLab_16 {

    private final static List<String> resultList = new ArrayList<>();
    private final static List<WebviewPage.MenuItem> itemList = new ArrayList<>();

    public static void main(String[] args) {
        DriverFactoryRD.startAppiumServer();
        AndroidDriver<MobileElement> androidDriver = DriverFactoryRD.getAndroidDriver();
        BottomNavBarComponent bottomNavBarComp = new BottomNavBarComponent(androidDriver);
        LeftNavBarComponent leftNavBarComp;

        bottomNavBarComp.clickOnWebviewLabel();

        WebviewPage webviewPage = new WebviewPage(androidDriver);

        if (webviewPage.robotLogoElem().isDisplayed()) {
            resultList.add("Introduce text: \n\t" + webviewPage.logoTextFieldElem().getText());
        }

        leftNavBarComp = webviewPage.clickOnLeftNavBarToggleBtn();
        leftNavBarComp.viewModeToggleBtn().click();

        leftNavBarComp.menuItemList().forEach(item -> {
            if (StringUtils.isEmpty(item.getText())) {
                itemList.add(new WebviewPage.MenuItem("GitHub Logo", item.getAttribute("href")));
            } else {
                itemList.add(new WebviewPage.MenuItem(item.getText(), item.getAttribute("href")));
            }
        });

        /* Switch androidDriver to NATIVE_APP context */
        androidDriver.context(Context.NATIVE.getContext());
        bottomNavBarComp.clickOnHomeLabel();

        /* Let the app run in background */
        androidDriver.runAppInBackground(Duration.ofSeconds(3L));

        resultList.forEach(System.out :: println);
        itemList.forEach(System.out :: println);

        androidDriver.closeApp();
        DriverFactoryRD.stopAppiumServer();
    }
     /*
     Set<String> contextHandles = androidDriver.getContextHandles();
     contextHandles.forEach(System.out :: println);
     */
}