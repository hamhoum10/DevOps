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
        stage('Remove Old Docker Containers') {
            steps {
                script {
                    try {
                        // Remove the old Docker containers if they exist
                          sh 'docker stop  devops-app mysql:8.3.0 || true'
                          sh 'docker rm  devops-app mysql:8.3.0 || true'
                    } catch (Exception e) {
                        echo "Error occurred while removing old Docker containers: ${e.message}"
                        currentBuild.result = 'FAILURE'
                        error("Failed to remove old Docker containers")
                    }
                }
            }
        }
            stage('Docker Compose') {
                        steps {
                  sh 'docker-compose up -d'
            }
                    }
                } 

                post {
                    always {
                        echo 'Cleaning up...'
                        cleanWs() // Clean workspace after build
                    }
                    success {
                        echo 'Build succeeded!'
                    }
                    failure {
                        echo 'Build failed!'
                    }
                }
}
