package org.example.framework.steps;

import io.cucumber.java.ru.И;
import org.example.framework.managers.PageManager;



public class HomePageSteps {

    PageManager pageManager = PageManager.getPageManager();


    @И("Закрыть cookies")
    public void closeCookie() {
        pageManager.getHomePage().closeCookie();
    }

    @И("Нажать на Ипотека в главном меню, выбрать Ипотека на вторичное жильё")
    public void goToMortgageSecondaryHousing() {
        pageManager.getHomePage().goToMortgageSecondaryHousing();
    }

}
