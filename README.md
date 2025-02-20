# AAIB-Assesment
# 🛠️ Selenium Test Automation Framework

## 📌 Project Overview
This project is a comprehensive test automation framework designed to automate end-to-end testing for the e-commerce website [automationexercise.com](https://automationexercise.com/). It leverages Selenium WebDriver, TestNG, and Apache POI to perform data-driven testing, ensuring efficient and robust test execution across various scenarios.

---

## 🚀 Tech Stack & Tools Used

| Tool/Technology          | Purpose                                     |
|--------------------------|----------------------------------------------|
| **Java**                 | Programming language for test scripts       |
| **Selenium WebDriver**   | Web browser automation                      |
| **TestNG**               | Test execution and reporting framework      |
| **Apache POI**           | Reading and writing Excel data files        |
| **Maven**                | Build management and dependency handling    |
| **Git/GitHub**           | Version control and collaboration           |
| **ChromeDriver**         | Browser driver for Chrome browser           |

---

## 🔍 Test Automation Methodology

This framework follows a modular and data-driven approach to ensure scalability and reusability of test scripts.

### Key Features:
- **Page Object Model (POM)**: Each page of the application is represented as a class.
- **Data-Driven Testing**: Test data is maintained in Excel files for flexibility.
- **Explicit Waits**: Handles dynamic web elements and ensures stable test execution.
- **Assertions**: Validates the expected outcomes of test cases.
- **Logging & Reporting**: Provides detailed logs and reports for easy debugging.

---

## 📂 Project Structure

```
📦 SeleniumTestFramework
│── 📁 src
│   ├── 📁 main
│   │   ├── 📁 Pages
│   │   │   ├── OpenBrowser.java
│   │   │   ├── LogIn.java
│   │   │   ├── AddToCart.java
│   │   │   ├── ProceedToCheckout.java
│   │   │   ├── PaymentAndConfirmOrder.java
│   ├── 📁 test
│   │   ├── Runner.java
│   ├── 📁 resources
│   │   ├── test_data.xlsx
│   │   ├── testng.xml
│   │   ├── log4j.properties
│── 📄 pom.xml
│── 📄 README.md
```

---

## 🛠️ Setup & Installation

### Prerequisites
- **Java JDK 11+**
- **Maven**
- **ChromeDriver** (matching your Chrome version)
- **TestNG Plugin**
- **Excel Files** for test data

### Installation Steps
1. **Clone the Repository**
   ```sh
   git clone https://github.com/yourusername/SeleniumTestFramework.git
   cd SeleniumTestFramework
   ```

2. **Install Dependencies**
   ```sh
   mvn clean install
   ```

3. **Configure Test Data**
    - Update `test_data.xlsx` with your test credentials and payment data.

4. **Run Tests**
   ```sh
   mvn test
   ```

---

## 🏃‍♂️ Running Test Cases

### Using Maven
- Run all tests:
  ```sh
  mvn test
  ```
- Run specific test using TestNG:
  ```sh
  mvn test -DsuiteXmlFile=testng.xml
  ```

### Test Execution Flow
1. **Open Browser**: Initializes WebDriver.
2. **Log In**: Executes login test with data from Excel.
3. **Add to Cart**: Adds a product to the cart.
4. **Proceed to Checkout**: Navigates to the checkout page.
5. **Payment and Confirmation**: Processes payment and confirms the order.

---

## 📝 Test Data Management
- Test data is stored in `test_data.xlsx`.
- Includes login credentials and payment information.

### Excel Data Format
| Email           | Password | Card Number | Expiry Month | Expiry Year | CVC |
|-----------------|----------|-------------|--------------|-------------|-----|
| example@site.com| password | 123456789   | 12           | 2025        | 123 |

---

## 📊 Test Reporting
- **TestNG Reports**: Located in `target/surefire-reports/index.html`



## 🛠️ Troubleshooting

| Issue                                | Solution                                              |
|--------------------------------------|-------------------------------------------------------|
| `WebDriverException: chrome not reachable` | Ensure correct ChromeDriver version is installed. |
| `ElementNotVisibleException`         | Use explicit waits before interacting with elements.  |
| `FileNotFoundException: test_data.xlsx` | Verify that the Excel file exists in the correct path. |

---

## 🔥 Future Enhancements
- Cross-browser testing with Firefox and Edge.
- Parallel test execution.
- Database validation for payment processes.

---

## 📩 Contributing
1. **Fork** this repository.
2. **Create a branch** for your feature or bug fix.
3. **Commit your changes** and push to your branch.
4. **Submit a Pull Request** for review.

---



