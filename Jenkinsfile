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

            stage('MVN Sonarqube') {
            steps {
                script {
                   sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=0000'
                }
            }
        }
    }
}
