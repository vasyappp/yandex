package Pages;

import Utils.Stash;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Страница списка продуктов
 */
public class ProductsListPage extends BasePage {
    private List<WebElement> products; // Список карточек-контейнеров, содержащих имеющиеся на странице продукты

    @FindBy(xpath = ".//a[contains(text(), 'Перейти ко всем фильтрам')]")
    private WebElement filters; // Кнопка перехода на страницу выбора параметров поиска продукта

    @FindBy(xpath = ".//button[@role = 'listbox']")
    private WebElement showProductsAmountList; // Кнопка выбора отображения количества продуктов

    @FindBy(xpath = ".//div[contains(@class, 'n-snippet-list')]")
    private WebElement productList; // Контейнер списка всех продуктов на странице

    @FindBy(xpath = ".//input[@id = 'header-search']")
    private WebElement searchInput; // Строка ввода для поиска продукта

    @FindBy(xpath = ".//span[@class = 'search2__button']/button[@type = 'submit']")
    private WebElement searchButton; // Кнопка для поиска продукта


    public ProductsListPage() {
        PageFactory.initElements(driver, this);
    }

    /**
     * Метод получает список всех продуктов на странице и сохраняет его
     */
    private void setProducts() {
        WebElement product = productList.findElement(By.xpath("./div"));

        if (product.getAttribute("class").contains("card")) {
            products = productList.findElements(By.xpath("./div[contains(@class, 'card')]"));
        } else {
            products = productList.findElements(By.xpath("./div[contains(@class, 'cell')]"));
        }
    }

    /**
     * Метод перехода на страницу выбора параметров поиска продукта
     *
     * @return Страница выбора параметров
     */
    public FiltersPage goToFilters() {
        waitVisibility(filters);
        filters.click();

        return new FiltersPage();
    }

    /**
     * Метод выбирает количество отображаемых продуктов
     *
     * @param amount Количество продуктов
     *
     * @return Страница списка продуктов
     */
    private ProductsListPage getProductsAmountOption(Integer amount) throws InterruptedException {
        if ((amount != 12) && (amount != 48)) {
            Assert.fail("Нет опции: показывать по " + amount + " товаров");
        }

        Thread.sleep(5000);

        WebElement element = driver.findElement(By.xpath
                (".//span[@class ='select__text' and contains(text(),'Показывать по " + amount + "')]"));

        waitVisibility(element);

        element.click();

        return this;
    }

    /**
     * Метод нажимает на кнопку для просмотра имеющихся опций для количества отображаемых продуктов
     *
     * @param amount Количество отображаемых продуктов
     *
     * @return Страница списка продуктов
     */
    public ProductsListPage showProductsAmount(int amount) throws InterruptedException {
        waitVisibility(showProductsAmountList);
        showProductsAmountList.click();

        return getProductsAmountOption(amount);
    }

    /**
     * Метод проверки количества отображаемых продуктов с заданным в тесте
     *
     * @param amount Количество продуктов
     *
     * @return Страница списка продуктов
     *
     * @throws InterruptedException эксепшн
     */
    public ProductsListPage checkProductsAmount(int amount) throws InterruptedException {
        Thread.sleep(5000);

        waitVisibility(productList);

        setProducts();

        Assert.assertEquals
                ("Количество продуктов на странице не соответствует заданному",
                        amount, products.size());

        return this;
    }

    /**
     * Метод сохраняет название продукта для дальнейшей проверки
     *
     * @param index Порядковый номер продукта на странице
     *
     * @return Страница списка продуктов
     */
    public ProductsListPage saveResultName(Integer index) {
        String title = products.get(index - 1).findElement(By.xpath
                (".//div[contains(@class, 'title')]/a")).getText();

        Stash.getInstance().put(Stash.productName, title);

        return this;
    }

    /**
     * Метод вводит в строку поиска заданное название продукта
     *
     * @param name Название продукта
     *
     * @return Страница информации о продукте
     */
    public ProductPage searchByName(String name) {
        searchInput.sendKeys(name);
        searchButton.click();

        return new ProductPage();
    }

    /**
     * Метод получения сохраненного имени для поиска
     *
     * @return Страница информации о продукте
     */
    public ProductPage searchSavedProductName() {
        String name = (String)
                Stash.getInstance().get(Stash.productName);

        return searchByName(name);
    }
}
