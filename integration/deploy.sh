#!/usr/bin/env bash

sh $TOMCAT_PATH/bin/catalina.sh stop
PROJECT_PATH=/home/yasitha/IdeaProjects/spring-hibernate-ogm
cd $PROJECT_PATH
mvn clean install -DskipTests


rm -r $TOMCAT_PATH/webapps/hibernate-ogm*
cp web/target/hibernate-ogm.war $TOMCAT_PATH/webapps

export JPDA_ADDRESS=5005
export JPDA_TRANSPORT=dt_socket
sh $TOMCAT_PATH/bin/catalina.sh jpda run