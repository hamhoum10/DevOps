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
        stage("SonarQube Analysis") {
            steps {
                withSonarQubeEnv('0000') {
                    sh 'mvn sonar:sonar'
                }
            }
        }

    }
}
