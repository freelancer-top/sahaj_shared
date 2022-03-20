def call(String param = 'test') {
 pipeline {
    agent any
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
