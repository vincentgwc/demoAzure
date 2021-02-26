pipeline {
    agent any


    stages {
        stage('Start') {
            steps {
                echo 'Starting Pulse SDK Build Pipeline'
            }
        }

        stage('Setup build environment') {
            steps {
                sh 'whoami'
                sh 'mkdir -p `eval echo ~$USER`/.maven-repo'
                sh 'chmod -R 777 `eval echo ~$USER`/.maven-repo'
                sh 'ls -la ~'
                sh 'mvn --version'
                sh 'which kubectl'
            }
        }

        stage('Checkout from Git') {
            steps {
                git branch: 'main', credentialsId: '51827d28-bd7f-4db8-a39d-3d42c4bd15fb', url: 'https://github.com/vincentgwc/demoAzure.git'
            }

        }

        stage('Build Docker image') {
            steps {
                sh 'mvn -s http://localhost:8082/artifactory/mvn-frog/artifactory-settings.xml -Dmaven.repo.local=`eval echo ~$USER`/.maven-repo verify jib:dockerBuild -DskipTests'
            }
        }
    }
}