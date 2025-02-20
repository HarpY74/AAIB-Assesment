package Pages;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Runner {
    OpenBrowser open = new OpenBrowser();
    LogIn signIn = new LogIn();
    AddToCart addProduct = new AddToCart();
    ProceedToCheckout proceed = new ProceedToCheckout();
    PaymentAndConfirmOrder confirm = new PaymentAndConfirmOrder();

    @BeforeClass
    public void setBrowser() {
        open.setupBrowser();
    }

    @Test(priority = 1, dataProvider = "loginData", dataProviderClass = LogIn.class)
    public void login(String email, String password) {
        signIn.setLogin(email, password);
    }

    @Test(priority = 2)
    public void addProduct() {
        addProduct.addProduct();
    }

    @Test(priority = 3)
    public void proceedToCheckout() {
        proceed.setProceed();
    }

    @Test(priority = 4, dataProvider = "paymentData", dataProviderClass = PaymentAndConfirmOrder.class)
    public void confirmOrder(String nameOnCard, String cardNumber, String cvc, String expiryMonth, String expiryYear) {
        confirm.setPaymentAndConfirm(nameOnCard, cardNumber, cvc, expiryMonth, expiryYear);

    }


}
