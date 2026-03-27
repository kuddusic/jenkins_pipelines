String repoUrl = 'https://github.com/kuddusic/jenkins_pipelines.git'
String repoBranch = '*/main'
String scmCredentialsId = ''

def createPipelineJob = { String jobName, String scriptPath, String descriptionText ->
    pipelineJob(jobName) {
        description(descriptionText)

        properties {
            disableConcurrentBuilds()
        }

        definition {
            cpsScm {
                scm {
                    git {
                        remote {
                            url(repoUrl)
                            if (scmCredentialsId?.trim()) {
                                credentials(scmCredentialsId)
                            }
                        }
                        branch(repoBranch)
                    }
                }
                scriptPath(scriptPath)
            }
        }

        triggers {
            scm('H/5 * * * *')
        }
    }
}

createPipelineJob(
    'sample-app-pipeline',
    'pipelines/sample-app.Jenkinsfile',
    'Sample application pipeline job generated from Job DSL seed.'
)

createPipelineJob(
    'test-app-pipeline',
    'pipelines/test-app.Jenkinsfile',
    'Test application pipeline job generated from Job DSL seed.'
)

createPipelineJob(
    'terraform-pipeline',
    'pipelines/terraform.Jenkinsfile',
    'Terraform pipeline job generated from Job DSL seed.'
)
