def call(Map jenkinParams = [:]) {

    def buildParam = [stage: 'tbd'];
 pipeline {
    agent any
     parameters {
        string(defaultValue: 'XXXX', description: 'Server URL to deploy', name: 'SH_SERVER_NAME')
        choice(name: 'SH_ENV', choices: ['dev', 'uat', 'prod'], description: 'Choose the enviornment') 
    }
    stages {
          stage('Configure') {
            steps {
                echo 'Configure..'                                
                helper.configure(buildParam,jenkinParams)
                
            }
        }
        stage('Build') {
            steps {
                echo 'Building..'                
                print("${jenkinParams}")
                helper.buildAspNet(buildParam)
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'             
             
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
  
}
