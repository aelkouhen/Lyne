# Pull base image
FROM openjdk:8-jre-alpine

MAINTAINER amine.elkouhen@gmail.com

RUN mkdir -p /opt/app

# Copy sources
COPY build/libs/Lyne.jar /opt/app/

# Define working directory
WORKDIR /opt/app/

EXPOSE 8083

VOLUME /var/lib/carhub

ENTRYPOINT ["java", "-jar", "/opt/app/Lyne.jar"]
