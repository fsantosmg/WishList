FROM openjdk:17

ENV MONGODB_HOST=mongo
ENV MONGODB_PORT=27017
ENV MONGODB_DATABASE=ecommerce
ENV MONGODB_USERNAME=admin
ENV MONGODB_PASSWORD=admin
ENV MONGODB_AUTHENTICATION_DATABASE=admin
ENV WISHLIST_LIMIT=20

WORKDIR /app

COPY target/*.jar /app/api.jar
COPY wait-for-it.sh /wait-for-it.sh

RUN chmod +x /wait-for-it.sh
EXPOSE 8080

CMD ["java", "-jar", "api.jar"]