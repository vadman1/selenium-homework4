package org.example.framework.steps;

import io.cucumber.java.ru.И;
import org.example.framework.managers.PageManager;



public class HomePageSteps {

    PageManager pageManager = PageManager.getPageManager();


    @И("^Закрыть cookies$")
    public void closeCookie() {
        pageManager.getHomePage().closeCookie();
    }

    @И("^Нажать на \"(.+)\" в главном меню, выбрать \"(.+)\"$")
    public void goToMortgageSecondaryHousing(String baseMenu, String subMenu) {
        pageManager.getHomePage().goToMortgageSecondaryHousing(baseMenu, subMenu);
    }

}
