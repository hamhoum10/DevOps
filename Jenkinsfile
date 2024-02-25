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
                   sh 'mvn sonar:sonar -Dsonar.host.url=http://192.168.127.132:9000 -Dsonar.token=sqa_6de8c1d46dca04af1233a03266552cb15046182c -Dsonar.projectKey=Jenkins_pipeline -Dsonar.projectName=DevOps -Dsonar.projectVersion=1.0.0'
                }
        }
    }
}
