FROM java:8
VOLUME /boot-order-service
ADD /target/boot-order-service-0.0.1-SNAPSHOT.jar order-service.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/order-service.jar"]