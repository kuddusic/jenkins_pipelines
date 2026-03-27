pipeline {
    agent any

    options {
        timestamps()
        disableConcurrentBuilds()
        buildDiscarder(logRotator(numToKeepStr: '10'))
    }

    environment {
        APP_NAME = 'test-app'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Lint') {
            steps {
                echo "Linting ${APP_NAME}"
                sh 'echo "Lint step placeholder"'
            }
        }

        stage('Integration Test') {
            steps {
                echo "Running integration tests for ${APP_NAME}"
                sh 'echo "Integration test placeholder"'
            }
        }
    }

    post {
        always {
            echo "Pipeline finished for ${APP_NAME}"
        }
        unstable {
            echo 'Build unstable'
        }
        success {
            echo 'Build succeeded'
        }
        failure {
            echo 'Build failed'
        }
    }
}
