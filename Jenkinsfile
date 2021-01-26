pipeline {
    agent any

    tools {
        maven "M3"
    }

    stages {
        stage('Build') {
            steps {
                // Get some code from a local git repository
                git 'file:///Users/mukutbhattacharjee/CODEBASE/football'

                // Run Maven on a Unix agent.
                sh "mvn clean install"
            }
        }
        stage('Build Docker image') {
             steps {
                 sh 'docker build -t ${BUILD_TAG} .'
             }
        }
    }
}
