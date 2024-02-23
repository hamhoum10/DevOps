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
                   sh "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=75865852"
                }
        }
    }
}
