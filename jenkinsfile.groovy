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
            def envDir = ''
            if (env.BRANCH_NAME.contains('dev')) {
                envDir = 'dev'
            } else if (env.BRANCH_NAME.contains('qa')) {
                envDir = 'qa'
            } else if (env.BRANCH_NAME.contains('prod')) {
                envDir = 'prod'
            }
            if (envDir) {
                dir("env/${envDir}") {
                sh 'terraform init'
                }
            }
            }
        }
    }

    stage('Terraform fmt') {
        steps {
            script {
            def envDir = ''
            if (env.BRANCH_NAME.contains('dev')) {
                envDir = 'dev'
            } else if (env.BRANCH_NAME.contains('qa')) {
                envDir = 'qa'
            } else if (env.BRANCH_NAME.contains('prod')) {
                envDir = 'prod'
            }
            if (envDir) {
                dir("env/${envDir}") {
                sh 'terraform fmt'
                }
            }
            }
        }
    }

    stage('Terraform validate') {
        steps {
            script {
            def envDir = ''
            if (env.BRANCH_NAME.contains('dev')) {
                envDir = 'dev'
            } else if (env.BRANCH_NAME.contains('qa')) {
                envDir = 'qa'
            } else if (env.BRANCH_NAME.contains('prod')) {
                envDir = 'prod'
            }
            if (envDir) {
                dir("env/${envDir}") {
                sh 'terraform validate'
                }
            }
            }
        }
    }

    stage('Terraform Plan') {
      steps {
        script {
      def envDir = ''
      if (env.BRANCH_NAME.contains('dev')) {
        envDir = 'dev'
      } else if (env.BRANCH_NAME.contains('qa')) {
        envDir = 'qa'
      } else if (env.BRANCH_NAME.contains('prod')) {
        envDir = 'prod'
      }
      if (envDir) {
        dir("env/${envDir}") {
          sh 'terraform plan'
        }
      }
    }

      }
    }
    stage('Approval'){
      steps{
        input message : 'are you sure to apply changes', ok: 'Approve'
      }
    }


    stage('Approval_1') {
      steps {
        script {
          // Replace this with your own approval mechanism
          def approvedUser = 'pavan'
          if (approvedUser in env.CHANGE_AUTHOR_DISPLAY_NAME) {
            input "Do you approve the changes for ${env.BRANCH_NAME}?"
          } else {
            error "${env.CHANGE_AUTHOR_DISPLAY_NAME} is not authorized to approve changes for ${env.BRANCH_NAME}"
          }
        }
      }
    }

    stage('Terraform Apply') {
        steps {
            script {
            def envDir = ''
            if (env.BRANCH_NAME.contains('dev')) {
                envDir = 'dev'
            } else if (env.BRANCH_NAME.contains('qa')) {
                envDir = 'qa'
            } else if (env.BRANCH_NAME.contains('prod')) {
                envDir = 'prod'
            }
            if (envDir) {
                dir("env/${envDir}") {
                sh 'terraform apply -auto-approve terraform.plan'
                }
            }
            }
        }
    }

    stage('Merge to Master') {
        steps {
            script {
            def envDir = ''
            if (env.BRANCH_NAME.contains('dev')) {
                envDir = 'dev'
            } else if (env.BRANCH_NAME.contains('qa')) {
                envDir = 'qa'
            } else if (env.BRANCH_NAME.contains('prod')) {
                envDir = 'prod'
            }
            if (envDir) {
                dir("env/${envDir}") {
                sh 'git checkout master && git merge origin/${BRANCH_NAME} && git push'
                }
            }
            }
        }
    }
  }
}