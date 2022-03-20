def call(String param = 'test') {
 pipeline {
    agent any
     parameters {
        string(defaultValue: 'XXXX', description: 'Server URL to deploy', name: 'SH_SERVER_NAME')
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
