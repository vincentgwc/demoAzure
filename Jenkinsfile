pipeline {
    agent any


    stages {
        stage('Start') {
            steps {
                echo 'Starting Pulse SDK Build Pipeline'
            }
        }

        stage('Checkout from Git') {
            steps {
                git branch: 'main', 
                credentialsId: '51827d28-bd7f-4db8-a39d-3d42c4bd15fb', 
                url: 'https://github.com/vincentgwc/demoAzure.git'
            }

        }

        stage('Build Docker image') {
            steps {
                sh 'mvn -s http://localhost:8082/artifactory/mvn-frog/artifactory-settings.xml -Dmaven.repo.local=`eval echo ~$USER`/.maven-repo verify jib:dockerBuild -DskipTests'
            }
        }
    }
}