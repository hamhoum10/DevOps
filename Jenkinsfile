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

        stage('SonarQube Analysis') {
                steps {
                  withSonarQubeEnv('SonarQube_server') {
                    sh 'mvn clean package sonar:sonar'
                  }
                }
        }
    }
}
