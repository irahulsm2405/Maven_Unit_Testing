This is a User REST api project with Integration Testing

Technologies:

- java 17
- spring boot
- JUnit 5
- embedded tomcat
- h2 database with a data file
- SQL database for some test cases (commented out)

This project has all the exception handling complete along with all the controller methods and service methods. It uses JPA repository along Hibernate. It also contains custom queries which include HQL as well SQL.

Import project and post the sample users persent in test_users.json file and run further. the H2 data file is present in the data folder and database configuraions are present in application.properties file. Once application is up, use following link to login tothe database to see data and run queries: http://localhost:9090/h2-console 
