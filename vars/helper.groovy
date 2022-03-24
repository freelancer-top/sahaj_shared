package vars

def configure (buildParam , jenkinParams)
{
buildPardiram.jenkinParams = jenkinParams;

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