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
                    sh "mvn sonar:sonar -Dsonar.host.url=http://192.168.127.132:9000 -Dsonar.login=sqa_40c39827e633ef27563bce0bd8ba5c60dca507ae"
                }
            }
        }
    }
}
