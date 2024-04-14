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
        
        // stage('pushing to docker hub') {
        //     steps {
        //         script {
        //             sh('docker login -u yassine238 -p 75865852Ya')
        //             sh('docker tag sha256:1a476676cf6a7c00b695fc96eaf0ab8bdf2c3327d32223e6845dc66ad202ef37 nasriamine/devops:latest')
        //             sh('docker push yassine238/devops:latest')
        //         }
        //     }
        // }

        // stage('Docker compose') {
        //     steps {
        //         sh 'docker compose up -d --remove-orphans '
        //     }
        // }
    }
}
