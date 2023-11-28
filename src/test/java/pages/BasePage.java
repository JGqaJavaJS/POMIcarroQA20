package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    AppiumDriver<MobileElement> driver;

    public BasePage(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public String getTextBase(MobileElement el) {
        return el.getText().toUpperCase().trim();
    }

    public boolean isTextEqual(MobileElement el, String text) {
        if(getTextBase(el).equals(text.toUpperCase())) {
            return true;
        } else {
            System.out.println("actual res: " + getTextBase(el) +
                     " expected res: " + text.toUpperCase());
            return false;
        }
    }

}
