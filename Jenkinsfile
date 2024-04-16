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
        stage('Remove Old Docker Containers') {
    steps {
        script {
            try {
                // Remove the old Docker containers if they exist
                sh 'docker stop  devops-app || true'
                sh 'docker rm  devops-app  || true'
            } catch (Exception e) {
                echo "Error occurred while removing old Docker containers: ${e.message}"
                currentBuild.result = 'FAILURE'
                error("Failed to remove old Docker containers")
            }
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
                    sh('docker login -u nasriamine -p 25059373Hadil')
                    sh('docker tag sha256:4aadbe1c7333d3bf24daad8cc3a914aa5f120f4660e27b65d6b5beff197656f8 nasriamine/devopsnew:latest')
                    sh('docker push nasriamine/devopsnew:latest')
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
