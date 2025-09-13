# DataProvider Framework Guide

This guide explains how to use the DataProvider framework that has been implemented to externalize test data from your test classes.

## Overview

The framework provides multiple ways to manage test data:

1. **JSON Files** - Structured data in JSON format
2. **Properties Files** - Key-value pairs for simple data
3. **CSV Files** - Comma-separated values
4. **Excel Files** - Advanced Excel file support with Apache POI
5. **Hardcoded Data** - Fallback option for simple scenarios

## File Structure

```
src/test/resources/testdata/
├── loginData.json          # JSON format test data
├── loginData.properties    # Properties format test data
├── loginData.csv          # CSV format test data
└── loginData.xlsx         # Excel format test data
```

## Usage Examples

### 1. JSON DataProvider

**File: `src/test/resources/testdata/loginData.json`**
```json
[
  {
    "username": "standard_user",
    "password": "secret_sauce",
    "expectedTitle": "Swag Labs",
    "testType": "valid"
  },
  {
    "username": "locked_out_user",
    "password": "secret_sauce",
    "expectedTitle": "Swag Labs",
    "testType": "locked"
  }
]
```

**Test Method:**
```java
@Test(dataProvider = "loginData", dataProviderClass = DataProvider.class)
public void testLogin(String username, String password, String expectedTitle, String testType) {
    driver.get(ConfigReader.get("web.baseUrl"));
    LoginPage login = new LoginPage(driver);
    login.login(username, password);
    // Your test logic here
}
```

### 2. Properties DataProvider

**File: `src/test/resources/testdata/loginData.properties`**
```properties
username1=standard_user
password1=secret_sauce
expectedTitle1=Swag Labs
testType1=valid

username2=locked_out_user
password2=secret_sauce
expectedTitle2=Swag Labs
testType2=locked
```

**Test Method:**
```java
@Test(dataProvider = "loginDataFromProperties", dataProviderClass = DataProvider.class)
public void testLoginFromProperties(String username, String password, String expectedTitle, String testType) {
    // Your test logic here
}
```

### 3. CSV DataProvider

**File: `src/test/resources/testdata/loginData.csv`**
```csv
username,password,expectedTitle,testType
standard_user,secret_sauce,Swag Labs,valid
locked_out_user,secret_sauce,Swag Labs,locked
```

**Test Method:**
```java
@Test(dataProvider = "loginDataFromCSV", dataProviderClass = DataProvider.class)
public void testLoginFromCSV(String username, String password, String expectedTitle, String testType) {
    // Your test logic here
}
```

### 4. Excel DataProvider

**File: `src/test/resources/testdata/loginData.xlsx`**
```
| username              | password     | expectedTitle | testType |
|-----------------------|--------------|---------------|----------|
| standard_user         | secret_sauce | Swag Labs     | valid    |
| locked_out_user       | secret_sauce | Swag Labs     | locked   |
| problem_user          | secret_sauce | Swag Labs     | valid    |
```

**Test Method:**
```java
@Test(dataProvider = "loginDataFromExcel", dataProviderClass = ExcelUtils.class)
public void testLoginFromExcel(String username, String password, String expectedTitle, String testType) {
    // Your test logic here
}
```

### 5. Simple Hardcoded DataProvider

**Test Method:**
```java
@Test(dataProvider = "simpleLoginData", dataProviderClass = DataProvider.class)
public void testLoginSimple(String username, String password, String expectedTitle, String testType) {
    // Your test logic here
}
```

## Advanced Excel Usage

### Reading Specific Columns
```java
@Test(dataProvider = "customExcelData", dataProviderClass = ExcelUtils.class)
public void testCustomExcelData(String username, String password) {
    // Only reads username and password columns
}

// In ExcelUtils class:
@DataProvider(name = "customExcelData")
public static Object[][] getCustomExcelData() {
    String[] columns = {"username", "password"};
    return readExcelDataWithColumns("src/test/resources/testdata/loginData.xlsx", "LoginData", columns);
}
```

### Reading Row Range
```java
@Test(dataProvider = "excelRowRange", dataProviderClass = ExcelUtils.class)
public void testExcelRowRange(String username, String password) {
    // Only reads rows 2-5 (1-based indexing)
}

// In ExcelUtils class:
@DataProvider(name = "excelRowRange")
public static Object[][] getExcelRowRange() {
    return readExcelDataByRowRange("src/test/resources/testdata/loginData.xlsx", "LoginData", 1, 4);
}
```

## Creating Custom DataProviders

### 1. Custom JSON DataProvider
```java
@DataProvider(name = "customJsonData")
public static Object[][] getCustomJsonData() {
    String[] fields = {"field1", "field2", "field3"};
    return readDataFromJson("src/test/resources/testdata/customData.json", fields);
}
```

### 2. Custom Properties DataProvider
```java
@DataProvider(name = "customPropertiesData")
public static Object[][] getCustomPropertiesData() {
    String[] keys = {"key1", "key2", "key3"};
    return readDataFromProperties("src/test/resources/testdata/customData.properties", keys);
}
```

### 3. Dynamic DataProvider
```java
@DataProvider(name = "dynamicData")
public static Object[][] getDynamicData() {
    List<Object[]> data = new ArrayList<>();
    
    // Add data dynamically
    for (int i = 1; i <= 5; i++) {
        data.add(new Object[]{"user" + i, "pass" + i, "type" + i});
    }
    
    return data.toArray(new Object[0][]);
}
```

## Best Practices

### 1. File Organization
```
src/test/resources/testdata/
├── login/
│   ├── validUsers.json
│   ├── invalidUsers.json
│   └── lockedUsers.json
├── products/
│   ├── productData.xlsx
│   └── productCategories.csv
└── users/
    ├── userRegistration.json
    └── userProfiles.properties
```

### 2. Naming Conventions
- Use descriptive names: `loginData.json`, `productData.xlsx`
- Include data type in filename: `validUsers.json`, `invalidUsers.json`
- Use consistent parameter names across all data sources

### 3. Error Handling
```java
@Test(dataProvider = "loginData", dataProviderClass = DataProvider.class)
public void testLogin(String username, String password, String expectedTitle, String testType) {
    try {
        driver.get(ConfigReader.get("web.baseUrl"));
        LoginPage login = new LoginPage(driver);
        login.login(username, password);
        
        // Test logic based on test type
        if ("valid".equals(testType)) {
            Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
        } else {
            Assert.assertTrue(driver.getCurrentUrl().contains("saucedemo.com"));
        }
    } catch (Exception e) {
        Assert.fail("Test failed for user: " + username + ", Error: " + e.getMessage());
    }
}
```

### 4. Data Validation
```java
@Test(dataProvider = "loginData", dataProviderClass = DataProvider.class)
public void testLogin(String username, String password, String expectedTitle, String testType) {
    // Validate input data
    Assert.assertNotNull(username, "Username should not be null");
    Assert.assertNotNull(password, "Password should not be null");
    Assert.assertNotNull(testType, "Test type should not be null");
    
    // Your test logic here
}
```

## Troubleshooting

### Common Issues

1. **File Not Found Exception**
   - Ensure file path is correct
   - Check if file exists in the specified location
   - Verify file permissions

2. **Data Format Issues**
   - JSON: Validate JSON syntax
   - CSV: Check for proper comma separation
   - Excel: Ensure sheet name matches exactly
   - Properties: Verify key-value format

3. **Parameter Mismatch**
   - Ensure number of parameters in test method matches data columns
   - Check parameter order matches data order

### Debug Tips

1. **Print DataProvider Data**
```java
@Test(dataProvider = "loginData", dataProviderClass = DataProvider.class)
public void testLogin(String username, String password, String expectedTitle, String testType) {
    System.out.println("Testing with: " + username + ", " + password + ", " + testType);
    // Your test logic here
}
```

2. **Validate DataProvider Output**
```java
public static void main(String[] args) {
    Object[][] data = DataProvider.getLoginData();
    for (Object[] row : data) {
        System.out.println(Arrays.toString(row));
    }
}
```

## Migration from Hardcoded Data

### Before (Hardcoded)
```java
@Test
public void testLogin() {
    driver.get(ConfigReader.get("web.baseUrl"));
    LoginPage login = new LoginPage(driver);
    login.login("standard_user", "secret_sauce");
    Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
}
```

### After (DataProvider)
```java
@Test(dataProvider = "loginData", dataProviderClass = DataProvider.class)
public void testLogin(String username, String password, String expectedTitle, String testType) {
    driver.get(ConfigReader.get("web.baseUrl"));
    LoginPage login = new LoginPage(driver);
    login.login(username, password);
    
    if ("valid".equals(testType)) {
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
    } else {
        Assert.assertTrue(driver.getCurrentUrl().contains("saucedemo.com"));
    }
}
```

## Performance Considerations

1. **File Loading**: DataProvider methods are called once per test method, not per test case
2. **Memory Usage**: Large Excel files may consume more memory
3. **Parallel Execution**: Each test thread gets its own data set
4. **Caching**: Consider implementing data caching for large datasets

This framework provides a flexible, maintainable approach to managing test data while keeping your test classes clean and focused on test logic.
