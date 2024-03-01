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
            steps {
                script {
                    sh 'mvn test'
                }
            }
        }

        stage('Unit Test') {
            steps {
                script {
                    sh 'mvn test'
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    withSonarQubeEnv('sonarQube') {
                        sh 'mvn sonar:sonar'
                    }
                }
            }
        }
    }
}
