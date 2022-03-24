def call(Map jenkinParams = [:]) {

    def buildParam = [runQualityCheck: true, runTestCases: false];
 pipeline {
    agent any
     parameters {
        string(defaultValue: 'XXXX', description: 'Server URL to deploy', name: 'SH_SERVER_NAME')
        choice(name: 'SH_ENV', choices: ['dev', 'uat', 'prod'], description: 'Choose the enviornment') 
    }
    stages {
          stage('Configure') {
            steps {
                script 
                {
                echo 'Configure..'                                
                helper.configure(buildParam,jenkinParams)
                }
                
            }
        }
        stage('Build AspNet') {
            steps {
                script
                {
                echo 'Building..'                
                print("${jenkinParams}")
                helper.buildAspNet(buildParam)
                }
            }
        }

        stage('Quaity Check ') {
            when {
                expression {
                    return buildParam.runQualityCheck
                }
                beforeAgent true
            } 
            steps {
                echo 'quality check..'             
             
            }
        }
        stage('Test') {
            when {
                expression {
                    return buildParam.runTestCases
                }
            } 
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
