pipeline {
    agent any
    
    tools {
        maven 'Maven-3.8.6'
        jdk 'JDK-11'
    }
    
    stages {
        stage('Checkout Code') {
            steps {
                echo 'Checking out code from repository...'
                git branch: 'main', url: 'https://github.com/your-repo/automation-framework.git'
            }
        }
        
        stage('Build Project') {
            steps {
                echo 'Building project with Maven...'
                sh 'mvn clean compile'
            }
        }
        
        stage('Code Quality Check') {
            steps {
                echo 'Running code quality checks...'
                sh 'mvn checkstyle:check'
                sh 'mvn spotbugs:check'
            }
        }
        
        stage('Web UI Tests') {
            steps {
                echo 'Running Web UI tests...'
                sh 'mvn test -DsuiteXmlFile=testng-web.xml -Dheadless=true -DthreadCount=3'
            }
            post {
                always {
                    publishHTML([
                        allowMissing: false,
                        alwaysLinkToLastBuild: true,
                        keepAll: true,
                        reportDir: 'reports',
                        reportFiles: 'extent.html',
                        reportName: 'Web Test Reports'
                    ])
                }
            }
        }
        
        stage('API Tests') {
            steps {
                echo 'Running API tests...'
                sh 'mvn test -DsuiteXmlFile=testng-api.xml -Dapi.baseUrl=https://api.example.com'
            }
            post {
                always {
                    publishHTML([
                        allowMissing: false,
                        alwaysLinkToLastBuild: true,
                        keepAll: true,
                        reportDir: 'reports',
                        reportFiles: 'extent.html',
                        reportName: 'API Test Reports'
                    ])
                }
            }
        }
        
        stage('Integration Tests') {
            steps {
                echo 'Running Integration tests...'
                sh 'mvn test -DsuiteXmlFile=testng-integration.xml'
            }
        }
        
        stage('Publish Results') {
            steps {
                echo 'Publishing test results...'
                publishTestResults testResultsPattern: 'target/surefire-reports/*.xml'
                
                // Archive reports
                archiveArtifacts artifacts: 'reports/**/*', fingerprint: true
                
                // Send email notification
                emailext (
                    subject: "Test Results: ${env.JOB_NAME} - Build #${env.BUILD_NUMBER}",
                    body: """
                        Build: ${env.BUILD_URL}
                        Test Results: ${env.BUILD_URL}testReport/
                        Extent Reports: ${env.BUILD_URL}artifact/reports/extent.html
                        
                        Build Status: ${currentBuild.result ?: 'SUCCESS'}
                        Duration: ${currentBuild.durationString}
                    """,
                    to: "team@company.com",
                    attachLog: true
                )
            }
        }
    }
    
    post {
        always {
            echo 'Pipeline execution completed.'
            cleanWs()
        }
        success {
            echo 'All tests passed successfully!'
        }
        failure {
            echo 'Some tests failed. Check the reports for details.'
        }
        unstable {
            echo 'Build is unstable. Some tests failed or were skipped.'
        }
    }
}