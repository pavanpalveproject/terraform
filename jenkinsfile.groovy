def parts = BRANCH_NAME.readCSV('-')
def grade = parts[0]
def env = parts[1]
def res = parts[2]

pipeline {
  agent any

  environment {
    CLOUDSDK_CORE_PROJECT = 'o-media-2'
  }
  stages{
    stage('test'){
      script{
        dir("${grade}/${env}/${res}") {
                sh 'terraform init'
        }
      }
    }
  }
  }
