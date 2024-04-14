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
         stage('Nexus') {
      steps {
        sh 'mvn deploy -Dmaven.test.skip'
      }
    }    
        
        // stage('SonarQube Analysis') {
        //     steps {
        //         script {
        //             withSonarQubeEnv('sonarqube') {
        //                 sh 'mvn test jacoco:report'
        //                 sh 'mvn sonar:sonar'
        //             }
        //         }
        //     }
        // }
        stage('Building image') {
            steps {
                script {
                    sh('docker-compose build')
                }
            }
        }
         stage('pushing to docker hub') {
            steps {
                script {
                    sh(' docker push 98944696/dev ')
                }
            }
        }
    }
}
