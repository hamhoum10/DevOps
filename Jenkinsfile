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

        stage('SonarQube Analysis') {
            steps {
                script {
                    withSonarQubeEnv('SonarQube') {
                        sh 'mvn test jacoco:report'
                        sh 'mvn sonar:sonar'
                    }
                }
            }
        }
        
       stage('Nexus') {
         steps {
            sh 'mvn deploy -Dmaven.test.skip'
           }
        }

    }
}
