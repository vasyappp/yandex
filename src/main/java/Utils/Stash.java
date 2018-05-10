package Utils;

import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class Stash {
    private Map<String, Object> stash;
    private static Stash INSTANCE = null;

    public static final String driver = "driver";
    public static final String productName = "productName";

    private Stash() {
        this.stash = new HashMap<>();
    }

    public static Stash getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Stash();
        }
        return INSTANCE;
    }

    public Map<String, Object> getStash() {
        return stash;
    }

    public void put(String key, Object value) {
        stash.put(key, value);
    }

    public Object get(String key) {
        return stash.get(key);
    }

    public WebDriver getDriver() {
        return (WebDriver) stash.get(driver);
    }

    public void setDriver(WebDriver newDriver) {
        stash.put(driver, newDriver);
    }
}
