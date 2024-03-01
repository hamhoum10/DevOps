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

<<<<<<< HEAD
        stage('SonarQube Analysis') {
            steps {
                script {
                    withSonarQubeEnv('sonarQube') {
=======

>>>>>>> 6f2aed737f681b62e968143227b29973b0858238
                        sh 'mvn sonar:sonar'
                    }
                }
            }
        }
<<<<<<< HEAD
=======


        
>>>>>>> 6f2aed737f681b62e968143227b29973b0858238
    }
}
