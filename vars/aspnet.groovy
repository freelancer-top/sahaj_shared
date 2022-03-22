def call(String param = 'test') {
 pipeline {
    agent any
     parameters {
        string(defaultValue: 'XXXX', description: 'Server URL to deploy', name: 'SH_SERVER_NAME')
        choice(name: 'SH_ENV', choices: ['dev', 'uat', 'prod'], description: 'Choose the enviornment') 
    }
    stages {
        stage('Build') {
            steps {
                echo 'Building..'                

            bat """                
                dir
                nuget.exe restore SahajPayroll.sln
                msbuild /t:Rebuild /t:restore                 
              """                 
                
                
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
