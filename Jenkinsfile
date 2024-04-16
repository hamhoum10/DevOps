pipeline {
    agent any
    
   
    stages {
      
      stage('Node Clean') {  
            steps {
                echo 'Cleaning node_modules...'
                sh 'rm -rf node_modules || true'
            }
        }
        
        stage('Install dependencies') {
            steps {
                script {
                    sh 'npm install'
                }
            }
        }
        
        stage('Build application') {
            steps {
                script {
                    sh 'npm run build'
                }
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
                    sh('docker login -u nasriamine -p 25059373Hadil')
                    sh('docker tag sha256:4aadbe1c7333d3bf24daad8cc3a914aa5f120f4660e27b65d6b5beff197656f8 nasriamine/devopsnew:latest')
                    sh('docker push nasriamine/devopsnew:latest')
                }
            }
        }

        stage('Remove Old Docker Containers') {
            steps {
                script {
                    try {
                        // Remove the old Docker containers if they exist
                        sh 'docker stop devopsfrontend_angular_app_1 || true'
                        sh 'docker rm devopsfrontend_angular_app_1 || true'
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
               catchError {
                   sh 'docker-compose up -d'
               }
           }
        }
    }
}
