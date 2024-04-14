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
        
        //stage('SonarQube Analysis') {
        //    steps {
        //        script {
        //            withSonarQubeEnv('SonarQube_server') {
        //                sh 'mvn test jacoco:report'
        //                sh 'mvn sonar:sonar'
        //            }
        //        }
        //   }
        // }

        stage('Nexus') {
            steps {
                sh 'mvn deploy -Dmaven.test.skip'
              }
        } 
    
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
                    sh('docker login -u yassine238 -p 75865852Ya')
                    sh('docker tag sha256:d8d715783b80cab158f5bf9726bcada5265c1624b64ca2bb46f42f94998d4662 yassine238/devops:latest')
                    sh('docker push yassine238/devops:latest')
                }
            }
        }

        stage('Docker compose') {
            steps {
                sh 'docker compose up -d --remove-orphans '
            }
        }
    }
}
