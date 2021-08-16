package org.example.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class MortgageSecondaryHousingPage extends BasePage {

    @FindBy(xpath = "//div[text()='Стоимость недвижимости']/../input")
    private WebElement inputPropertyPrice;

    @FindBy(xpath = "//div[text()='Первоначальный взнос']/../input")
    private WebElement initialFee;

    @FindBy(xpath = "//div[text()='Срок кредита']/../input")
    private WebElement creditTerm;

    @FindBy(xpath = "//span[text()='Страхование жизни и здоровья']/../..//input[@class='switch-input-3-1-2']")
    private WebElement radioBtnInsuranceLifeAndHealth;

    @FindBy(xpath = "//span[text()='Молодая семья']/../../..//input[@class='switch-input-3-1-2']")
    private WebElement radioBtnYoungFamily;

    @FindBy(xpath = "//div[@data-e2e-id='mland-calculator/result-block']//span[text()='Сумма кредита']/../span/span")
    private WebElement creditSum;

    @FindBy(xpath = "//div[@data-e2e-id='mland-calculator/result-block']//span[text()='Ежемесячный платеж']/../span/span")
    private WebElement monthlyPayment;

    @FindBy(xpath = "//div[@data-e2e-id='mland-calculator/result-block']//span[text()='Необходимый доход']/../span/span")
    private WebElement requiredIncome;


    @FindBy(xpath = "//div[@data-e2e-id='mland-calculator/result-block']//span[text()='Процентная ставка']/../span/span")
    private WebElement creditRate;

    public MortgageSecondaryHousingPage waitFrameSwitch() {
        wait.until(ExpectedConditions
                .frameToBeAvailableAndSwitchToIt(By.id("iFrameResizer0")));

        return this;
    }

    public MortgageSecondaryHousingPage fillField(String nameField, String value) {


        WebElement element = null;

        switch (nameField) {
            case "Стоимость недвижимости":
                fillInputField(inputPropertyPrice, value);
                element = inputPropertyPrice;
                break;
            case "Первоначальный взнос":
                fillInputField(initialFee, value);
                element = initialFee;
                break;
            case "Срок кредита":
                fillInputField(creditTerm, value);
                element = creditTerm;
                break;
            default:
                Assertions.fail("Поле '" + nameField + "' отсутствует на странице " +
                        "расчёта ипотеки");
        }

        wait.until(ExpectedConditions.attributeToBe(element, "value", value));
        Assertions.assertEquals(value, element.getAttribute("value"),
                "Поле '" + nameField + "' было заполнено неверно");


        return this;
    }

    public MortgageSecondaryHousingPage selectRadioBtnByName(String nameRadioBtn, String value){
        WebElement element = null;

        switch (nameRadioBtn) {
            case "Страхование жизни и здоровья":
                selectRadioBtn(radioBtnInsuranceLifeAndHealth, value);
                element = radioBtnInsuranceLifeAndHealth;
                break;
            case "Молодая семья":
                selectRadioBtn(radioBtnYoungFamily, value);
                element = radioBtnYoungFamily;
                break;
            default:
                Assertions.fail("Радиокнопка '" + nameRadioBtn + "' отсутствует на странице " +
                        "расчёта ипотеки");
        }


        wait.until(ExpectedConditions.attributeContains(element, "aria-checked", value));
        Assertions.assertEquals(value, element.getAttribute("aria-checked"),
                "У радиокнопки '" + nameRadioBtn + "' неверное значение");

        return this;
    }

    // проверка значения полей
    public MortgageSecondaryHousingPage checkValueField(String nameField, String value) {

        WebElement element = null;
        switch (nameField) {
            case "Сумма кредита":
                element = creditSum;
                break;
            case "Ежемесячный платеж":
                element = monthlyPayment;
                break;
            case "Необходимый доход":
                element = requiredIncome;
                break;
            case "Процентная ставка":
                element = creditRate;
                break;
            default:
                Assertions.fail("Поле с наименованием '" + nameField + "' нет на странице с расчётом ипотеки");
        }

        Assertions.assertEquals(value, element.getText(), "У поля '" + nameField +
                "' другое значение");

        return this;


    }


    private void fillInputField(WebElement element, String value) {

        element.click();
        element.sendKeys(value);
        element.sendKeys(Keys.CONTROL + "A", Keys.DELETE);
        element.sendKeys(value);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void selectRadioBtn(WebElement element, String value) {

        scrollToElementJs(element.findElement(By.xpath("./../../../../../../..")));
        if (!element.getAttribute("aria-checked").contains(value)) {
            element.click();
        }

        wait.until(ExpectedConditions.attributeContains(element, "aria-checked", value));
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
