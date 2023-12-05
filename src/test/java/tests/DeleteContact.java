package tests;

import config.AppiumConfig;
import dto.ContactDTO;
import dto.UserDTO;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.ContactListPage;
import pages.SplashPage;

import java.util.Random;

public class DeleteContact extends AppiumConfig {

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

    @Test
    public void deleteOneContactPositive() {
        int i;
        i = new Random().nextInt(1000) + 1000;
        System.out.println(i);
        Assert.assertFalse(new ContactListPage(driver).clickBtnAddNewContact()
                .addNewContact(ContactDTO.builder()
                        .name("testQA20" + i)
                        .lastName("lN"+i)
                        .email("test" + i + "@gmail.af")
                        .phone("1234567" + i)
                        .address("Haifa")
                        .description("contact: " + i)
                        .build())
                .moveContactByPhoneNumberToTheRight("1234567" + i)
                .clickYesBtnPopUpForContactDelete()
                .isPhoneNumberOnThePage("1234567" + i));
    }


}
