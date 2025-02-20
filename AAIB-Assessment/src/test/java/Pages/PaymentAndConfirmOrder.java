package Pages;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

public class PaymentAndConfirmOrder extends OpenBrowser {

    @org.testng.annotations.DataProvider(name = "paymentData")
    public Object[][] readPaymentDataFromExcel() throws IOException {
        String filePath = "C:\\Users\\Mohamed\\Desktop\\Test_Payment_data.xlsx";
        File file = new File(filePath);

        if (!file.exists()) {
            throw new RuntimeException("Excel file not found at: " + filePath);
        }

        FileInputStream fis = new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet("Sheet1");

        if (sheet == null) {
            throw new RuntimeException("Sheet 'Sheet1' not found in the Excel file.");
        }

        int rowCount = sheet.getPhysicalNumberOfRows();
        int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
        Object[][] data = new Object[rowCount - 1][colCount];

        for (int i = 1; i < rowCount; i++) {
            Row row = sheet.getRow(i);
            for (int j = 0; j < colCount; j++) {
                Cell cell = row.getCell(j);
                if (cell != null) {
                    switch (cell.getCellType()) {
                        case STRING:
                            data[i - 1][j] = cell.getStringCellValue().trim();
                            break;
                        case NUMERIC:
                            data[i - 1][j] = String.valueOf((long) cell.getNumericCellValue());
                            break;
                        default:
                            data[i - 1][j] = "";
                    }
                } else {
                    data[i - 1][j] = "";
                }
            }
        }

        workbook.close();
        fis.close();
        return data;
    }

    public void setPaymentAndConfirm(String nameOnCard, String cardNumber, String cvc, String expiryMonth, String expiryYear) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {


            WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@data-qa='name-on-card']")));
            nameField.sendKeys(nameOnCard);

            WebElement cardField = driver.findElement(By.xpath("//input[@data-qa='card-number']"));
            cardField.sendKeys(cardNumber);

            WebElement cvcField = driver.findElement(By.xpath("//input[@name='cvc']"));
            cvcField.sendKeys(cvc);

            WebElement expiryMonthField = driver.findElement(By.xpath("//input[@name='expiry_month']"));
            expiryMonthField.sendKeys(expiryMonth);

            WebElement expiryYearField = driver.findElement(By.xpath("//input[@name='expiry_year']"));
            expiryYearField.sendKeys(expiryYear);

            WebElement payButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));
            payButton.click();

        }
        catch (Exception e) {

        }
    }
}
