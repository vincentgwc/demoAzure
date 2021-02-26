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
                bat 'mvn -s ${WORKSPACE}/artifactory-settings.xml clean verify jib:dockerBuild -DskipTests'
            }
        }
    }
}