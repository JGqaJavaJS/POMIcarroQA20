package tests;

import com.github.javafaker.Faker;
import config.AppiumConfig;
import dto.UserDTO;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.AuthenticationPage;
import pages.ContactListPage;
import pages.SplashPage;

public class SignUpTests extends AppiumConfig {

    boolean flagIsUserLogin = false;
    boolean flagIsPopUpErrorDisplays = false;

    // Initialize Faker
    Faker faker = new Faker();
    // Generate a random email address
    String randomEmail = faker.internet().emailAddress();

    @AfterMethod
    public void afterMethod() {
        if(flagIsUserLogin) {
            flagIsUserLogin = false;
            new ContactListPage(driver).logout();
        } else if(flagIsPopUpErrorDisplays) {
            flagIsPopUpErrorDisplays = false;
            new AuthenticationPage(driver).clickOkBtnAlert();
        }
    }

    @Test
    public void positiveReg() {
        System.out.println("Random Email: " + randomEmail);
        flagIsUserLogin = true;
        Assert.assertTrue(new SplashPage(driver).goToAuthPage()
                .registration(UserDTO.builder()
                        .email(randomEmail)
                        .password("123456Aa$")
                        .build())
                .validateContactListOpened());
    }

    @Test
    public void negativeRegEmptyEmail() {
        flagIsPopUpErrorDisplays = true;
        Assert.assertTrue(new SplashPage(driver).goToAuthPage().fillPassword("123456Aa$")
                .clickRegBtnNegative().validateErrorTitleAlertCorrect());
    }
}
