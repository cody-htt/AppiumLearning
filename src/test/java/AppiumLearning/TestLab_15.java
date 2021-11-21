package AppiumLearning;

import driver.DriverFactoryRD;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import models.components.forms_page_component.ActiveBtnDialogComponent;
import models.components.forms_page_component.DropdownDialogComponent;
import models.pages.FormPage;
import models.pages.SwipePage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TestLab_15 {
    private final static List<String> resultList = new ArrayList<>();
    private final static List<String> dropDownListItem = new ArrayList<>();
    private final static List<SwipePage.Card> cardListItem = new ArrayList<>();

    public static void main(String[] args) {
        DriverFactoryRD.startAppiumServer();
        AndroidDriver<MobileElement> androidDriver = DriverFactoryRD.getAndroidDriver();

        /* Test Swipe Action on Form Page */
        FormPage formPage = new FormPage(androidDriver);
        DropdownDialogComponent dropdownDialogComp;
        ActiveBtnDialogComponent activeBtnDialogComp;

        formPage.bottomNavBarComponent().clickOnFormsLabel();

        resultList.add("You have typed: " + formPage.inputField("Tung is handsome").inputTextResult().getText());

        resultList.add(formPage.clickOnSwitchBtn().switchText().getText());
        resultList.add(formPage.clickOnSwitchBtn().switchText().getText());

        dropdownDialogComp = formPage.clickOnDropDownIcon();
        dropdownDialogComp.dialogListItems().forEach(item -> {
            dropDownListItem.add(item.getText());
        });

        AtomicInteger indexOfItem = new AtomicInteger(0);
        dropDownListItem.forEach(item -> {
            dropdownDialogComp.getItemFromList(indexOfItem.get()).click();
            if (formPage.dropDownField().getText().equalsIgnoreCase(item)) {
                resultList.add(item + " is displayed");
            }
            formPage.clickOnDropDownIcon();
            indexOfItem.incrementAndGet();
        });
        /* Click on the last item in dropdown list to return FormsPage */
        int lastItemIndex = 3;
        dropdownDialogComp.getItemFromList(lastItemIndex).click();

        activeBtnDialogComp = formPage.clickOnActiveBtn();
        resultList.add("Active dialog:" +
                       "\n\tTitle: " + activeBtnDialogComp.alertTitle().getText() +
                       "\n\tText: " + activeBtnDialogComp.alertMessage().getText());
        activeBtnDialogComp.okBtn().click();

        /* Test Swipe Action on Swipe Page */
        SwipePage swipePage = new SwipePage(androidDriver);
        swipePage.bottomNavBarComponent().clickOnSwipeLabel();

        swipePage.CARD_TEXT.forEach(cardText -> {
            if (swipePage.CARD_TEXT.indexOf(cardText) == 0) {
                cardListItem.add(new SwipePage.Card(swipePage.firstCardTitleElem().getText(),
                        swipePage.firstCardTextElem().getText()));
            } else {
                cardListItem.add(new SwipePage.Card(swipePage.centerCardTitleElem().getText(),
                        swipePage.centerCardTextElem().getText()));
            }
            swipePage.swipeToNextCard();
        });

        swipePage.swipeToWebDriverIOLogo();
        resultList.add("Logo Text: \n\t" + swipePage.webDriverIOLogoText().getText());

        resultList.forEach(System.out :: println);
        System.out.println();
        cardListItem.forEach(System.out :: println);

        androidDriver.closeApp();
        DriverFactoryRD.stopAppiumServer();
    }
}
