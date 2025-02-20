package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddToCart extends OpenBrowser {

    public void addProduct() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 500);");


        WebElement productImage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[contains(@src,'get_product_picture/2')]")));
        Actions actions = new Actions(driver);
        actions.moveToElement(productImage).perform();


        WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Add to cart')]")));
        addToCart.click();


        WebElement viewCart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/view_cart']")));
        viewCart.click();
    }
}
