# Web + API Automation Framework Architecture

## 🏗️ **Framework Overview**

This is a comprehensive test automation framework that combines **Web UI Testing** and **API Testing** using modern tools and best practices.

## 📊 **Framework Architecture Diagram**

```
┌─────────────────────────────────────────────────────────────────────────────────┐
│                           TEST AUTOMATION FRAMEWORK                            │
├─────────────────────────────────────────────────────────────────────────────────┤
│                                                                                 │
│  ┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐            │
│  │   WEB TESTS     │    │   API TESTS     │    │  INTEGRATION    │            │
│  │   (Selenium)    │    │ (REST Assured)  │    │     TESTS       │            │
│  └─────────────────┘    └─────────────────┘    └─────────────────┘            │
│           │                       │                       │                    │
│           ▼                       ▼                       ▼                    │
│  ┌─────────────────────────────────────────────────────────────────────────┐   │
│  │                        TESTNG EXECUTION ENGINE                        │   │
│  │  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐   │   │
│  │  │   @Test     │  │ DataProvider│  │  Listeners  │  │  Retry      │   │   │
│  │  │ Annotations │  │   (JSON)    │  │ (Extent)    │  │  Analyzer   │   │   │
│  │  └─────────────┘  └─────────────┘  └─────────────┘  └─────────────┘   │   │
│  └─────────────────────────────────────────────────────────────────────────┘   │
│                                   │                                             │
│                                   ▼                                             │
│  ┌─────────────────────────────────────────────────────────────────────────┐   │
│  │                        FRAMEWORK LAYERS                               │   │
│  │                                                                       │   │
│  │  ┌─────────────────────────────────────────────────────────────────┐ │   │
│  │  │                    TEST LAYER                                  │ │   │
│  │  │  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐            │ │   │
│  │  │  │ Web Tests   │  │ API Tests   │  │ Base Tests  │            │ │   │
│  │  │  │ (LoginTest) │  │(UserApiTest)│  │ (BaseTest)  │            │ │   │
│  │  │  └─────────────┘  └─────────────┘  └─────────────┘            │ │   │
│  │  └─────────────────────────────────────────────────────────────────┘ │   │
│  │                                   │                                   │   │
│  │  ┌─────────────────────────────────────────────────────────────────┐ │   │
│  │  │                   PAGE LAYER                                  │ │   │
│  │  │  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐            │ │   │
│  │  │  │ BasePage    │  │ LoginPage   │  │ UploadPage  │            │ │   │
│  │  │  │ (Waits)     │  │ (Actions)   │  │ (Actions)   │            │ │   │
│  │  │  └─────────────┘  └─────────────┘  └─────────────┘            │ │   │
│  │  └─────────────────────────────────────────────────────────────────┘ │   │
│  │                                   │                                   │   │
│  │  ┌─────────────────────────────────────────────────────────────────┐ │   │
│  │  │                  UTILITY LAYER                                │ │   │
│  │  │  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐            │ │   │
│  │  │  │DriverFactory│  │ConfigReader │  │ WaitUtils   │            │ │   │
│  │  │  │(ThreadSafe) │  │(Properties) │  │ (Explicit)  │            │ │   │
│  │  │  └─────────────┘  └─────────────┘  └─────────────┘            │ │   │
│  │  │  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐            │ │   │
│  │  │  │ApiClient    │  │ExcelUtils   │  │ScreenshotUtil│           │ │   │
│  │  │  │(REST)       │  │(Test Data)  │  │(Screenshots)│            │ │   │
│  │  │  └─────────────┘  └─────────────┘  └─────────────┘            │ │   │
│  │  └─────────────────────────────────────────────────────────────────┘ │   │
│  │                                   │                                   │   │
│  │  ┌─────────────────────────────────────────────────────────────────┐ │   │
│  │  │                  REPORTING LAYER                              │ │   │
│  │  │  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐            │ │   │
│  │  │  │ExtentReports│  │ReportManager│  │ExtentListener│           │ │   │
│  │  │  │(HTML)       │  │(Setup)      │  │(Test Events)│            │ │   │
│  │  │  └─────────────┘  └─────────────┘  └─────────────┘            │ │   │
│  │  └─────────────────────────────────────────────────────────────────┘ │   │
│  └─────────────────────────────────────────────────────────────────────────┘   │
│                                   │                                             │
│                                   ▼                                             │
│  ┌─────────────────────────────────────────────────────────────────────────┐   │
│  │                        EXTERNAL INTEGRATIONS                          │   │
│  │  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐   │   │
│  │  │   Jenkins   │  │   GitHub    │  │   Maven     │  │   Java 11   │   │   │
│  │  │   (CI/CD)   │  │  (Version)  │  │ (Build)     │  │  (Runtime)  │   │   │
│  │  └─────────────┘  └─────────────┘  └─────────────┘  └─────────────┘   │   │
│  └─────────────────────────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────────────────────┘
```

## 🛠️ **Technology Stack**

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

## 📁 **Framework Structure**

```
src/
├── main/java/
│   ├── pages/                    # Page Object Model
│   │   ├── BasePage.java        # Base page with explicit waits
│   │   ├── LoginPage.java       # Login page actions
│   │   └── UploadFilePage.java  # File upload actions
│   ├── utils/                   # Utility classes
│   │   ├── DriverFactory.java   # Thread-safe WebDriver management
│   │   ├── ConfigReader.java    # Configuration management
│   │   ├── WaitUtils.java       # Explicit wait utilities
│   │   ├── ApiClient.java       # API client for REST calls
│   │   ├── ExcelUtils.java      # Excel data handling
│   │   ├── ScreenshotUtil.java  # Screenshot capture
│   │   ├── ReportManager.java   # Extent Reports setup
│   │   └── ExtentTestManager.java # Test reporting management
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

## 🔄 **Framework Flow**

### **1. Test Execution Flow:**
```
TestNG XML → Test Classes → Page Objects → WebDriver/API → Assertions → Reports
```

### **2. Web Test Flow:**
```
BaseTest → DriverFactory → Page Objects → Explicit Waits → Actions → Screenshots → Reports
```

### **3. API Test Flow:**
```
BaseTest → ApiClient → REST Calls → JSON Validation → Response Assertions → Reports
```

## 🎯 **Key Framework Features**

### **1. Page Object Model (POM)**
- **BasePage**: Common functionality with explicit waits
- **Page Classes**: Specific page actions and elements
- **Reusable Methods**: Safe actions with built-in waits

### **2. Thread-Safe Execution**
- **ThreadLocal WebDriver**: Isolated driver instances
- **Parallel Execution**: TestNG parallel configuration
- **DataProvider Parallel**: Concurrent test data processing

### **3. Explicit Wait Strategy**
- **WebDriverWait**: Selenium explicit waits
- **Custom Wait Methods**: Reusable wait utilities
- **Configurable Timeouts**: Environment-specific settings

### **4. Data-Driven Testing**
- **JSON DataProvider**: Structured test data
- **Multiple Formats**: JSON, Properties, CSV support
- **Dynamic Data**: Runtime data generation

### **5. Comprehensive Reporting**
- **Extent Reports**: Rich HTML reports
- **Screenshots**: Automatic failure capture
- **Test Metrics**: Pass/fail statistics
- **Retry Handling**: No duplicate entries

## 🚀 **Jenkins CI/CD Pipeline**

### **Pipeline Stages:**

```
┌─────────────────────────────────────────────────────────────────┐
│                    JENKINS PIPELINE                            │
├─────────────────────────────────────────────────────────────────┤
│                                                                 │
│  Stage 1: Checkout Code                                        │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │ git clone https://github.com/your-repo/automation.git  │   │
│  └─────────────────────────────────────────────────────────┘   │
│                              │                                 │
│  Stage 2: Build & Dependencies                                │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │ mvn clean compile                                       │   │
│  │ mvn dependency:resolve                                  │   │
│  └─────────────────────────────────────────────────────────┘   │
│                              │                                 │
│  Stage 3: Code Quality                                       │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │ mvn checkstyle:check                                    │   │
│  │ mvn spotbugs:check                                      │   │
│  └─────────────────────────────────────────────────────────┘   │
│                              │                                 │
│  Stage 4: Web Tests                                          │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │ mvn test -DsuiteXmlFile=testng-web.xml                 │   │
│  │ -Dheadless=true -DthreadCount=3                        │   │
│  └─────────────────────────────────────────────────────────┘   │
│                              │                                 │
│  Stage 5: API Tests                                          │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │ mvn test -DsuiteXmlFile=testng-api.xml                 │   │
│  │ -Dapi.baseUrl=https://api.example.com                  │   │
│  └─────────────────────────────────────────────────────────┘   │
│                              │                                 │
│  Stage 6: Integration Tests                                  │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │ mvn test -DsuiteXmlFile=testng-integration.xml         │   │
│  └─────────────────────────────────────────────────────────┘   │
│                              │                                 │
│  Stage 7: Report Generation                                  │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │ Archive Extent Reports                                  │   │
│  │ Publish Test Results                                    │   │
│  │ Send Email Notifications                                │   │
│  └─────────────────────────────────────────────────────────┘   │
│                              │                                 │
│  Stage 8: Deployment (Optional)                              │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │ Deploy to Staging                                       │   │
│  │ Run Smoke Tests                                         │   │
│  │ Deploy to Production                                    │   │
│  └─────────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────┘
```

### **Jenkins Configuration:**

#### **1. Pipeline Script (Jenkinsfile):**
```groovy
pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/your-repo/automation.git'
            }
        }
        
        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }
        
        stage('Web Tests') {
            steps {
                sh 'mvn test -DsuiteXmlFile=testng-web.xml -Dheadless=true'
            }
            post {
                always {
                    publishHTML([
                        allowMissing: false,
                        alwaysLinkToLastBuild: true,
                        keepAll: true,
                        reportDir: 'reports',
                        reportFiles: 'extent.html',
                        reportName: 'Extent Reports'
                    ])
                }
            }
        }
        
        stage('API Tests') {
            steps {
                sh 'mvn test -DsuiteXmlFile=testng-api.xml'
            }
        }
        
        stage('Publish Results') {
            steps {
                publishTestResults testResultsPattern: 'target/surefire-reports/*.xml'
                emailext (
                    subject: "Test Results: ${env.JOB_NAME} - ${env.BUILD_NUMBER}",
                    body: "Build: ${env.BUILD_URL}\nResults: ${env.BUILD_URL}testReport/",
                    to: "team@company.com"
                )
            }
        }
    }
}
```

#### **2. Jenkins Job Configuration:**
- **Source Code Management**: GitHub integration
- **Build Triggers**: Webhook on push, scheduled builds
- **Build Environment**: Maven, Java 11, Chrome browser
- **Post-build Actions**: Test result publishing, email notifications

## 📊 **Test Execution Strategies**

### **1. Parallel Execution:**
```xml
<suite name="Test Suite" parallel="methods" thread-count="5">
    <test name="Web Tests" parallel="methods" thread-count="3">
        <classes>
            <class name="webTests.LoginTest"/>
        </classes>
    </test>
</suite>
```

### **2. Environment-Specific Execution:**
```bash
# Development
mvn test -Denv=dev -DsuiteXmlFile=testng-dev.xml

# Staging
mvn test -Denv=staging -DsuiteXmlFile=testng-staging.xml

# Production
mvn test -Denv=prod -DsuiteXmlFile=testng-prod.xml
```

### **3. Test Categories:**
```bash
# Smoke Tests
mvn test -Dgroups=smoke

# Regression Tests
mvn test -Dgroups=regression

# API Tests Only
mvn test -DsuiteXmlFile=testng-api.xml
```

## 🎯 **Interview Key Points**

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

This framework demonstrates enterprise-level test automation with modern tools, best practices, and comprehensive CI/CD integration.

