package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.FindBy;

public class ContactInfoPage extends BasePage{
    public ContactInfoPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/emailTxt']")
    MobileElement textEmail;

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/phoneTxt']")
    MobileElement textPhone;

    public boolean validateEmailUpdated(String email) {
        return isTextEqual(textEmail, email);
    }

    public ContactListPage returnBack() {
        backBtnOnEmulator();
        return new ContactListPage(driver);
    }

    public boolean validatePhoneUpdated(String phone) {
        return isTextEqual(textPhone, phone);
    }
}
