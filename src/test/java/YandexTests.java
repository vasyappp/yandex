import Pages.MainPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class YandexTests extends BaseTest {

    @Parameterized.Parameter()
    public String productType;

    @Parameterized.Parameter(1)
    public Integer minPrice;

    @Parameterized.Parameter(2)
    public String[] manufacturers;

    @Parameterized.Parameter(3)
    public Integer productsAmount;

    @Parameterized.Parameter(4)
    public Integer productIndex;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"Телевизоры", 20000, new String[]{"Samsung", "LG"}, 12, 1},
                {"Наушники", 5000, new String[]{"Sennheiser"}, 12, 2},
                {"Телевизоры", 20000, new String[]{"Samsung", "LG"}, 48, 1}
        });
    }


    @Test
    public void yandex_test() throws InterruptedException {
        new MainPage() // Главная страница Яндекса
                .goToMarket() // Переход на страницу Яндекс.Маркета
                .goToElectronics() // Переход в раздел электроники
                .goToProducts(productType) // Переход в раздел продуктов указанного типа
                .goToFilters() // Переход к заданию параметров поиска продуктов
                .setStartingPrice(minPrice) // Задание минимальной цены продукта
                .setManufacturersOptions(manufacturers) // Задание производителей продукта
                .showFilteredProducts() // Отображение продуктов с заданными параметрами
                .showProductsAmount(productsAmount) // Выбор количества отображаемых продуктов
                .checkProductsAmount(productsAmount) // Проверка соответствия количества отображаемых продуктов заданному в тесте
                .saveResultName(productIndex) // Сохранение названия продукта с указанным порядковым номером
                .searchSavedProductName() // Ввод в поиск сохраненного значения
                .checkSavedProductName(); // Проверка соответствия отображаемого названия продукта сохраненному
    }

}
