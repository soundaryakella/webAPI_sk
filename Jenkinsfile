pipeline {
    agent any

    tools {
        maven 'Maven_3.9.6'   // name configured in Jenkins Global Tools
        jdk 'Java_17'       // name configured in Jenkins Global Tools
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/soundaryakella/webAPI_sk.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('Run Tests') {
            steps {
                // Run regression suite
                sh 'mvn test -DsuiteXmlFile=testng.xml'
            }
        }

        stage('Publish Extent Report') {
            steps {
                publishHTML([allowMissing: false,
                             alwaysLinkToLastBuild: true,
                             keepAll: true,
                             reportDir: 'reports',
                             reportFiles: 'extent-report.html',
                             reportName: 'Extent Report'])
            }
        }
    }

    post {
        always {
            // archive report for history
            archiveArtifacts artifacts: 'reports/*.html', fingerprint: true
        }
        failure {
            mail to: 'qa-team@company.com',
                 subject: "Pipeline Failed: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                 body: "Check Jenkins for details: ${env.BUILD_URL}"
        }
    }
}
