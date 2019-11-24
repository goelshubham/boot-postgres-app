# boot-postgres-app
POC app to do CRUD operations in Postgres

This POC app demonstrate how to create a spring boot application which can connect with Postgres database. 

Set up these properties

spring.datasource.platform=postgres
spring.datasource.url=jdbc:postgresql://localhost:5433/powerprotectdataprocessordb
spring.datasource.username=postgres
spring.datasource.password=postgres

And when we use @Repository in a class/interface, spring provides us the ability to interact with postgres db. We also need to include pring-boot-starter-data-jpa dependency to inform spring that we want to use spring data and do the magic for us. We also need to include postgres jdbc driver.

		<dependency>
			<groupId>org.postgresql</groupId>
			<artifactId>postgresql</artifactId>
		</dependency>
    

This app also teaches how to implement Transaction Management using Spring framework and how to implement row level locking mechanism. 

We can execute this app on two different ports and hit /api/save endpoint for same id. While first transaction acquires a WRITE lock on a row of table and does some processing (I have enabled thread sleep for 20 secs), if another transaction comes and try to get WRITE lock on same row of table, it has to wait until first transaction releases the lock. This wait is enabled by .setHint("javax.persistence.lock.timeout", 15000)

We are making our transaction serialized. 



