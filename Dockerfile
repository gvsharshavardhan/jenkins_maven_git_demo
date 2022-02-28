FROM openjdk:8u191-jre-alpine3.8
WORKDIR /usr/share/docker
ADD target/selenium-docker.jar selenium-docker.jar
ADD target/selenium-docker-tests.jar selenium-docker-tests.jar
ADD target/libs libs
ADD book-flight-module.xml book-flight-module.xml
ADD testng.xml testng.xml
ENTRYPOINT java -cp selenium-docker.jar:selenium-docker-tests.jar:libs/* -Dbrowser=$browser -Dhub_host=hub_host org.testng.TestNG $module