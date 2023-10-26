# CronService

### Requirements

This project requires Java 17 (JDK 17).

## Production usage

### Building

Use embedded Maven Wrapper to build the project as JAR:

```
./mvnw clean install
```

### Running

Run the JAR with parameter:

```
java -jar ./target/CronService-1.0-SNAPSHOT-jar-with-dependencies.jar '*/15 0 1,15 * 1-5 /usr/bin/find'
```

## Development

### Running

Run Maven goal to run the application without building the JAR:

```
./mvnw compile exec:java -D"exec.args"="*/15 0 1,15 * 1-5 /usr/bin/find"
```

### Testing

To run all tests:

```
./mvnw clean test
```

To run a single test:

```
./mvnw test -D"test"="com.bazusoft.cron.parser.CronEntryParserUnitTest#shouldParseCronExpressions"
```

## Further development

- add input validation
- improve user experience
- support more CRON use cases
- add logging
- add more tests to build testing pyramid