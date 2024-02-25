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

        stage('SonarQube Analysis') {
                steps {
                    script {
                        withSonarQubeEnv('SonarQube_server') {
                            sh 'mvn sonar:sonar'
                        }
                    }
                }
        }
    }
}
