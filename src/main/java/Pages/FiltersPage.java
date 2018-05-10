package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Страница выбора параметров продукта
 */
public class FiltersPage extends BasePage {
    // Строки для xpath опций выбора производителя
    private final String manufacturerInputXpathStarts = ".//label[contains(text(), '";
    private final String manufacturerInputXpathEnds = "')]/ancestor::span[contains(@class, 'checkbox')]";

    @FindBy(xpath = ".//span[@sign-title = 'от']//input")
    private WebElement priceInputStarts; // Поле ввода минимальной цены продукта

    @FindBy(xpath = ".//span[(@class = 'button__text') and (contains(text(), 'Показать подходящие'))]/ancestor::a")
    private WebElement showProductsButton; // Кнопка перехода к списку товаров с выбранными параметрами

    public FiltersPage() {
        PageFactory.initElements(driver, this);
    }

    /**
     * Метод устанавливает минимальную цену продукта
     *
     * @param price Минимальная цена
     *
     * @return Страница выбора параметров продукта
     */
    public FiltersPage setStartingPrice(Integer price) {
        waitVisibility(priceInputStarts);
        priceInputStarts.sendKeys(price.toString());

        return this;
    }

    /**
     * Метод устанавливает производителей продукта
     *
     * @param manufacturers Список названий производителей
     *
     * @return Страница выбора параметров продукта
     */
    public FiltersPage setManufacturersOptions(String[] manufacturers) {
        for (String manufacturer : manufacturers) {
            WebElement option = driver.findElement(By.xpath
                    (manufacturerInputXpathStarts + manufacturer + manufacturerInputXpathEnds));

            option.click();
        }
        return this;
    }

    /**
     * Метод переходит на сраницу списка продуктов с выбранными параметрами
     *
     * @return Страница списка продуктов
     */
    public ProductsListPage showFilteredProducts() {
        waitVisibility(showProductsButton);
        showProductsButton.click();

        return new ProductsListPage();
    }
}
