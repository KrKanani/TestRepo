FROM openjdk:8-jre-slim
USER root

# Add the jar with all the dependencies
# Maven creates container-test.jar in the target folder of my workspace.
# We need this in some location - say - /usr/share/tag folder of the container
# WORKDIR /usr/share/tag
 WORKDIR /usr/share

# RUN mkdir /usr/share/reports
# RUN mkdir -p ~/test_reports
# RUN mkdir /usr/share/logs

# Add the project jar & copy dependencies
ADD  target/SCAuto-1.0-SNAPSHOT.jar SCAuto-1.0-SNAPSHOT.jar
ADD  target/SCAuto-1.0-SNAPSHOT-tests.jar SCAuto-1.0-SNAPSHOT-tests.jar
ADD  target/libs libs
ADD  target/test-classes/config.properties config.properties
ADD  target/test-classes/log4j.properties log4j.properties
ADD  target/test-classes/chromedriver_win32/chromedriver.exe chromedriver.exe

# Add the suite xmls
ADD suite20.xml suite20.xml
ADD suite35.xml suite35.xml
ADD suite53.xml suite53.xml
ADD suite55.xml suite55.xml

EXPOSE 25

# Command line to execute the test
# Expects below ennvironment variables
# BROWSER = chrome / firefox
# MODULE  = order-module / search-module
# GRIDHOST = selenium hub hostname / ipaddress

ENTRYPOINT java -cp SCAuto-1.0-SNAPSHOT.jar:SCAuto-1.0-SNAPSHOT-tests.jar:libs/* -DseleniumHubHost=$SELENIUM_HUB -Dbrowser=$BROWSER org.testng.TestNG $MODULE