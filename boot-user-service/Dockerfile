FROM java:8
VOLUME /boot-user-service
ADD /target/boot-user-service-0.0.1-SNAPSHOT.jar user-service.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","/user-service.jar"]