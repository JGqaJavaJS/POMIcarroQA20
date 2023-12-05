package tests;

import config.AppiumConfig;
import dto.ContactDTO;
import dto.UserDTO;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.AddNewContactPage;
import pages.ContactListPage;
import pages.SplashPage;

import java.util.Random;

public class AddNewContactTests extends AppiumConfig {

    boolean flagToCloseAlert = false;

    @BeforeClass
    public void beforeClass() {
        new SplashPage(driver).goToAuthPage()
                .login(UserDTO.builder()
                        .email("testqa20@gmail.com")
                        .password("123456Aa$")
                        .build());
    }

    @AfterClass
    public void afterClass() {
        // need to open contACT PAGE be4 logout
        new ContactListPage(driver).logout();
    }

    @AfterMethod
    public void afterMethod() {
        if(flagToCloseAlert) {
            flagToCloseAlert = false;
            new AddNewContactPage(driver).clickOkCloseAlert().backBtnOnEmulator();
            //driver.pressKeyCode(AndroidKeyCode.BACK);
            //driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
          //  driver.navigate().back();
         //   new ContactListPage(driver).pause(10000);
        }
    }

    @Test
            //(invocationCount = 15)
    public void positiveAddNewContact() {
        int i;
        i = new Random().nextInt(1000) + 1000;
        System.out.println(i);
        // contact page click +
     //   new ContactListPage(driver).pause(10000);
        Assert.assertTrue(new ContactListPage(driver).clickBtnAddNewContact()
                .addNewContact(ContactDTO.builder()
                        .name("testQA20" + i)
                        .lastName("lN"+i)
                        .email("test" + i + "@gmail.af")
                        .phone("1234567" + i)
                        .address("Haifa")
                        .description("contact: " + i)
                        .build())
             //   .validateCurrentContactCreated(i));
                        .isPhoneNumberOnThePage("1234567" + i));
        // fill fields on the add new contact page and click create
        // some validation?
    }

    @Test
    public void negativeTestEmptyPhone() {
        int i;
        i = new Random().nextInt(1000) + 1000;
        System.out.println(i);
        // contact page click +
        flagToCloseAlert = true;
        Assert.assertTrue(new ContactListPage(driver).clickBtnAddNewContact()
                .addNewContactNegative(ContactDTO.builder()
                        .name("testQA20" + i)
                        .lastName("lN"+i)
                        .email("test" + i + "@gmail.af")
                        .phone("")
                        .address("Haifa")
                        .description("contact: " + i)
                        .build())
                .validateErrorMessage());
    }
}
