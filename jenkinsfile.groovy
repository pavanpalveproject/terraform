def parts = BRANCH_NAME.split('-')
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
      steps{
        script{
        dir("${grade}/${env}/${res}") {
                sh 'pwd'
                sh 'ls'
        }
      }
      }
    }
  }
  }
