package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


/**
 * Главная страница Яндекс.Маркета
 */
public class MarketPage extends BasePage {
    @FindBy(xpath = ".//li[@data-department = 'Электроника']/a")
    private WebElement electronicsButton; // Ссылка (кнопка перехода) на раздел "Электроника"

    public MarketPage() {
        PageFactory.initElements(driver, this);
    }

    /**
     * Метод перехода в раздел "Электроника"
     *
     * @return Страница раздела "Электроника"
     */
    public ElectronicsPage goToElectronics() {
        waitVisibility(electronicsButton);
        electronicsButton.click();

        return new ElectronicsPage();
    }
}
