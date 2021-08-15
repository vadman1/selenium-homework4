package org.example.framework.pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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


    public MortgageSecondaryHousingPage goToMortgageSecondaryHousing() {
        btnMortgage.click();
        btnMortgageSecondaryHousing.click();

        return pageManager.getMortgageSecondaryHousingPage();
    }




}
