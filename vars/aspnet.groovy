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
                msbuild SahajPayroll.sln
              """                 
                
                
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
                def msbuildHome = tool 'Default MSBuild'
                def scannerHome = tool 'SonarScanner for MSBuild'
        withSonarQubeEnv() {
      bat "\"${scannerHome}\\SonarScanner.MSBuild.exe\" begin /k:\"SahajVikas_SahajPayroll\""
      bat "\"${msbuildHome}\\MSBuild.exe\" /t:Rebuild"
      bat "\"${scannerHome}\\SonarScanner.MSBuild.exe\" end"
        } 
             
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
