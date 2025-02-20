package Pages;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

public class PaymentAndConfirmOrder extends OpenBrowser {

    // DataProvider to read payment details from Excel
    @org.testng.annotations.DataProvider(name = "paymentData")
    public Object[][] readPaymentDataFromExcel() throws IOException {
        String filePath = "C:\\Users\\Mohamed\\Desktop\\Test_Payment_data.xlsx"; // ✅ Corrected path

        // Verify file existence
        File file = new File(filePath);
        if (!file.exists()) {
            throw new RuntimeException("File not found at: " + filePath);
        }

        FileInputStream fis = new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(fis); // ✅ Using XSSFWorkbook for .xlsx files

        // Ensure the correct sheet name
        String sheetName = "Sheet1"; // Change this if your sheet has a different name
        Sheet sheet = workbook.getSheet(sheetName);

        if (sheet == null) {
            throw new RuntimeException("Sheet '" + sheetName + "' not found in Excel file.");
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
                            data[i - 1][j] = cell.getStringCellValue();
                            break;
                        case NUMERIC:
                            if (j == 1) { // Card number column
                                data[i - 1][j] = String.format("%.0f", cell.getNumericCellValue()); // Avoids scientific notation
                            } else {
                                data[i - 1][j] = String.valueOf((int) cell.getNumericCellValue()); // Convert to int for other numeric values
                            }
                            break;
                        default:
                            data[i - 1][j] = "";
                    }
                }
            }
        }

        workbook.close();
        fis.close();
        return data;
    }

    // Method to process payment
    public void setPaymentAndConfirm(String nameOnCard, String cardNumber, String cvc, String expiryMonth, String expiryYear) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        System.out.println("Processing payment for: " + nameOnCard);

        // Fill in payment details
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

        // Click on Pay and Confirm button
        WebElement payButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));
        payButton.click();

        System.out.println("Payment submitted successfully.");
    }
}
