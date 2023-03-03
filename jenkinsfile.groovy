pipeline {
  agent any

  environment {
    CLOUDSDK_CORE_PROJECT = 'o-media-2'
  }

  stages {
    stage('gcp_authentication') {
      steps {
        sh 'gcloud auth activate-service-account --key-file $GOOGLE_APPLICATION_CREDENTIALS'
      }
    }

    // stage('Checkout') {
    //     // when {
    //     //     expression { env.GIT_BRANCH.contains("dev") }
    //     // }
    //     steps {
    //         git branch: 'dev*', url: 'https://github.com/pavanpalveproject/terraform'
    //     }
    // }

    stage('Terraform Init') {
      steps {
        script {
          if (env.BRANCH_NAME.contains('dev')) {
            dir('env/dev') {
              sh "pwd"
              sh 'terraform init'
            }
          }
          if (env.BRANCH_NAME.contains("qa")) {
            dir('env/qa') {
              sh "pwd"
              sh 'terraform init'
            }
          }
          if (env.BRANCH_NAME.contains("prod")) {
            dir('env/prod') {
              sh "pwd"
              sh 'terraform init'
            }
          }
        }

      }
    }

    stage('Terraform Plan') {
      steps {
        script {
          if (env.BRANCH_NAME.contains('dev')) {
            dir('env/dev') {
              sh "pwd"
              sh 'terraform plan'
              // sh 'terraform apply'
            }
          }
          if (env.BRANCH_NAME.contains("qa")) {
            dir('env/qa') {
              sh "pwd"
              sh 'terraform plan'
            }
          }
          if (env.BRANCH_NAME.contains("prod")) {
            dir('env/prod') {
              sh "pwd"
              sh 'terraform plan'
            }
          }
        }

      }
    }
    stage('Approval'){
      steps{
        input message : 'are you sure to merge', ok: 'Approve'
      }
    }
    // stage('Terraform Plan') {
    //     steps {

    //         dir('env/${env_}') {
    //         sh "pwd"
    //         sh 'terraform plan'
    //         sh 'terraform plan -var app_name=${TF_VAR_app_name} -var env=${TF_VAR_env} -out=tfplan'
    //         }

    //     }
    // }
  }
}