package vars

def configure (buildParam , jenkinParams)
{
buildParam.jenkinParams = jenkinParams;
if (buildParam.jenkinParams.runTestCases)
        buildParam.runTestCases=buildParam.jenkinParams.runTestCases



if (buildParam.jenkinParams.build.type)
{
buildParam.build.type =buildParam.jenkinParams.build.type;
}

if ( buildParam.build.type == "asp")
{
    buildParam.build.buildOutFolder ="${jenkinParams.build.outApp}\\bin"
}


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

def buildCore(buildParam)
{
    //@echo off
    //dir
    //dotnet publish ${buildParam.jenkinParams.solutionFile} --no-restore -c Release -o ${buildParam.build.buildOutFolder}
        bat """               
                
                nuget.exe restore ${buildParam.jenkinParams.solutionFile}
                dotnet build ${buildParam.jenkinParams.solutionFile} --no-restore -c Release -o ${buildParam.build.buildOutFolder}
                
              """     
}
def runQualityGate(buildParam)
{

/*       bat """ 
       @echo off 
    SonarScanner.MSBuild.exe begin /d:sonar.host.url="http://localhost:9000" /d:sonar.login="ef87ded16e4176063843cea7f6797fb480cf3183"
    MsBuild.exe /t:Rebuild
    SonarScanner.MSBuild.exe end /d:sonar.login="ef87ded16e4176063843cea7f6797fb480cf3183"
  """     

  */
}

def runCoreTestCases(buildParam)
{

       bat """ 
       @echo off 
    dotnet test ${buildParam.jenkinParams.solutionFile}
  """     


}
dotnet test eShopOnWeb.sln

def deloyCode(buildParam)
{

print ( "${buildParam}");
        bat """                
                @echo off
                del /s /q "${buildParam.jenkinParams.build.deployFolder}\\*"
                Xcopy ${buildParam.build.buildOutFolder} "${buildParam.jenkinParams.build.deployFolder}" /E /H /C /I

              """  


}
return this