pipeline {
    agent any

    options {
        timestamps()
        disableConcurrentBuilds()
        buildDiscarder(logRotator(numToKeepStr: '20'))
    }

    environment {
        APP_NAME = 'sample-app'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo "Building ${APP_NAME}"
                sh 'echo "Compile step placeholder"'
            }
        }

        stage('Test') {
            steps {
                echo "Testing ${APP_NAME}"
                sh 'echo "Test step placeholder"'
            }
        }

        stage('Package') {
            steps {
                echo "Packaging ${APP_NAME}"
                sh 'echo "Package step placeholder"'
            }
        }
    }

    post {
        always {
            echo "Pipeline finished for ${APP_NAME}"
        }
        success {
            echo 'Build succeeded'
        }
        failure {
            echo 'Build failed'
        }
    }
}
