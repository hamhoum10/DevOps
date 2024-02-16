pipeline {
    agent any

  

        stage('Clean') {
            steps {
                // Execute 'mvn clean' command
                sh 'mvn clean'
            }
        }

        stage('Compile') {
            steps {
                // Execute 'mvn compile' command
                sh 'mvn compile'
            }
        }
    }
}
