pipeline {
    agent any

    tools {
        maven 'Maven3'
        jdk 'JDK21'
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/archu1190/selenium-framework.git'
            }
        }

        stage('Build & Test') {
            steps {
                bat "mvn clean test -DsuiteXmlFile=testng.xml"
            }
        }

        stage('Archive Reports') {
            steps {
                archiveArtifacts artifacts: 'reports/**', fingerprint: true
            }
        }
    }
}