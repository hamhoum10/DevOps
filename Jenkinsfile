pipeline {
    agent any

    stages {

// stage('Build front') {
    
//             steps {
               
//                 sh 'npm install'
//                 sh 'npm run build'
//             }
//         }
// stage('Build Docker Image front') {
//             steps {
                
//                 script {
//                     docker.build('98944696/angular-app',"/DevOps_Project_Front")
//                 }
//             }
//         }
//         stage('Push to Docker Hub front') {
//             steps {
//                 script {
//                     sh('docker login -u 98944696 -p omriyasser')
//         //             sh('docker tag sha256:f01c5e66172c66fac893c1bde9bdebe71cf2ed36356b1c0a98571a256f3ebe4f 98944696/angular-app:latest')
//                     sh('docker push 98944696/angular-app:latest')
//                 }
//             }


        stage('Clean') {
            steps {
                script {
                    sh 'mvn clean'
                }
            }
        }
         // stage('npm install ') {
         //            steps {
         //                script {
         //                    sh 'npm i'
         //                }
         //            }
         //        }
                // stage('npm build') {
                //                     steps {
                //                         script {
                //                             sh 'npm build'
                //                         }
                //                     }
                //                 }

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
          stage('pushing to docker hub') {
            steps {
                script {
                    sh('docker login -u 98944696 -p omriyasser')
                    sh('docker tag sha256:5fd0d77c4e27dc7a114909acb455c388f89217f1a09c3b9e7f66ad79aad8312c 98944696/dev:latest')
                    sh('docker push 98944696/dev:latest')
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
