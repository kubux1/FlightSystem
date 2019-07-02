# FlightSystem
FlightSystem is an application where user can store information about flights and tourists.

# Build
Before building an app please create a database 'flight_system_db' using mysql server 8.0 version.

Create a user in a database and give him necessary privilages.
mysql> create user 'springuser'@'%' identified by 'ThePassword'; -- Creates the user
mysql> grant all on flight_system_db.* to 'springuser'@'%'; -- Gives all the privileges to the new user on the newly created database

# Run
Run an app with a command 'mvn spring-boot:run'.
