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
            environment {
                // Define SonarQube server configuration
                SONARQUBE_HOST = 'http://192.168.127.132:9000/'
                SONARQUBE_TOKEN = 'sqa_2552c4c26e7488ae3e24692fdda7db64371b6b16'
            }
            steps {
                script {
                    sh "mvn sonar:sonar -Dsonar.host.url=${env.SONARQUBE_HOST} -Dsonar.login=${env.SONARQUBE_TOKEN}"
                }
            }
        }
    }
}
