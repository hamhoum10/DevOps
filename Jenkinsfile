pipeline {
    agent any

    stages {
        stage('Clean') {
            steps {
                script {
                    sh 'mvn clean'
                }
            }
        }

        stage('Compile') {
            steps {
                script {
                    sh 'mvn compile'
                }
            }
        }
        
        stage('Unit Test') {
          steps{
            script {
             sh 'mvn test'
            }
          }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    // Assuming SonarQube server configuration is already set up in Jenkins
                    withSonarQubeEnv('sonarqube') {
                        sh 'mvn sonar:sonar'
                    }
                }
            }
        }
    }
}
