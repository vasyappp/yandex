package Pages;

import Utils.Stash;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    WebDriver driver;

    public BasePage() {
        this.driver = Stash.getInstance().getDriver();
    }

    /**
     * Метод ожидает отображения элемента заданное время
     *
     * @param element Элемент, который ожидается на странице
     * @param time Время, которое тест будет ожидать элемент
     */
    public void waitVisibility(WebElement element, int time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Метод ожидает отображения элемента установленное время
     *
     * @param element Элемент, который ожидается на странице
     */
    public void waitVisibility(WebElement element) {
        waitVisibility(element, 10);
    }
}
