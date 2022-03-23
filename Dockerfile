FROM openjdk:latest
COPY target/cocktails-0.0.1.war /usr/src/cocktails/
COPY ./wait-for-it.sh /usr/src/cocktails/
WORKDIR /usr/src/cocktails