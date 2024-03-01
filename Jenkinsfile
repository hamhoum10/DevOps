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
         stage('test') {
            steps {
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
                        sh 'mvn test jacoco:report'
                        sh 'mvn sonar:sonar'
                    }
                }
            }
        }


        
    }
}
