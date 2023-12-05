package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.Rectangle;

import java.util.List;

public class ContactListPage extends BasePage{
    public ContactListPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@text='Contact list']")
    MobileElement textTitle;

    @FindBy(xpath = "//android.widget.ImageView[@content-desc='More options']")
    MobileElement menuMoreOptions;
    // class android.widget.ImageView
    // accessibility id	   = More options

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/title']")
    MobileElement btnLogout;

    @FindBy(xpath = "//*[@resource-id='android:id/button1']")
    MobileElement btnYesDeleteContact;

    @FindBy(xpath = "//*[@class='android.widget.ImageButton']")
    MobileElement btnAddNewContact;

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowPhone']")
    List<MobileElement> allPhoneNumbers;

    By allPhones = By.xpath("//*[@resource-id='com.sheygam.contactapp:id/rowPhone']");

    public By getElementByPhoneNumber(String phone) {
        return By.xpath(String.format("//*[@text='%s']", phone));
    }

    public ContactListPage scrollToPhoneNumber(String phoneNumber) {
        try {
            scrollToElementBaseBy(getElementByPhoneNumber(phoneNumber));
        }catch(Exception e) {
            e.getMessage();
        }
        return this;
    }

    public boolean isPhoneNumberOnThePage (String phoneNumber) {
        pause(10000);
        waitElement(btnAddNewContact, 5);
        boolean flag = false;
        try {
            scrollToPhoneNumber(phoneNumber);
            pause(10000);
            findElementBase(getElementByPhoneNumber(phoneNumber));
            flag = true;
            System.out.println(flag + "-------------------------");
        }catch(Exception e) {
            e.getMessage();
        }
        return flag;
    }

    public boolean validateContactListOpened() {
        return isTextEqual(textTitle, "Contact list");
    }

    public AuthenticationPage logout() {
        clickBase(menuMoreOptions);
        clickBase(btnLogout);
        return new AuthenticationPage(driver);
    }

    public AddNewContactPage clickBtnAddNewContact() {
        clickBase(btnAddNewContact);
        return new AddNewContactPage(driver);
    }

    public boolean validateCurrentContactCreated(int i) {
        boolean flagRes = false;
        for(MobileElement el: allPhoneNumbers) {
            if(getTextBase(el).contains(String.valueOf(i))) {
                flagRes = true;
                break;
            }
        }
        return flagRes;
    }

    public ContactListPage moveContactByPhoneNumberToTheRight(String phone) {
        MobileElement phoneNumber = findElementBase(getElementByPhoneNumber(phone));

        Rectangle rect = phoneNumber.getRect();
        int xStart = rect.getX() + rect.getWidth()/8;
        int xEnd = rect.getX() + rect.getWidth()*6/8;
        int y = rect.getY() + rect.getHeight()/2;

        TouchAction<?> touchAction = new TouchAction<>(driver);

        touchAction
                .longPress(PointOption.point(xStart, y))
                .moveTo(PointOption.point(xEnd, y))
                .release()
                .perform();
        return this;
    }

    public ContactListPage clickYesBtnPopUpForContactDelete() {
        clickBase(btnYesDeleteContact);
        return this;
    }

    public ContactListPage deleteAllContacts() {
        String phone = "";
     //   List<MobileElement> list = driver.findElements(allPhones);
//        for(MobileElement el: list) {
//            phone = getTextBase(el);
//            moveContactByPhoneNumberToTheRight(phone);
//            clickYesBtnPopUpForContactDelete();
//        }
        List<MobileElement> list = driver.findElements(allPhones);
        while(!list.isEmpty()){
            try {
                MobileElement el = findElementBase(allPhones);
                phone = getTextBase(el);
                System.out.println(phone);
                moveContactByPhoneNumberToTheRight(phone);
                clickYesBtnPopUpForContactDelete();
            } catch (Exception e) {
                e.getMessage();
            } finally {
                list = driver.findElements(allPhones);
            }
        }
        return this;
    }

    public boolean validateContactListEmpty() {
        List<MobileElement> list = driver.findElements(allPhones);
        return list.isEmpty();
    }
}
