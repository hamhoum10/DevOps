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

        stage('Remove Old Docker Containers') {
            steps {
                script {
                    try {
                        // Remove the old Docker containers if they exist
                        sh 'docker stop devops-app mysql:8.3.0 || true'
                        sh 'docker rm devops-app mysql:8.3.0 || true'
                    } catch (Exception e) {
                        echo "Error occurred while removing old Docker containers: ${e.message}"
                        currentBuild.result = 'FAILURE'
                        error("Failed to remove old Docker containers")
                    }
                }
            }
        }

        stage('Nexus') {
            steps {
                script {
                    // Set up Maven settings.xml with Nexus token authentication
                    sh '''
                        echo '<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
                                    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                                    xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                                                        http://maven.apache.org/xsd/settings-1.0.0.xsd">
                                  <servers>
                                    <server>
                                      <id>nexus-repo-id</id>
                                      <username>admin</username>
                                      <password>e7e37639-9907-35d7-8e50-107e982623da</password>
                                    </server>
                                  </servers>
                                </settings>' > settings.xml
                    '''
                    sh 'mvn deploy -Dmaven.test.skip --settings settings.xml'
                }
            }
        }

        stage('Building image') {
            steps {
                script {
                    sh 'docker-compose build'
                }
            }
        }

        stage('Pushing to Docker Hub') {
            steps {
                script {
                    sh 'docker login -u nasriamine -p 25059373Hadil'
                    sh 'docker tag sha256:1a476676cf6a7c00b695fc96eaf0ab8bdf2c3327d32223e6845dc66ad202ef37 nasriamine/devopsnew:latest'
                    sh 'docker push nasriamine/devopsnew:latest'
                }
            }
        }

        stage('Docker Compose') {
            steps {
                sh 'docker compose up -d --remove-orphans'
            }
        }
    }
}
