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
                bat 'mvn clean verify com.google.cloud.tools:jib-maven-plugin:2.8.0:dockerBuild -DskipTests'
            }
        }
        
        stage('Push Docker image to Jfrog artifactory') {
            steps {     
                bat 'docker tag vgdockerimg vgjfrog.jfrog.io/vgdocker-docker-local/vgdemo:latest'
                bat 'docker login vgjfrog.jfrog.io -u vincentgwc@hotmail.com -p eyJ2ZXIiOiIyIiwidHlwIjoiSldUIiwiYWxnIjoiUlMyNTYiLCJraWQiOiJENXJTNmJpVnlWTVlOdUVrVmI0WHp0UGlHeHkzRXJodVdHYXM5OE1Zbm1JIn0.eyJzdWIiOiJqZmZlQDAwMFwvdXNlcnNcL3ZpbmNlbnRnd2NAaG90bWFpbC5jb20iLCJzY3AiOiJhcHBsaWVkLXBlcm1pc3Npb25zXC9hZG1pbiBhcGk6KiIsImF1ZCI6WyJqZnJ0QCoiLCJqZm1kQCoiLCJqZmV2dEAqIiwiamZhY0AqIl0sImlzcyI6ImpmZmVAMDAwIiwiaWF0IjoxNjE0MzIzNTk5LCJqdGkiOiJiNGNjYzMzNy1lMzc2LTQyZGQtYTEzNy1mN2UxNzMyYWFkNzUifQ.W_fgeRQTSnIA07PkGY53yGieFDI97o2JGxiQyzwCY7Xad7BZX-yV6tvrHLrykhbEuq5i8b172oT4xmLxP3arvGV04hY2oQR6ot8sreCigPUe-oMma-6K7Vmgh-EIiQEFLXUtBpSAjBhBGuICLp9Yq_ySGM63RNboYPsjcGQ5zQUVtvpL9r0J2o5gki019lTXfKjof3YYbP8mBZMu4iq08DE7auMZhGV-uHROa4fdGpijykIsrNTxxRBDCwC2uLZLs22YLs_cEJcUob57OKXTf7YhxgbZuHTQwxx7p0KyFH4E_9U8xRW_BODrWT25iKZzP_Qd_d6790sLRhEHMd-_4w'
                bat 'docker push vgjfrog.jfrog.io/vgdocker-docker-local/vgdemo:latest'
            }
        }
        
        stage('Connect to Azure Kubernetes'){
        	steps {
        		echo 'Connect to AKS.'
        		bat 'kubectl get pods --all-namespaces=true'
        		bat 'kubectl get deployments --all-namespaces=true'
        		echo "${WORKSPACE}"
        		bat 'kubectl apply -f ${WORKSPACE}\manifests\deployment.yml'
        		bat 'kubectl apply -f ${WORKSPACE}\manifests\service.yml'
        		bat 'kubectl set image deployments/vgcluster-43a6 vgcluster-43a6=vgjfrog.jfrog.io/vgdocker-docker-local/vgdemo:latest'
        	}
        }
    }
}