def parts = BRANCH_NAME.split('-')
def grade = parts[0]
def env = parts[1]
def res = parts[2]
def base_path = "gcp-op-as-infra-saas"

pipeline {
  agent any
  environment {
    CLOUDSDK_CORE_PROJECT = 'o-media-2'
  }
  stages{
    stage('test-terraform-init'){
      steps{
        script{
        dir("${base_path}/${grade}/${env}/${res}") {
                sh 'pwd'
                sh 'ls'
                sh 'terraform init'
        }
      }
      }
    }
  }
  }