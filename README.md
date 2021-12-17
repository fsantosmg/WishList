# WishList
##Api WishList<br>
-Tecnologias<br>
-Java 17<br>
-Spring Boot 2.6.17<br>
-MongoDB<br>

Para rodar o projeto configure as variáveis de ambiente descritas a seguir:<br>
spring.data.mongodb.host=${MONGODB_HOST}<br>
spring.data.mongodb.port=${MONGODB_PORT}<br>
spring.data.mongodb.database=${MONGODB_DATABASE}<br>
spring.data.mongodb.username=${MONGODB_USERNAME}<br>
spring.data.mongodb.password=${MONGODB_PASSWORD}<br>
spring.data.mongodb.authentication-database=${MONGODB_AUTHENTICATION_DATABASE}<br>
spring.data.mongodb.auto-index-creation=true<br>
customer.wishlist.limit=${WISHLIST_LIMIT}<br>


O arquivo docker-compose ajudará a instanciar um container docker contendo o MongoDB e o Mongo Express.
