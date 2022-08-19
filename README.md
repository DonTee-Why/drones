# Drones

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/41ea406d79eb4eacb5ac3a9be0787321)](https://app.codacy.com/gh/DonTee-Why/drones?utm_source=github.com&utm_medium=referral&utm_content=DonTee-Why/drones&utm_campaign=Badge_Grade_Settings)


Just as the mobile phone allowed developing countries to leapfrog older technologies for personal communication, drones have the potential to leapfrog traditional transportation infrastructure.

Useful drone functions include delivery of small items that are (urgently) needed in locations with difficult access. This project simulates these functions. For this project, the items being delivered are medications.

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
- You can also inport the project into you IDE

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
