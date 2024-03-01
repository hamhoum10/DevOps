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

<<<<<<< HEAD
        stage('SonarQube Analysis') {
            steps {
                script {
                    // Assuming SonarQube server configuration is already set up in Jenkins
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
