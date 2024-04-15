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
       stage('Building Docker Image') {
            steps {
                script {
                    // Build the Docker image using the Dockerfile in the current directory
                    sh 'docker build -t nasriamine/devopsnew:latest .'
                }
            }
        }
        stage('pushing to docker hub') {
            steps {
                script {
                    sh('docker login -u nasriamine -p 25059373Hadil')
                    sh('docker tag sha256:1a476676cf6a7c00b695fc96eaf0ab8bdf2c3327d32223e6845dc66ad202ef37 nasriamine/devopsnew:latest')
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
