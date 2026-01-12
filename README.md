# Computer Database (CDB)

This project is a web application for managing a database of computers. It allows users to view, create, edit, and delete computer entries. Each computer has a name, an introduction date, a discontinued date, and a company.

## Features

* List computers with pagination
* View computer details
* Create, update, and delete computers
* List companies
* Search for computers and companies

## Building

This project uses Maven for dependency management and building. To build the project, run the following command from the `cdb` directory:

```bash
mvn clean install
```

## Database Setup

This project uses a MySQL database. To set it up, follow these steps:

1.  **Install MySQL:** If you don't have it already, install a MySQL server.
2.  **Create the database and user:** Execute the following SQL scripts from the `training.java/config/db` directory in this order:
    *   `1-SCHEMA.sql`: Creates the `computer-database-db` schema and the `company` and `computer` tables.
    *   `2-PRIVILEGES.sql`: Creates the `admincdb` user with the password `qwerty1234` and grants it the necessary privileges.
    *   `3-ENTRIES.sql`: Populates the tables with initial data.

## Configuration

The database connection settings are located in `cdb/persistance/src/main/resources/datasource.properties`. You can modify this file to match your database configuration.

## Running the Application

This application is designed to be run on a servlet container like Apache Tomcat.

1.  **Build the project:** Follow the "Building" instructions to generate the `webapp.war` file. You will find it in the `cdb/webapp/target/` directory.
2.  **Deploy the application:** Copy the `webapp.war` file to the `webapps` directory of your Tomcat installation.
3.  **Start Tomcat:** Start your Tomcat server.
4.  **Access the application:** Open a web browser and navigate to `http://localhost:8080/webapp/`.
