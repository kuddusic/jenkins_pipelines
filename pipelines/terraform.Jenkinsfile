pipeline {
    agent any

    options {
        timestamps()
        disableConcurrentBuilds()
        buildDiscarder(logRotator(numToKeepStr: '20'))
    }

    parameters {
        string(name: 'TF_REPO_URL', defaultValue: 'https://github.com/kuddusic/tf-vsphere.git', description: 'Git repository URL containing Terraform code')
        string(name: 'TF_REPO_BRANCH', defaultValue: 'main', description: 'Git branch to build')
        string(name: 'TF_ROOT_DIR', defaultValue: '.', description: 'Directory containing Terraform code inside the repository')
        choice(name: 'TF_ACTION', choices: ['plan', 'apply', 'destroy'], description: 'Terraform action to execute')
        booleanParam(name: 'AUTO_APPROVE', defaultValue: false, description: 'Pass -auto-approve for apply and destroy')
    }

    environment {
        TF_IN_AUTOMATION = 'true'
        TF_INPUT = 'false'
    }

    stages {
        stage('Checkout Terraform Repo') {
            steps {
                git branch: params.TF_REPO_BRANCH, credentialsId: 'github-credentials', url: params.TF_REPO_URL
            }
        }

        stage('Terraform Version') {
            steps {
                sh 'terraform version'
            }
        }

        stage('Terraform Init') {
            steps {
                dir("${params.TF_ROOT_DIR}") {
                    sh 'terraform init'
                }
            }
        }

        stage('Terraform Format Check') {
            when {
                expression { params.TF_ACTION == 'plan' }
            }
            steps {
                dir("${params.TF_ROOT_DIR}") {
                    sh 'terraform fmt -check -recursive'
                }
            }
        }

        stage('Terraform Validate') {
            steps {
                dir("${params.TF_ROOT_DIR}") {
                    sh 'terraform validate'
                }
            }
        }

        stage('Terraform Plan') {
            when {
                expression { params.TF_ACTION == 'plan' || params.TF_ACTION == 'apply' }
            }
            steps {
                dir("${params.TF_ROOT_DIR}") {
                    sh 'terraform plan -out=tfplan'
                }
            }
        }

        stage('Terraform Apply') {
            when {
                expression { params.TF_ACTION == 'apply' }
            }
            steps {
                dir("${params.TF_ROOT_DIR}") {
                    sh '''
                        if [ "${AUTO_APPROVE}" = "true" ]; then
                          terraform apply -auto-approve tfplan
                        else
                          terraform apply tfplan
                        fi
                    '''
                }
            }
        }

        stage('Terraform Destroy') {
            when {
                expression { params.TF_ACTION == 'destroy' }
            }
            steps {
                dir("${params.TF_ROOT_DIR}") {
                    sh '''
                        if [ "${AUTO_APPROVE}" = "true" ]; then
                          terraform destroy -auto-approve
                        else
                          terraform destroy
                        fi
                    '''
                }
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: "${params.TF_ROOT_DIR}/tfplan", allowEmptyArchive: true
        }
    }
}
