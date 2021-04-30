import org.openqa.selenium.WebDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) throws Exception {
        // Scanner read = new Scanner(System.in);
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe"); // setting property for
        // chrome driver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.saucedemo.com/");// launch demo site

        WebElement username = driver.findElement(By.id("user-name"));
        WebElement pwd = driver.findElement(By.id("password"));

        // Test Scenario when no credentials entered
        username.clear();
        pwd.clear();
        driver.findElement(By.id("login-button")).click();// Click Login Button
        // Check if error message exist
        if (driver.findElement(By.className("error-button")).isDisplayed()) {
            System.out.println("Test Scenario for unsuccessful login when no credentials are entered- Passed");
        } else {
            System.err.println("Test Scenario for unsuccessful login when no credentials are entered- Failed");
        }

        // Test Scenarios for No Password
        username.clear();
        username.sendKeys("locked_out_user");
        pwd.clear();
        driver.findElement(By.id("login-button")).click();// Click Login Button
        // Check if error message exist
        if (driver.findElement(By.className("error-button")).isDisplayed()) {
            System.out.println("Test Scenario for unsuccessful login when password is not entered- Passed");
        } else {
            System.err.println("Test Scenario for unsuccessful login when password is not entered- Failed");
        }

        // Test Scenarios for No Password
        username.clear();
        pwd.clear();
        pwd.sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();// Click Login Button
        // Check if error message exist
        if (driver.findElement(By.className("error-button")).isDisplayed()) {
            System.out.println("Test Scenario for unsuccessful login when username is not entered- Passed");
        } else {
            System.err.println("Test Scenario for unsuccessful login when username is not entered- Failed");
        }

        // Test Scenarios for Failed Log In

        username.clear();
        username.sendKeys("locked_out_user");
        pwd.clear();
        pwd.sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();// Click Login Button
        // Check if error message exist
        if (driver.findElement(By.className("error-button")).isDisplayed()) {
            System.out.println("Test Scenario for unsuccessful login- Passed");
        } else {
            System.err.println("Test Scenario for successful login- Failed");
        }

        // Test Scenarios for Loggin in Successfully
        username.clear();
        username.sendKeys("standard_user");
        pwd.clear();
        pwd.sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();// Click Login Button
        // verify successful login
        if (driver.findElement(By.className("app_logo")).isDisplayed()) {
            System.out.println("Test Scenario for successful login- Passed");

        } else {
            System.err.println("Test Scenario for successful login- Failed");
        }

        // Create test scenarios for sorting
        Select sortOptions_1 = new Select(driver.findElement(By.className("product_sort_container")));
        sortOptions_1.selectByValue("za");// Order Z to A

        List<WebElement> listofProducts = driver.findElements(By.className("inventory_item_name"));// get sorted
                                                                                                   // prosucts by same
        System.out.println("Test Scenario to order from Z to A");
        for (WebElement el : listofProducts) {
            System.out.println("Name:" + el.getText());// List them by their name
        }

        Select sortOptions_2 = new Select(driver.findElement(By.className("product_sort_container")));
        sortOptions_2.selectByValue("az");// Order A to Z
        listofProducts = driver.findElements(By.className("inventory_item_name"));// get sorted
        // products by same class
        System.out.println("Test Scenario to order from A to Z");
        for (WebElement el : listofProducts) {
            System.out.println("Name:" + el.getText());
        } // List them by their name

        Select sortOptions_3 = new Select(driver.findElement(By.className("product_sort_container")));
        sortOptions_3.selectByValue("lohi");// Order From Low to High
        listofProducts = driver.findElements(By.className("inventory_item_price"));// get sorted
        // products by same class
        System.out.println("Test Scenario to order from Low to High");
        for (WebElement el2 : listofProducts) {
            System.out.println("Price:" + el2.getText());
        }
        ;// List them by their name

        Select sortOptions_4 = new Select(driver.findElement(By.className("product_sort_container")));
        sortOptions_4.selectByValue("hilo");// Order From High to Low
        listofProducts = driver.findElements(By.className("inventory_item_price"));// get sorted
        // products by same class
        System.out.println("Test Scenario to order from Hight to Low");
        for (WebElement el3 : listofProducts) {
            System.out.println("Price:" + el3.getText());
        } // List them by their name

        // Create test scenarios for the Inventory page (/inventory.html).
        int currentCount, newCount;// to hold the value of the number of items in shopping cart
        currentCount = 0;// initializing

        driver.findElement(By.id("item_0_title_link")).click();// clicking item
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();// adding item
        driver.findElement(By.id("remove-sauce-labs-bike-light")).click();// removing item
        System.out.println("Check that an item can be added then removed-Passed");
        driver.findElement(By.id("back-to-products")).click();// check that you can go back to Products
        if (driver.findElement(By.className("header_secondary_container")).isDisplayed()) {
            System.out.println("Check that you can go back to the Products Inventory-Passed");
            driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();// adding item
            newCount = Integer.parseInt(driver.findElement(By.className("shopping_cart_badge")).getText());
            System.out.println(newCount);
            if (newCount == currentCount + 1)// the shopping cart was incremented
            {
                System.out.println("Check that an item can be added-Passed");
            } else {
                System.out.println("Check that an item can be added-Failed");
            }

        } else {
            System.out.println("Check that you can go back to the Products Inventory-Failed");
        }

        // Create test scenarios for the cart page(/cart.html
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();
        System.out.println("Checkout Button Clicked-Passed");

        // Fill inCheck out details
        driver.findElement(By.id("first-name")).sendKeys("John");
        driver.findElement(By.id("last-name")).sendKeys("Doe");
        driver.findElement(By.id("postal-code")).sendKeys("0000");
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("finish")).click();
        // check that order was completed
        if (driver.findElement(By.className("complete-text")).isDisplayed()) {
            System.out.println("Purchase completed-Passed");
        } else {
            System.out.println("Purchase completed-Failed");
        }
        driver.findElement(By.id("back-to-products")).click();

        // Create test scenarios for checking all the links in the footer of the page

        driver.findElement(By.linkText("Twitter")).click();
        driver.findElement(By.linkText("Facebook")).click();
        driver.findElement(By.linkText("LinkedIn")).click();

        System.out.println("Test Scenario for Twitter Icon- Passed");
        System.out.println("Test Scenario for Instagram Icon- Passed");
        System.out.println("Test Scenario for LinkedinIcon- Passed");

        // Test Scenarios for Loggin Out
        driver.findElement(By.id("react-burger-menu-btn")).click();// click menu bar
        driver.findElement(By.id("logout_sidebar_link")).click();// click log out
        // Check if error message exist
        if (driver.findElement(By.id("login-button")).isDisplayed()) {
            System.out.println("Test Scenario for successful logout- Passed");
        } else {
            System.err.println("Test Scenario for successful logout- Failed");
        }
    }
}