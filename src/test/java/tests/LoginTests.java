package tests;

import config.AppiumConfig;
import dto.UserDTO;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.AuthenticationPage;
import pages.ContactListPage;
import pages.SplashPage;

public class LoginTests extends AppiumConfig {
    boolean flagIsUserLogin = false;
    boolean flagIsPopUpErrorDisplays = false;

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
    public void positiveLogin() {
        flagIsUserLogin = true;
        Assert.assertTrue(new SplashPage(driver).goToAuthPage()
                .login(UserDTO.builder()
                        .email("testqa20@gmail.com")
                        .password("123456Aa$")
                        .build())
                .validateContactListOpened());
    }

    @Test
    public void negativeLoginEmptyEmail() {
        flagIsPopUpErrorDisplays = true;
        Assert.assertTrue(new SplashPage(driver).goToAuthPage().fillPassword("123456Aa$")
                .clickLoginBtnNegative().validateErrorTitleAlertCorrect());
    }
}
