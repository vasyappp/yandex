package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

/**
 * Страница раздела Электроника
 */
public class ElectronicsPage extends BasePage {
    // Строки, задающие xpath кнопки перехода в раздел товаров Яндекс.Маркета
    private static final String typeOfProductButtonXpathStarts = ".//div[@class = 'catalog-menu__list']/a[contains(text(), '";
    private static final String typeOfProductButtonXpathEnds = "')]";

    public ElectronicsPage() {
        PageFactory.initElements(driver, this);
    }

    /**
     * Метод переходит на страницу списка продуктов заданного типа
     *
     * @param productType Тип продукта
     *
     * @return Страница списка продуктов
     */
    public ProductsListPage goToProducts(String productType) {
        WebElement typeOfProductButton = driver.findElement(By.xpath
                (typeOfProductButtonXpathStarts + productType + typeOfProductButtonXpathEnds));

        waitVisibility(typeOfProductButton);

        typeOfProductButton.click();

        return new ProductsListPage();
    }
}
