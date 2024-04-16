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
                    sh('docker login -u 98944696 -p omriyasser')
                    sh('docker tag sha256:9d21ad6d5203f15a55ef3ab097d95ebd1aecc0b7fdb3367980de2cd321c02b63 98944696/de:latest')
                    sh('docker push 98944696/de:latest')
                }
            }
        }
        stage('Remove Old Docker Containers') {
            steps {
                script {
                    try {
                        // Remove the old Docker containers if they exist
                          sh 'docker stop devops-app-1 devops-mysqldb-1 || true'
                        sh 'docker rm devops-app-1 devops-mysqldb-1 || true'
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
                } // End of stages block

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
} // End of pipeline block
