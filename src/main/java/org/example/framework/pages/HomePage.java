package org.example.framework.pages;


import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Стартовая страница приложения
 */
public class HomePage extends BasePage {

    @FindBy(xpath = "//button[@class='kitt-cookie-warning__close']")
    private WebElement btnCookieClose;

    @FindBy(xpath = "//a[@role='button' and text()='Ипотека']")
    private WebElement btnMortgage;

    @FindBy(xpath = "//a[text()='Ипотека на вторичное жильё']")
    private WebElement btnMortgageSecondaryHousing;

    @FindBy(xpath = "//ul[contains(@class, 'kitt-top-menu__list')]/li")
    private List<WebElement> listBaseMenu;

    @FindBy(xpath = "//a[@data-cga_click_top_menu]")
    private List<WebElement> listSubMenu;


    public HomePage closeCookie() {
        driverManager.getDriver().manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        try {
            btnCookieClose.click();
        } catch (Exception ignore) {
        } finally {
            driverManager.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        }

        return this;
    }


    public MortgageSecondaryHousingPage goToMortgageSecondaryHousing(String baseMenu, String subMenu) {

        for(WebElement menuItem : listBaseMenu) {
            if(menuItem.getText().equalsIgnoreCase(baseMenu)){
                waitUtilElementToBeClickable(menuItem).click();
                for (WebElement subMenuItem : listSubMenu) {
                    if(subMenuItem.getText().equalsIgnoreCase(subMenu)){
                        waitUtilElementToBeClickable(subMenuItem).click();
                        return pageManager.getMortgageSecondaryHousingPage();
                    }
                }
                Assertions.fail("Подменю '" + subMenu + "' не найдено в меню '" + baseMenu +"'");
            }
        }

        Assertions.fail("Меню '" + baseMenu + "' не найдено на странице");
        return pageManager.getMortgageSecondaryHousingPage();
    }




}
