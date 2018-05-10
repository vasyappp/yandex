package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Главная страница сайта yandex.ru
 */
public class MainPage extends BasePage {
    @FindBy(xpath = ".//a[@data-id = 'market']")
    private WebElement marketButton; // Ссылка (кнопка перехода) на Яндекс.Маркет


    public MainPage() {
        PageFactory.initElements(driver, this);
    }

    /**
     * Метод перехода на страницу Яндекс.Маркета
     *
     * @return Главная страница Яндекс.Мпркета
     */
    public MarketPage goToMarket() {
        waitVisibility(marketButton);
        marketButton.click();

        return new MarketPage();
    }
}
