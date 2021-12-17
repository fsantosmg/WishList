# WishList
Api WishList
Tecnologias
Java 17
Spring Boot 2.6.1
MongoDB

Para rodar o projeto configure as variáveis de ambiente descritas a seguir:
spring.data.mongodb.host=${MONGODB_HOST}
spring.data.mongodb.port=${MONGODB_PORT}
spring.data.mongodb.database=${MONGODB_DATABASE}
spring.data.mongodb.username=${MONGODB_USERNAME}
spring.data.mongodb.password=${MONGODB_PASSWORD}
spring.data.mongodb.authentication-database=${MONGODB_AUTHENTICATION_DATABASE}
spring.data.mongodb.auto-index-creation=true
customer.wishlist.limit=${WISHLIST_LIMIT}


O arquivo docker-compose ajudará a instanciar um container docker contendo o MongoDB e o Mongo Express.
