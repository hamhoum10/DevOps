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
         stage('npm install ') {
                    steps {
                        script {
                            sh 'npm i'
                        }
                    }
                }
                stage('npm build') {
                                    steps {
                                        script {
                                            sh 'npm build'
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
                    sh('docker tag sha256:f01c5e66172c66fac893c1bde9bdebe71cf2ed36356b1c0a98571a256f3ebe4f 98944696/dev:latest')
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
