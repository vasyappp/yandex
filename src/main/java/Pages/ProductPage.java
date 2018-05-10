package Pages;

import Utils.Stash;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Страница информации о продукте
 */
public class ProductPage extends BasePage {
    @FindBy(xpath = ".//div[@class = 'n-title__text']/h1")
    private WebElement productName; // Заголовок страницы, содержащий название продукта

    public ProductPage() {
        PageFactory.initElements(driver, this);
    }

    /**
     * Метод сравнения ожидаемого названия продукта с полученным на странице
     *
     * @param expectedName Ожидаемое название продукта
     */
    public void checkProductName(String expectedName) {
        waitVisibility(productName);

        String actualName = productName.getText();

        Assert.assertEquals("Имя продукта не соответствует ожидаемому",
                expectedName, actualName);
    }

    /**
     * Метод получения сохраненного имени продукта для сравнения
     */
    public void checkSavedProductName() {
        String name = (String)
                Stash.getInstance().get(Stash.productName);

        checkProductName(name);
    }
}
