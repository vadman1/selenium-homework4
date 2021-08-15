package org.example.framework.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.И;
import org.example.framework.managers.PageManager;


public class MortgageSecondaryHousingPageSteps {

    PageManager pageManager = PageManager.getPageManager();



    @И("^Заполняем поля:$")
    public void fillField(DataTable mapFieldsAndValue) {
        pageManager.getMortgageSecondaryHousingPage().waitFrameSwitch();

        mapFieldsAndValue.asMap(String.class, String.class).forEach((key,value) ->
                pageManager.getMortgageSecondaryHousingPage().fillField((String)key, (String) value));
    }

    @И("^Выбрать значение у радиокнопок$")
    public void selectRadioBtnByName(DataTable mapRadioBtnAndValues){
        mapRadioBtnAndValues.asMap(String.class, String.class).forEach((key, value) ->
                pageManager.getMortgageSecondaryHousingPage().selectRadioBtnByName((String) key, (String) value));
    }

    @И("^Проверить значение полей$")
    public void checkValueField(DataTable mapFieldsAndValue) {
        mapFieldsAndValue.asMap(String.class, String.class).forEach((key, value) ->
                pageManager.getMortgageSecondaryHousingPage().checkValueField((String) key, (String) value));
    }
}
