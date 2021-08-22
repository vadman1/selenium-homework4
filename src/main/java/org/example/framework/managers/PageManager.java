package org.example.framework.managers;


import org.example.framework.pages.HomePage;
import org.example.framework.pages.MortgageSecondaryHousingPage;

/**
 * Класс для управления страничками
 */
public class PageManager {

    /**
     * Менеджер страничек
     */
    private static PageManager pageManager;

    /**
     * Стартовая страница
     */
    private HomePage homePage;

    /**
     * Страница с ипотекой на вторичное жильё
     */
    private MortgageSecondaryHousingPage mortgageSecondaryHousingPage;

    private PageManager() {
    }

    /**
     * Ленивая инициализация PageManager
     *
     * @return PageManager
     */
    public static PageManager getPageManager() {
        if (pageManager == null) {
            pageManager = new PageManager();
        }
        return pageManager;
    }


    /**
     * Ленивая инициализация {@link HomePage}
     *
     * @return HomePage
     */
    public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage();
        }
        return homePage;
    }

    /**
     * Ленивая инициализация {@link MortgageSecondaryHousingPage}
     *
     * @return mortgageSecondaryHousingPage
     */
    public MortgageSecondaryHousingPage getMortgageSecondaryHousingPage() {
        if (mortgageSecondaryHousingPage == null) {
            mortgageSecondaryHousingPage = new MortgageSecondaryHousingPage();
        }
        return mortgageSecondaryHousingPage;
    }
}
