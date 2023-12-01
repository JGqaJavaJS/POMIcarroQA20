package tests;

import config.AppiumConfig;
import dto.UserDTO;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.ContactListPage;
import pages.SplashPage;

public class AddNewContactTests extends AppiumConfig {

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
}
