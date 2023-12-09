package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.FindBy;

public class EditContactPage extends BasePage{
    public EditContactPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputEmail']")
    MobileElement inputEmail;

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputPhone']")
    MobileElement inputPhone;

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/updateBtn']")
    MobileElement btnUpdate;

    public EditContactPage changeEmail(String email) {
        typeTextBase(inputEmail, email);
        return this;
    }

    public ContactListPage clickUpdateBtn() {
        clickBase(btnUpdate);
        return new ContactListPage(driver);
    }

    public EditContactPage changePhone(String phone) {
        typeTextBase(inputPhone, phone);
        return this;
    }
}
