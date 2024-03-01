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
        
        stage('sonarQube Analysis') {
            steps {
                script {
                    // Assuming SonarQube server configuration is already set up in Jenkins
                    withSonarQubeEnv('sonarQube') {
                        sh 'mvn sonar:sonar'
                    }
                }
            }
        }
    }
}
