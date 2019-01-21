# Pull base image
FROM openjdk:8-jre-alpine

MAINTAINER aelkouhen@norauto.com

RUN mkdir -p /opt/app

# Copy sources
COPY build/libs/lyne.jar /opt/app/

# Define working directory
WORKDIR /opt/app/

EXPOSE 8083

VOLUME /var/lib/eb-poc-repo

ENTRYPOINT ["java", "-Xmx2048m", "-jar", "/opt/app/lyne.jar"]
