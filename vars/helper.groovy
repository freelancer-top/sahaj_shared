package vars

def configure (buildParam , jenkinParams)
{
buildParam.jenkinParams = jenkinParams;
if (buildParam.jenkinParams.runTestCases)
        buildParam.runTestCases=buildParam.jenkinParams.runTestCases

 currentBuild.displayName= "${env.BRANCH_NAME} -> ${env.BUILD_NUMBER}"       
}


def buildAspNet(buildParam)
{
        bat """                
                @echo off
                dir
                nuget.exe restore ${buildParam.jenkinParams.solutionFile}
                msbuild /t:Rebuild /t:restore
                msbuild /property:Configuration=Release
              """     
} 
def runQualityGate(buildParam)
{


}

def deloyCode(buildParam)
{
//@echo off
        bat """                
                
                rmdir /s /q "${buildParam.jenkinParams.build.outApp.deployFolder}/*"
                Xcopy ${buildParam.jenkinParams.build.outApp}/bin "${buildParam.jenkinParams.build.outApp.deployFolder}" /E /H /C /I

              """  

   r 
}
return this