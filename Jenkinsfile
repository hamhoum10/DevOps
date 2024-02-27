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

        stage('MVN Sonarqube') {
            steps {
                script {
                    sh 'mvn sonar:sonar -Dsonar.host.url=http://192.168.8.135:9000 -Dsonar.token=sqa_de3fed6794743dfc30335b35b6f51a341a27cb97 -Dsonar.projectKey=Devops -Dsonar.projectName=Devops -Dsonar.projectVersion=1.0.0'
                }
            }
        }
    }
}
