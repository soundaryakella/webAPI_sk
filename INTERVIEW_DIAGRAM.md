# Framework Architecture - Interview Reference

## 🎯 **Quick Framework Overview**

```
┌─────────────────────────────────────────────────────────────────┐
│                    TEST AUTOMATION FRAMEWORK                   │
├─────────────────────────────────────────────────────────────────┤
│                                                                 │
│  ┌─────────────┐    ┌─────────────┐    ┌─────────────┐        │
│  │   WEB UI    │    │    API      │    │ INTEGRATION │        │
│  │  (Selenium) │    │(REST Assured)│    │    TESTS    │        │
│  └─────────────┘    └─────────────┘    └─────────────┘        │
│         │                   │                   │              │
│         ▼                   ▼                   ▼              │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │                TESTNG EXECUTION ENGINE                │   │
│  │  @Test | DataProvider | Listeners | Retry Analyzer    │   │
│  └─────────────────────────────────────────────────────────┘   │
│                              │                                 │
│                              ▼                                 │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │                  FRAMEWORK LAYERS                     │   │
│  │                                                       │   │
│  │  ┌─────────────────────────────────────────────────┐ │   │
│  │  │              TEST LAYER                         │ │   │
│  │  │  LoginTest | UserApiTest | BaseTest            │ │   │
│  │  └─────────────────────────────────────────────────┘ │   │
│  │                              │                       │   │
│  │  ┌─────────────────────────────────────────────────┐ │   │
│  │  │              PAGE LAYER                         │ │   │
│  │  │  BasePage | LoginPage | UploadPage             │ │   │
│  │  └─────────────────────────────────────────────────┘ │   │
│  │                              │                       │   │
│  │  ┌─────────────────────────────────────────────────┐ │   │
│  │  │             UTILITY LAYER                       │ │   │
│  │  │ DriverFactory | ConfigReader | WaitUtils       │ │   │
│  │  │ ApiClient | ExcelUtils | ScreenshotUtil        │ │   │
│  │  └─────────────────────────────────────────────────┘ │   │
│  │                              │                       │   │
│  │  ┌─────────────────────────────────────────────────┐ │   │
│  │  │            REPORTING LAYER                      │ │   │
│  │  │ ExtentReports | ReportManager | ExtentListener │ │   │
│  │  └─────────────────────────────────────────────────┘ │   │
│  └─────────────────────────────────────────────────────────┘   │
│                              │                                 │
│                              ▼                                 │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │              EXTERNAL INTEGRATIONS                    │   │
│  │  Jenkins | GitHub | Maven | Java 11                   │   │
│  └─────────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────┘
```

## 🚀 **Jenkins CI/CD Pipeline**

```
┌─────────────────────────────────────────────────────────────────┐
│                    JENKINS PIPELINE                            │
├─────────────────────────────────────────────────────────────────┤
│                                                                 │
│  Stage 1: Checkout Code                                        │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │ git clone repository                                   │   │
│  └─────────────────────────────────────────────────────────┘   │
│                              │                                 │
│  Stage 2: Build & Dependencies                                │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │ mvn clean compile                                       │   │
│  │ mvn dependency:resolve                                  │   │
│  └─────────────────────────────────────────────────────────┘   │
│                              │                                 │
│  Stage 3: Web Tests                                          │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │ mvn test -DsuiteXmlFile=testng-web.xml                 │   │
│  │ -Dheadless=true -DthreadCount=3                        │   │
│  └─────────────────────────────────────────────────────────┘   │
│                              │                                 │
│  Stage 4: API Tests                                          │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │ mvn test -DsuiteXmlFile=testng-api.xml                 │   │
│  └─────────────────────────────────────────────────────────┘   │
│                              │                                 │
│  Stage 5: Report Generation                                  │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │ Archive Extent Reports                                  │   │
│  │ Publish Test Results                                    │   │
│  │ Send Email Notifications                                │   │
│  └─────────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────┘
```

## 📁 **Project Structure**

```
src/
├── main/java/
│   ├── pages/                    # Page Object Model
│   │   ├── BasePage.java        # Base page with explicit waits
│   │   ├── LoginPage.java       # Login page actions
│   │   └── UploadFilePage.java  # File upload actions
│   ├── utils/                   # Utility classes
│   │   ├── DriverFactory.java   # Thread-safe WebDriver
│   │   ├── ConfigReader.java    # Configuration management
│   │   ├── WaitUtils.java       # Explicit wait utilities
│   │   ├── ApiClient.java       # API client for REST calls
│   │   ├── ExcelUtils.java      # Excel data handling
│   │   ├── ScreenshotUtil.java  # Screenshot capture
│   │   ├── ReportManager.java   # Extent Reports setup
│   │   └── ExtentTestManager.java # Test reporting
│   └── dataprovider/            # Test data providers
│       └── LoginPageDataProvider.java # JSON-based test data
├── test/java/
│   ├── base/                    # Base test classes
│   │   └── BaseTest.java        # Common test setup
│   ├── webTests/               # Web UI tests
│   │   └── LoginTest.java       # Login functionality tests
│   ├── apiTests/               # API tests
│   │   └── UserApiTest.java     # User API tests
│   └── listeners/              # TestNG listeners
│       └── ExtentTestListener.java # Extent Reports integration
└── test/resources/
    ├── testdata/               # Test data files
    │   ├── loginData.json      # JSON test data
    │   ├── loginData.properties # Properties test data
    │   └── loginData.csv       # CSV test data
    └── config.properties       # Framework configuration
```

## 🎯 **Key Interview Points**

### **1. Framework Design Principles:**
- **Maintainability**: Page Object Model, reusable utilities
- **Scalability**: Thread-safe execution, parallel testing
- **Reliability**: Explicit waits, retry mechanisms
- **Reporting**: Comprehensive test reporting with screenshots

### **2. Technical Highlights:**
- **Thread Safety**: ThreadLocal WebDriver for parallel execution
- **Data Management**: Multiple data sources (JSON, Excel, Properties)
- **Wait Strategy**: Explicit waits over implicit waits
- **API Testing**: REST Assured with JSON validation
- **CI/CD Integration**: Jenkins pipeline with automated reporting

### **3. Best Practices Implemented:**
- **Separation of Concerns**: Clear layer separation
- **Configuration Management**: Environment-specific settings
- **Error Handling**: Screenshot capture on failures
- **Test Data Management**: External data files
- **Reporting**: Rich HTML reports with metrics

### **4. Performance Optimizations:**
- **Parallel Execution**: 3-5 threads for optimal performance
- **Headless Mode**: Faster execution in CI/CD
- **Selective Testing**: Run specific test suites
- **Resource Management**: Proper driver cleanup

## 🔧 **Technology Stack**

### **Core Technologies:**
- **Java 11** - Programming language
- **TestNG** - Test framework and execution engine
- **Selenium WebDriver** - Web UI automation
- **REST Assured** - API testing
- **Maven** - Build and dependency management

### **Reporting & Utilities:**
- **Extent Reports** - HTML test reporting
- **Apache POI** - Excel file handling
- **Jackson** - JSON processing
- **ThreadLocal** - Thread-safe execution

### **CI/CD & Version Control:**
- **Jenkins** - Continuous Integration/Deployment
- **GitHub** - Version control
- **TestNG XML** - Test configuration

## 📊 **Test Execution Flow**

### **Web Test Flow:**
```
BaseTest → DriverFactory → Page Objects → Explicit Waits → Actions → Screenshots → Reports
```

### **API Test Flow:**
```
BaseTest → ApiClient → REST Calls → JSON Validation → Response Assertions → Reports
```

### **Jenkins Pipeline Flow:**
```
Checkout → Build → Web Tests → API Tests → Report Generation → Notifications
```

This framework demonstrates enterprise-level test automation with modern tools, best practices, and comprehensive CI/CD integration.

