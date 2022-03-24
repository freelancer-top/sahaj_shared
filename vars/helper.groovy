package vars

def configure (buildParam , jenkinParams)
{
buildParam.jenkinParams = jenkinParams;

}

def buildAspNet(buildParam)
{
        bat """                
                @echo off
                dir
                nuget.exe restore ${buildParam.jenkinParams.solutionFile}
                msbuild /t:Rebuild /t:restore                 
              """     
} 

return this