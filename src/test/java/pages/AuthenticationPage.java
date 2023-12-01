package pages;

import dto.UserDTO;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.FindBy;

public class AuthenticationPage extends BasePage{
    public AuthenticationPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputEmail']")
    MobileElement inputEmail;

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputPassword']")
    MobileElement inputPassword;

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/regBtn']")
    MobileElement btnRegistration;

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/loginBtn']")
    MobileElement btnLogin;

    @FindBy(xpath = "//*[@resource-id='android:id/alertTitle']")
    MobileElement titleErrorTextAlert;
    @FindBy(xpath = "//*[@resource-id='android:id/button1']")
    MobileElement btnAlertOkError;

    public ContactListPage clickRegBtn() {
        clickBase(btnRegistration);
        return new ContactListPage(driver);
    }

    public ContactListPage clickLoginBtn() {
        clickBase(btnLogin);
        return new ContactListPage(driver);
    }

    public AuthenticationPage clickLoginBtnNegative() {
        clickBase(btnLogin);
        return this;
    }

    public AuthenticationPage clickOkBtnAlert() {
        clickBase(btnAlertOkError);
        return this;
    }

    public AuthenticationPage fillEmail(String email) {
        typeTextBase(inputEmail, email);
        return this;
    }

    public AuthenticationPage fillPassword(String password) {
        typeTextBase(inputPassword, password);
        return this;
    }

    public ContactListPage login(UserDTO user) {
        return fillEmail(user.getEmail()).fillPassword(user.getPassword()).clickLoginBtn();
    }

    public boolean validateErrorTitleAlertCorrect() {
        return isTextEqual(titleErrorTextAlert, "Error");
    }

    public ContactListPage registration(UserDTO user) {
        fillEmail(user.getEmail());
        fillPassword(user.getPassword());
        return clickRegBtn();
    }

    public AuthenticationPage clickRegBtnNegative() {
        clickBase(btnRegistration);
        return this;
    }
}
