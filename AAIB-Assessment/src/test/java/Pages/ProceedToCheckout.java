package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProceedToCheckout extends OpenBrowser {

    public void setProceed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        WebElement checkoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Proceed To Checkout')]")));
        checkoutButton.click();


        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 700);");


        WebElement placeOrder = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Place Order')]")));
        placeOrder.click();
    }
}
