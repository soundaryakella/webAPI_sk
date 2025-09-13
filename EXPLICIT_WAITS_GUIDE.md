# Explicit Waits Framework Guide

This guide explains how to use the explicit waits framework that has been added to your UI testing framework.

## Overview

The framework now includes comprehensive explicit wait functionality that can be accessed by all UI pages through:

1. **BasePage Class** - Base class that all page objects should extend
2. **WaitUtils Class** - Utility class for advanced wait operations
3. **Configuration** - Configurable wait timeouts in `config.properties`

## Architecture

```
BasePage (Abstract)
    ↓
LoginPage (extends BasePage)
    ↓
Test Classes (use page objects with built-in waits)
```

## Configuration

### Wait Timeouts in config.properties

```properties
# Wait Configuration
web.wait.timeout=10          # Default wait timeout (10 seconds)
web.wait.short.timeout=5     # Short wait timeout (5 seconds)
web.wait.long.timeout=20     # Long wait timeout (20 seconds)
web.wait.page.load.timeout=30 # Page load timeout (30 seconds)
```

## Usage Examples

### 1. Basic Page Object with Explicit Waits

```java
public class LoginPage extends BasePage {
    private By username = By.id("user-name");
    private By password = By.id("password");
    private By loginBtn = By.id("login-button");

    public LoginPage(WebDriver driver) {
        super(driver); // Initialize BasePage with driver
    }

    public void login(String user, String pass) {
        // These methods use explicit waits automatically
        safeSendKeys(username, user);
        safeSendKeys(password, pass);
        safeClick(loginBtn);
    }
}
```

### 2. Available Wait Methods in BasePage

#### Element Visibility Waits
```java
// Wait for element to be visible
WebElement element = waitForElementToBeVisible(locator);

// Wait with custom timeout
WebElement element = waitForElementToBeVisible(locator, 15);
```

#### Element Clickability Waits
```java
// Wait for element to be clickable
WebElement element = waitForElementToBeClickable(locator);

// Wait with custom timeout
WebElement element = waitForElementToBeClickable(locator, 15);
```

#### Safe Action Methods
```java
// Safe click (waits for element to be clickable)
safeClick(locator);

// Safe send keys (waits for element to be visible)
safeSendKeys(locator, "text");

// Safe get text (waits for element to be visible)
String text = safeGetText(locator);
```

#### Page State Waits
```java
// Wait for URL to contain text
waitForUrlToContain("inventory");

// Wait for page title to contain text
waitForTitleToContain("Swag");

// Wait for page to load completely
waitForPageToLoad();
```

### 3. Advanced Wait Operations with WaitUtils

#### Custom Wait Conditions
```java
// Wait for element to be visible and enabled
WebElement element = WaitUtils.waitForElementToBeVisibleAndEnabled(driver, locator);

// Wait for element to have specific text
boolean hasText = WaitUtils.waitForElementToHaveText(driver, locator, "Expected Text");

// Wait for element to have specific attribute
boolean hasAttribute = WaitUtils.waitForElementToHaveAttribute(driver, locator, "class", "active");
```

#### Framework-Specific Waits
```java
// Wait for jQuery to finish loading
WaitUtils.waitForJQueryToLoad(driver);

// Wait for Angular to finish loading
WaitUtils.waitForAngularToLoad(driver);

// Wait for page to load completely
WaitUtils.waitForPageToLoad(driver);
```

#### Custom Wait Timeouts
```java
// Get different wait instances
WebDriverWait shortWait = WaitUtils.getShortWait(driver);     // 5 seconds
WebDriverWait defaultWait = WaitUtils.getDefaultWait(driver); // 10 seconds
WebDriverWait longWait = WaitUtils.getLongWait(driver);       // 20 seconds
WebDriverWait customWait = WaitUtils.getCustomWait(driver, 15); // 15 seconds
```

### 4. Test Class Examples

#### Basic Test with Explicit Waits
```java
@Test
public void testLogin() {
    driver.get(ConfigReader.get("web.baseUrl"));
    
    LoginPage loginPage = new LoginPage(driver);
    loginPage.waitForLoginPageToLoad(); // Wait for page elements
    loginPage.login("user", "pass");    // Uses explicit waits internally
    
    // Wait for page to load after login
    WaitUtils.waitForPageToLoad(driver);
    
    Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
}
```

#### Test with Custom Timeouts
```java
@Test
public void testSlowEnvironment() {
    driver.get(ConfigReader.get("web.baseUrl"));
    
    LoginPage loginPage = new LoginPage(driver);
    
    // Use custom timeout for slower environments
    loginPage.login("user", "pass", 20);
    
    // Wait with custom timeout
    WaitUtils.waitForPageToLoad(driver, 30);
    
    Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
}
```

## Best Practices

### 1. Always Extend BasePage
```java
public class YourPage extends BasePage {
    public YourPage(WebDriver driver) {
        super(driver);
    }
    // Your page methods here
}
```

### 2. Use Safe Methods for Common Operations
```java
// Instead of:
driver.findElement(locator).click();

// Use:
safeClick(locator);
```

### 3. Wait for Page Load After Navigation
```java
driver.get(url);
WaitUtils.waitForPageToLoad(driver);
// Or
waitForPageToLoad();
```

### 4. Use Appropriate Timeouts
```java
// For quick operations
safeClick(locator, 5);

// For slow operations
safeClick(locator, 20);
```

### 5. Check Element State Before Actions
```java
if (isElementVisible(locator)) {
    safeClick(locator);
}
```

## Common Wait Scenarios

### 1. Dynamic Content Loading
```java
// Wait for dynamic content to appear
waitForElementToBeVisible(dynamicContentLocator);

// Wait for loading spinner to disappear
waitForElementToDisappear(loadingSpinnerLocator);
```

### 2. Form Interactions
```java
// Wait for form to be ready
waitForElementToBeVisible(formLocator);

// Fill form with explicit waits
safeSendKeys(usernameLocator, username);
safeSendKeys(passwordLocator, password);
safeClick(submitButtonLocator);
```

### 3. Navigation and Redirects
```java
// Wait for redirect after action
waitForUrlToContain("success");

// Wait for page title change
waitForTitleToContain("Dashboard");
```

### 4. Error Handling
```java
// Wait for error message to appear
if (waitForElementToBeVisible(errorMessageLocator, 5)) {
    String errorText = safeGetText(errorMessageLocator);
    // Handle error
}
```

## Troubleshooting

### Common Issues

1. **TimeoutException**: Increase timeout or check if element locator is correct
2. **StaleElementReferenceException**: Use `waitForElementToBeRefreshedAndClickable()`
3. **ElementNotInteractableException**: Use `waitForElementToBeClickable()` before clicking

### Debug Tips

1. Add logging to see which wait is failing
2. Use browser developer tools to verify element locators
3. Check if elements are in iframes (use `waitForFrameAndSwitchToIt()`)
4. Verify page load completion with `waitForPageToLoad()`

## Migration Guide

### From Implicit Waits to Explicit Waits

**Before:**
```java
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
driver.findElement(locator).click();
```

**After:**
```java
// Remove implicit wait
// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

// Use explicit wait
safeClick(locator);
```

### From Direct WebDriver Calls to Page Objects

**Before:**
```java
driver.findElement(By.id("username")).sendKeys("user");
driver.findElement(By.id("password")).sendKeys("pass");
driver.findElement(By.id("login")).click();
```

**After:**
```java
LoginPage loginPage = new LoginPage(driver);
loginPage.login("user", "pass");
```

This framework provides a robust, maintainable, and scalable approach to handling waits in your UI tests while following best practices for test automation.
