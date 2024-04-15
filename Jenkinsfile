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
        
        stage('pushing to docker hub') {
            steps {
                script {
                    sh('docker login -u yassine238 -p 75865852Ya')
                    sh('docker tag sha256:94ebc44ee4e1e4ee88266abce5d0e7f9386390fc7c2ba438b664109b0d63efa8 yassine238/devops:latest')
                    sh('docker push yassine238/devops:latest')
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
        
        stage('Docker compose') {
            steps {
                sh 'docker compose up -d --remove-orphans '
            }
        }
    }
}
