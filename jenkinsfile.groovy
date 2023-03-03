pipeline {
    agent any

    environment{
        CLOUDSDK_CORE_PROJECT = 'o-media-2'
    }

    if(env.BRANCH_NAME.contains('dev')){
       def  env_ = dev
    }
    if(env.BRANCH_NAME.contains("qa")){
       def  env_ = qa
    }
    if(env.BRANCH_NAME.contains("prod")){
       def env_ = prod
    }
    

    stages{
        stage('gcp_authentication'){
            steps{
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
              dir('env/${env_}') {
                sh "pwd"
                sh 'terraform init'
                }
              
            }
        }
        stage('Terraform Plan') {
            steps {
                
                dir('env/${env_}') {
                sh "pwd"
                sh 'terraform plan'
                sh 'terraform plan -var app_name=${TF_VAR_app_name} -var env=${TF_VAR_env} -out=tfplan'
                }
                
            }
        }
    }
}