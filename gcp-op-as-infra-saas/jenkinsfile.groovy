def parts = BRANCH_NAME.split('-')
def grade = parts[0]
def env = "as-" + parts[1]
def res = parts[2]
// def res =         // manually provide resource
def base_path = "gcp-op-as-infra-saas"


// parameters {

        // booleanParam, choice, file, text, password, run, or string

        // booleanParam(defaultValue: true, description: '', name: 'booleanExample')

        // string(defaultValue: "TEST", description: 'What environment?', name: 'stringExample')

        // text(defaultValue: "vm", description: "give resource name", name: "res")

        // choice(choices: 'US-EAST-1\nUS-WEST-2', description: 'What AWS region?', name: 'choiceExample')

        // password(defaultValue: "Password", description: "Password Parameter", name: "passwordExample")

    // }




pipeline {
  agent any
  environment {
    CLOUDSDK_CORE_PROJECT = 'o-media-2'
  }
   stage('Checkout') {
      steps {
        // Checkout the pull request branch
        script {
          def prBranch = env.CHANGE_BRANCH.replace('refs/heads/', '')
          checkout([$class: 'GitSCM', branches: [[name: "refs/heads/${prBranch}"]], userRemoteConfigs: [[url: "https://github.com/pavanpalveproject/terraform"]]])
        }
      }
    }
  stages{
    stage('terraform-init'){
      steps{
        script{
        // dir("${base_path}/${env}/${res}") {
        //         sh 'pwd'
        //         sh 'ls'
        //         sh 'terraform init'
        //         sh 'terraform plan'
        
         def changedDir = sh(script: "git diff --name-only master..${BRANCH_NAME} | awk -F/ '{print $1}' | uniq", returnStdout: true).trim()
          sh "cd ${changedDir} && terraform init "
        }
      }
      }
    }

    stage{
        steps{
          script{
            git branch:
          }
        }
    }
  }
  }