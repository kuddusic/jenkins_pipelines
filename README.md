# Jenkins Pipelines Repository

This repository stores Jenkins pipelines and Job DSL seed definitions for a pipeline-as-code setup.

## Structure

- `pipelines/sample-app.Jenkinsfile`: sample declarative pipeline
- `pipelines/test-app.Jenkinsfile`: second sample pipeline for test jobs
- `jobs/seed.groovy`: sample Job DSL seed that creates pipeline jobs

## Jenkins Setup

1. Install the `Job DSL` plugin.
2. Install the `Pipeline` plugins.
3. Create a freestyle or pipeline seed job in Jenkins.
4. Configure the seed job to pull this repository.
5. Add a `Process Job DSLs` build step and point it to `jobs/*.groovy`.

## Sample Seed Outcome

Running the seed script creates two pipeline jobs:

- `sample-app-pipeline`
- `test-app-pipeline`

The generated jobs read their pipeline definitions from the `pipelines/` folder in this repository.

Update the repository URL, branch, credentials ID, and job names in `jobs/seed.groovy` to match your Jenkins environment.
