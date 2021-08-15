package org.example.framework;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class OldSberbankTest {

    private static WebDriver driver;
    private static WebDriverWait wait;
    JavascriptExecutor js = (JavascriptExecutor) driver;

    @BeforeAll
    static void beforeAll() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications"); // отключение уведомлений от браузера

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize(); // раскрытие окна браузера на максимальную ширину
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS); // ожидание загрузки страницы
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); // ожидание появления элемента на странице

        wait = new WebDriverWait(driver, 20, 1000);
    }

    @AfterAll
    static void afterAll() {
        driver.quit();
    }

    @BeforeEach
    void beforeEach(){
        driver.get("https://www.sberbank.ru/person"); // при следующем тесте переходим на начальную страницу
    }

    @AfterEach
    void afterEach() {
        // по окончании теста браузер не закрываем
    }


    @Test
    public void test() throws InterruptedException {

        try {
            driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
            driver.findElement(By.xpath("//button[@class='kitt-cookie-warning__close']")).click();
        } catch (Exception ignore) {

        } finally {
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        }

        WebElement btnMortgage = driver.findElement(By.xpath("//a[@role='button' and text()='Ипотека']"));
        btnMortgage.click();

        WebElement btnMortgageSecondaryHousing = driver.findElement(By.xpath("//a[text()='Ипотека на вторичное жильё']"));
        btnMortgageSecondaryHousing.click();


        wait.until(ExpectedConditions
                .frameToBeAvailableAndSwitchToIt(By.id("iFrameResizer0")));

        WebElement inputPropertyPrice = driver.findElement(By
                .xpath("//div[text()='Стоимость недвижимости']/../input"));
        inputPropertyPrice.click();
        inputPropertyPrice.sendKeys("1");
        inputPropertyPrice.sendKeys(Keys.CONTROL + "A", Keys.DELETE);
        inputPropertyPrice.sendKeys("5 180 000");

        Thread.sleep(500);

        WebElement initialFee = driver.findElement(By.xpath("//div[text()='Первоначальный взнос']/../input"));
        initialFee.click();
        initialFee.sendKeys("1");
        initialFee.sendKeys(Keys.CONTROL + "A", Keys.DELETE);
        initialFee.sendKeys("3 058 000");


        WebElement creditTerm = driver.findElement(By.xpath("//div[text()='Срок кредита']/../input"));
        creditTerm.click();
        creditTerm.sendKeys("1");
        creditTerm.sendKeys(Keys.CONTROL + "A", Keys.DELETE);
        creditTerm.sendKeys("30");

//        Thread.sleep(500);

        WebElement radioBtnInsuranceLifeAndHealth = driver.findElement(By
                .xpath("//span[text()='Страхование жизни и здоровья']/../..//input[@class='switch-input-3-1-2']"));

        if(radioBtnInsuranceLifeAndHealth.getAttribute("aria-checked").equals("true")) {
            radioBtnInsuranceLifeAndHealth.click();
        }



        WebElement radioBtnYoungFamily = driver.findElement(By
                .xpath("//span[text()='Молодая семья']/../../..//input[@class='switch-input-3-1-2']"));

        Assertions.assertEquals("true", radioBtnYoungFamily.getAttribute("aria-checked"),
                "Радиокнопка \"Молодая семья\" не нажата");

        // проверка значения полей

        WebElement creditSum = driver.findElement(By.xpath(
                "//div[@data-e2e-id='mland-calculator/result-block']//span[text()='Сумма кредита']/../span/span"));
        wait.until(ExpectedConditions.textToBePresentInElement(creditSum, "2 122 000 ₽"));
        Assertions.assertEquals("2 122 000 ₽", creditSum.getText(), "Сумма кредита неверна");

        WebElement monthlyPayment = driver.findElement(By
                .xpath("//div[@data-e2e-id='mland-calculator/result-block']//span[text()='Ежемесячный платеж']/../span/span"));
        wait.until(ExpectedConditions.textToBePresentInElement(monthlyPayment, "16 618 ₽"));
        Assertions.assertEquals("16 618 ₽", monthlyPayment.getText(), "Ежемесячный платеж неверен");

        WebElement requiredIncome = driver.findElement(By
                .xpath("//div[@data-e2e-id='mland-calculator/result-block']//span[text()='Необходимый доход']/../span/span"));
        wait.until(ExpectedConditions.textToBePresentInElement(requiredIncome, "21 393 ₽"));
        Assertions.assertEquals("21 393 ₽", requiredIncome.getText(), "Необходимый доход неверен");

    }

}