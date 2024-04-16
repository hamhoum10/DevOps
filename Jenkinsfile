pipeline {
    agent any
    
   
    stages {
        
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
        
        stage('SonarQube analysis') {
           steps {
               catchError {
                   echo 'Running SonarQube analysis...'
                   withSonarQubeEnv('SonarQube_Jenkins') {
                       sh 'sonar-scanner -Dsonar.projectKey=Devops -Dsonar.projectName=Devops_Project_Front -Dsonar.projectVersion=1.0.0 -Dsonar.sources=.'
                   }
               }
           }
        }
        
        //stage('Remove Old Docker Containers') {
        //    steps {
        //        script {
        //            try {
                        // Remove the old Docker containers if they exist
        //                sh 'docker stop frontend_angular_app_1  || true'
        //                sh 'docker rm frontend_angular_app_1|| true'
        //            } catch (Exception e) {
        //                echo "Error occurred while removing old Docker containers: ${e.message}"
        //                currentBuild.result = 'FAILURE'
        //                error("Failed to remove old Docker containers")
        //            }
        //        }
        //    }
        //}
        
        //stage('Docker Build and Push') {
        //    steps {
        //        catchError {
        //            echo 'Building Docker image...'
        //            sh 'docker build -t esmcompte/devops_frontend:latest .'
        //            echo 'Logging in to Docker Hub...'
        //            sh 'docker login -u esmcompte -p mdp'
        //            echo 'Pushing Docker image to Docker Hub...'
        //            sh 'docker push esmcompte/devops_frontend:latest'
        //        }
        //    }
        //}
        
        //stage('Docker Compose') {
        //    steps {
        //        catchError {
        //            sh 'docker-compose up -d'
        //        }
        //    }
        //}
    }
}
