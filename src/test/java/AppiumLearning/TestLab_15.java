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
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TestLab_15 {
    private final static List<String> resultList = new ArrayList<>();
    private final static List<String> dropDownListItem = new ArrayList<>();
    private final static List<SwipePage.Card> cardListItem = new ArrayList<>();

    public static void main(String[] args) {
        DriverFactoryRD.startAppiumServer();
        AndroidDriver<MobileElement> androidDriver = DriverFactoryRD.getAndroidDriver();
        /* Reset the implicitly wait time
        * Need to find a solution to get rid of this type of wait*/
        androidDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        /* Test Swipe Action on Form Page */
        FormPage formPage = new FormPage(androidDriver);
        DropdownDialogComponent dropdownDialogComp;
        ActiveBtnDialogComponent activeBtnDialogComp;

        /* Navigate to Forms Page */
        formPage.bottomNavBarComponent().clickOnFormsLabel();

        resultList.add("You have typed: " + formPage.inputField("Tung is handsome").inputTextResultElem().getText());
        resultList.add(formPage.clickOnSwitchBtn().switchTextElem().getText());
        resultList.add(formPage.clickOnSwitchBtn().switchTextElem().getText());

        dropdownDialogComp = formPage.clickOnDropDownIcon();
        dropdownDialogComp.dialogListItems().forEach(item -> {
            dropDownListItem.add(item.getText());
        });

        /* Retrieve all text in Dropdown list */
        AtomicInteger indexOfItem = new AtomicInteger(0);
        dropDownListItem.forEach(item -> {
            dropdownDialogComp.getItemFromList(indexOfItem.get()).click();
            if (formPage.dropDownInputFieldElem().getText().equalsIgnoreCase(item)) {
                resultList.add(item + " is displayed");
            }
            formPage.clickOnDropDownIcon();
            indexOfItem.incrementAndGet();
        });

        /* Click on the last item in dropdown list to return FormsPage
         * This is a bug, still need to find a solution to resolve it */
        int lastItemIndex = 3;
        dropdownDialogComp.getItemFromList(lastItemIndex).click();

        /* Let Active button displayed by swiping up the screen */
        activeBtnDialogComp = formPage.clickOnActiveBtn();
        resultList.add("Active dialog:" +
                       "\n\tTitle: " + activeBtnDialogComp.alertTitleElem().getText() +
                       "\n\tText: " + activeBtnDialogComp.alertMessageElem().getText());
        activeBtnDialogComp.okBtnElem().click();

        /* Test Swipe Action on Swipe Page */
        SwipePage swipePage = new SwipePage(androidDriver);

        /* Navigate to Swipe Page */
        swipePage.bottomNavBarComponent().clickOnSwipeLabel();

        /* Swipe to left and retrieve texts from all the displayed cards */
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

        /* Swipe up until the Logo displays */
        swipePage.swipeToWebDriverIOLogo();
        resultList.add("Logo Text: " + swipePage.webDriverIOLogoTextElem().getText());

        androidDriver.closeApp();
        DriverFactoryRD.stopAppiumServer();

        resultList.forEach(System.out :: println);
        System.out.println();
        cardListItem.forEach(System.out :: println);
    }
}
