# Drones API

[![Codacy Badge](https://app.codacy.com/project/badge/Grade/b0df6f805e4d49d5a5409cb02b1a711f)](https://www.codacy.com/gh/DonTee-Why/drones/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=DonTee-Why/drones&amp;utm_campaign=Badge_Grade)

This project is an API written in Java to simulate drone functions. The drone can be loaded with items to be transported. For this project, the items being delivered are medications.

## Features

- Registering a drone;
- Load a drone with medication items
- Check loaded medication items for a given drone
- Check available drones for loading
- Check drone battery level for a given drone
- Generate history/audit event log of drones battery levels periodically (Every 30 seconds by default).

## Installation

### Requirements

- [JDK 1.8](https://www.oracle.com/java/technologies/downloads) or later
- [Maven 3.2+](https://maven.apache.org/download.cgi)
- You can also import the project into you IDE

1. Clone the project from the repository and then cd into the root directory

```sh
git clone https://gitlab.com/DonTee-Why/drones
cd drone
```

2. Build the project

```sh
mvn install
```

3. Run the project

```sh
mvn spring-boot:run
```

4. Make API calls 

```sh
GET http://127.0.0.1:8080/drones
```

## Database

The project uses H2 database, an in-memory database. The database settings has been configured in the `src/main/resources/application.properties` file. Also needed data is preloaded into the database via the `src/main/resources/data.sql` file.

## Testing

The project includes tests written with JUnit 5.

- To run all the tests:

```sh
mvn test
```

- To run individual test classes:

```sh
mvn test -Dtest="TestClassName"
```

## License

MIT License
