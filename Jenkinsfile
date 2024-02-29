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

        stage('Code Coverage') {
            steps {
                script {
                    // Generate JaCoCo coverage report
                    sh 'mvn jacoco:prepare-agent test jacoco:report'
                }
                // Publish coverage report to Jenkins
                post {
                    always {
                        // Archive the generated JaCoCo reports
                        jacoco(execPattern: 'target/jacoco.exec')
                        // Publish the JaCoCo coverage report
                        publishCoverage adapters: [jacocoAdapter('target/site/jacoco/index.html')]
                    }
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    withSonarQubeEnv('sonarqube') {
                        sh 'mvn sonar:sonar'
                    }
                }
            }
        }
    }
}
