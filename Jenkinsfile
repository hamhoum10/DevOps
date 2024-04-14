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
                   
                    // Assuming SonarQube server configuration is already set up in Jenkins
                    withSonarQubeEnv('SonarQube') {
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
        
        //  stage('Building image') {
        //     steps {
        //         script {
        //             sh('docker-compose build')
        //         }
        //     }
        // }
        stage('Build image') {
            steps {
                sh 'docker build -t safagrech/devops:1.0.0 .'
            }
        }
        stage('Deploy image') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerkey', usernameVariable: 'safagrech', passwordVariable: '12345')]) {
                    sh "docker login -u ${env.safagrech} -p ${env.12345}"
                    sh 'docker push safagrech/devops:1.0.0'
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
