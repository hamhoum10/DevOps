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
        stage('Build image') {
            steps {
                sh 'docker build -t safagrech/devops:1.0.0 .'
            }
        }
        stage('pushing to docker hub') {
            steps {
                script {
                    sh('docker login -u safagrech -p 123456789')
                    sh('docker tag sha256:15f6fdc3fe63910c402bf0a9f3db022dfde28db1292eaaa0cae25b0a57b12316 safagrech/devops:1.0.0')
                    sh('docker push safagrech/devops:1.0.0')
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
