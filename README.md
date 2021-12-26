# SonarQube

### run this repository on cloud vscode IDE by clicking:
[![Open in Gitpod](https://gitpod.io/button/open-in-gitpod.svg)](https://gitpod.io/#https://github.com/CastHash532/SonarQube)  
You need to login with your github account  

This will automatiqually setup SonarQube Server accessible at port `9000` and build project  
next you need to generate an AuthenticationToken on SonarQube Platform 
  
to analyze code open a terminal and run:  
``
mvn clean verify sonar:sonar \
  -Dsonar.projectKey=sonar \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.login=**AuthenticationToken**replace here
``