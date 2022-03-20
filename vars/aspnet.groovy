def call(String param = 'test') {
 pipeline {
    agent any
     parameters {
        string(defaultValue: 'us-west-2', description: 'Provide your region', name: 'REGION')
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
