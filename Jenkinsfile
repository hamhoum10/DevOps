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
        
        // stage('SonarQube analysis') {
        //    steps {
        //        catchError {
        //            echo 'Running SonarQube analysis...'
        //            withSonarQubeEnv('SonarQube_Jenkins') {
        //                sh 'sonar-scanner -Dsonar.projectKey=Devops -Dsonar.projectName=Devops_Project_Front -Dsonar.projectVersion=1.0.0 -Dsonar.sources=.'
        //            }
        //        }
        //    }
        // }
        
        // stage('Remove Old Docker Containers') {
        //    steps {
        //        script {
        //            try {
        //                 Remove the old Docker containers if they exist
        //                sh 'docker stop frontend_angular_app_1  || true'
        //                sh 'docker rm frontend_angular_app_1|| true'
        //            } catch (Exception e) {
        //                echo "Error occurred while removing old Docker containers: ${e.message}"
        //                currentBuild.result = 'FAILURE'
        //                error("Failed to remove old Docker containers")
        //            }
        //        }
        //    }
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
                    sh('docker login -u yassine238 -p 75865852Ya')
                    sh('docker tag sha256:d1f2611bb1a1969309f5f8c6d84dd90b6d7cac4e2593e42e40459cb996273903 yassine238/devops_frontend:latest')
                    sh('docker push yassine238/devops_frontend:latest')
                }
            }
        }

        stage('Remove Old Docker Containers') {
            steps {
                script {
                    try {
                        // Remove the old Docker containers if they exist
                        sh 'docker stop frontend_container || true'
                        sh 'docker rm frontend_container || true'
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
