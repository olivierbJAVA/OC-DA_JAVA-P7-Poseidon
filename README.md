# Poseidon
Welcome to Poseidon !

Poseidon is a web application which goal is to store and manage information related to fixed income financial products.
Information related to these products are stored in financial entities : *Rating, BidList, CurvePoint, RuleName and Trade*.
The application allows to manage the users of the application as well (*User* entity).

On the Backend side, it is a Java SpringBoot web application based on the MVC pattern and a REST API.
Data are recorded in a MySQL database.
Maven is used to run the tests, build and package the application.

On the FrontEnd side, HTML / CSS and BootStrap are used as well as Thymeleaf template engine to render views to users.

## Getting Started

The following instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

You need to install the following software :

- Java 1.8
- Maven 3.6.2
- SpringBoot 2.3.0
- MySQL Server 8.0

### Installing

You will find below a step by step explanation that tell you how to get a development environment running :

1.Install Java:
<https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html>

2.Install Maven:
<https://maven.apache.org/install.html>

3.Install SpringBoot :
<https://docs.spring.io/spring-boot/docs/current/reference/html/getting-started.html#getting-started-installing-spring-boot>

4.Install MySQL Server :
<https://dev.mysql.com/doc/mysql-installation-excerpt/5.7/en/>

### Profiles and Configuration

Three Spring profiles are available for each following phase :
- PROD profile used for *Production* phase
- DEV profile used for *Development* phase
- TEST profile used for *Test* phase

There is a global Spring configuration properties file : *application.properties*, and a dedicated configuration properties file for each profile : *application-profileName.properties*.
These files are stored in the *src/main/resources* directory for PROD and DEV profiles and in the *src/test/resources* directory for the TEST profile.


### DataBase creation and initialization

The username and password for connection to the database are stored in the configuration *application-profileName.properties* files.
You must fill these properties files with your own username and password.

A dedicated database is created for each profile (with name : *PoseidonProfileName*) using the files : *schema-profileName.sql*.
Then databases are initialized with some data using the files : *data-profileName.sql*.
These SQL scripts are automatically run by SpringBoot.

>During installing, application running or tests launching you may have an issue (depending on your configuration) related to Time zone configuration.
It is an issue due the configuration of MySQL server.

>To solve this issue, you can add the following line in the MySQL server configuration file (*my.ini* or *my.cfg*) that is in your MySQL directory :
*default-time-zone='+02:00'*.

>This line must be added in the [mysqld] section of the configuration file and may be adapted to your own local timezone obviously.

### Application running

Then you can import and run the application from your favorite IDE.

Please note that the application is secured and you must authenticate before having access to the application.

### Security and connection to the application 

A Security layer is included within the application.   

1.Authentication : Every user needs to authenticate to the application with username and password.

2.Authorization : Two roles are available within the application : ADMIN and USER.
- Users with ADMIN role are authorized to access and manage financial entities AND users
- Users with USER role are authorized to access and manage financial entities but are NOT authorized to access and manage users

3.Password : Users passwords are hashed before being stored in the database.

When the application starts, 2 users are recorded and have access to the application :
- A user having a ADMIN role : username = admin / password = %Password1Admin
- A user having a USER role : username = user / password = %Password1User

You can use these credentials to connect to the application.

### Endpoints
Endpoints are available for managing financial entities with CRUD methods.
Endpoints are available for each entity : 
- Rating, BidList, CurvePoint, RuleName and Trade.

You will find below endpoints for *Rating* management :

1.Read :
- GET  <http://localhost:8080/rating/list> to list all Ratings

2.Create :
- GET  <http://localhost:8080/rating/add> to get the form to fill to add a new Rating
- POST <http://localhost:8080/rating/validate> to post the form filled to add a new Rating

3.Update :
- GET  <http://localhost:8080/rating/update/{id}> to get the form to fill to update the Rating which id is in parameter
- POST <http://localhost:8080/rating/update/{id}> to post the form filled to update the Rating which id is in parameter

4.Delete :
- GET  <http://localhost:8080/rating/delete/{id}> to delete the Rating which id is in parameter
        
Same endpoints are available for other financial entities : BidList, CurvePoint, RuleName and Trade.

There are endpoints for *Users* management as well :

1.Read :
- GET  <http://localhost:8080/user/list> to list all Users

2.Create :
- GET  <http://localhost:8080/user/add> to get the form to fill to add a new User
- POST <http://localhost:8080/user/validate> to post the form filled to add a new User

3.Update :
- GET  <http://localhost:8080/user/update/{id}> to get the form to fill to update the User which id is in parameter
- POST <http://localhost:8080/user/update/{id}> to post the form filled to update the User which id is in parameter

4.Delete :
- GET  <http://localhost:8080/user/delete/{id}> to delete the User which id is in parameter

### Standalone executable JAR file

You can produce a standalone executable JAR file of the application, by running the below command :

`mvn clean install`
 
### Logging

The tool Logback is used for logging. Logs are sent to the console and to a file.
You can configure the logging to your own needs by using the configuration file : *logback.xml*.

Each request and result for endpoints are logged.

### Tests

Tests are included for each layer of the application. You can run them using JUnit runner (`Run as JUnit test`) or using Maven (`Run as Maven test`).
