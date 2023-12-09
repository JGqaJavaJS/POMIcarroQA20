package tests;

import config.AppiumConfig;
import dto.UserDTO;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.ContactInfoPage;
import pages.ContactListPage;
import pages.SplashPage;

import java.util.Random;

public class UpdateContactTests extends AppiumConfig {

    @BeforeClass
    public void beforeClass() {
        new SplashPage(driver).goToAuthPage()
                .login(UserDTO.builder()
                        .email("testqa20@gmail.com")
                        .password("123456Aa$")
                        .build()).pause(5000);
    }

    @AfterClass
    public void afterClass() {
        // need to open contACT PAGE be4 logout
        new ContactListPage(driver).logout();
    }

    @AfterMethod
    public void afterMethod() {
        new ContactInfoPage(driver).returnBack();
    }

    @Test
    public void changeEmailPositiveTest() {
        // it is good to add create contact - but not now
        int i = new Random().nextInt(1000) + 1000;
        String email = "test" + i + "@gmail.af";
        Assert.assertTrue(new ContactListPage(driver)
                .switchLeftContactToEditFirstContact() // return editcontact page
                .changeEmail(email) // return editcontact page
                .clickUpdateBtn() // return contact page
                .clickOnFirstContact() // return page contact info
                .validateEmailUpdated(email));
    }

    @Test
    public void changePhonePositiveTest() {
        // it is good to add create contact - but not now
        int i = new Random().nextInt(1000) + 1000;
        String phone = "1234567" + i;
        Assert.assertTrue(new ContactListPage(driver)
                .switchLeftContactToEditFirstContact() // return editcontact page
                .changePhone(phone)
                .clickUpdateBtn()
                .clickOnFirstContact()
                .validatePhoneUpdated(phone));
    }
}
