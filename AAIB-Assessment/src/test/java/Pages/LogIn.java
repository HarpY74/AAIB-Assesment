package Pages;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class LogIn extends OpenBrowser {

    public void setLogin(String email, String password) {
        driver.get("https://automationexercise.com/login"); // Ensure login page loads

        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@data-qa='login-email']")));
        emailField.sendKeys(email);

        WebElement passwordField = driver.findElement(By.xpath("//input[@data-qa='login-password']"));
        passwordField.sendKeys(password);

        WebElement loginButton = driver.findElement(By.xpath("//button[text()='Login']"));
        loginButton.click();

        // Assertion to verify successful login
        try {
            WebElement welcomeMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Logged in as')]")));
            Assert.assertTrue(welcomeMessage.isDisplayed(), "Login failed!");
        } catch (Exception e) {
            Assert.fail("Login unsuccessful. Possible incorrect credentials or website issue.");
        }
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() throws IOException {
        String filePath = "C:\\Users\\Mohamed\\Desktop\\Test_Driven.xlsx";

        FileInputStream fis = new FileInputStream(new File(filePath));
        Workbook wb = new XSSFWorkbook(fis);
        Sheet sheet = wb.getSheetAt(0);

        int rowCount = sheet.getPhysicalNumberOfRows();
        int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

        Object[][] data = new Object[rowCount - 1][colCount];

        for (int i = 1; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                Cell cell = sheet.getRow(i).getCell(j);
                if (cell != null) {
                    switch (cell.getCellType()) {
                        case STRING:
                            data[i - 1][j] = cell.getStringCellValue().trim(); // Trim any extra spaces
                            break;
                        case NUMERIC:
                            data[i - 1][j] = String.valueOf((int) cell.getNumericCellValue()); // Convert numeric to String
                            break;
                        default:
                            data[i - 1][j] = "";
                            break;
                    }
                }
            }
        }

        wb.close();
        return data;
    }}
