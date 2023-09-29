# Shipping Application

Java version -> 17
Spring Boot verson -> 2.7.2

The Shipping application is a flexible Spring Boot-based solution that streamlines shipping operations. It leverages various technologies and modules to provide a comprehensive shipping management system.

## Features

- **Modularity**: The application is divided into four modules, with three of them designed for independent use, allowing you to tailor the system to your specific needs.

- **Integration**: The Shipping application integrates with the following technologies:
  - RabbitMQ
  - Redis
  - Flyway for database migrations
  - Prometheus for monitoring
  - Grafana for visualization
  - PostgreSQL database
  - Docker for containerization
  
- **Dynamic Querying**: Querydsl is used to create dynamic queries, providing powerful and flexible querying capabilities.

- **Documentation**: The application's API documentation is generated using OpenAPI and Swagger, making it easy for developers to understand and interact with the API.

- **Data Modeling**: The application includes data model consisting of five tables that interact seamlessly to support the shipping business flow.

- **Data Storage**: JSON data types are used for storing data in the PostgreSQL database, enabling efficient and structured data storage.

- **Scenario Management**: During the analysis phase, scenarios were documented in Confluence, and tasks were tracked and managed using Jira.

## Getting Started

To run the application, follow these steps:

1. Clone this repository to your local machine.
2. Build Docker images for the application and its dependencies using the provided Dockerfiles.
3. Start the application and related servers using Docker.
4. Access the API documentation via Swagger, which is available once the application is running.
5. Use tools like Postman or the integrated Swagger documentation to test and interact with the application's API.
6. Since Flyway migration used, database tables will be automatically created when the application is started.




  



