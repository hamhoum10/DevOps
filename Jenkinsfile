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
        stage("MVN SONARQUABE") {
            steps {
                script {
                    mvn 'sonar:sonar -Dsonar.login=admin -Dsonar.password=0000'
                }
            }
        }

    }
}
