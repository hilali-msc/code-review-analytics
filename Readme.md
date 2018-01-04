The project uses a library : 

https://github.com/uwolfer/gerrit-rest-java-client to use Gerrit Rest API

It suppose that you are using oauth authentication to Github (see https://developer.github.com/v3/oauth_authorizations/)

For the R processing, we have used JRI :

http://www.rforge.net/JRI/index.html (Use of https://cran.r-project.org/web/packages/arules/arules.pdf)

The tool is implemented as a micro-service, and has as customer, a chrome extension : <Br>

https://github.com/NostraSoft/github-analyse-this

Download :
https://mvnrepository.com/artifact/com.urswolfer.gerrit.client.rest/gerrit-rest-java-client/0.8.13

Then :
mvn install:install-file -Dfile=gerrit-rest-java-client-0.8.13.jar -DgroupId=com.urswolfer.gerrit.client.rest -DartifactId=gerrit-rest-java-client -Dversion=0.8.13 -packaging=jar
