# Description

This is an educational project to get familiar with Java Servlets

# Run

To compile java sources and run

```bash
mvn clean compile
mvn exec:java -Dexec.mainClass=com.github.otr.slug.Main
```

To build a `fat jar`

```shell
mvn clean package
mv ./target/slug_generator-0.0.1-jar-with-dependencies.jar ./target/slug_generator.jar
java -jar ./target/slug_generator.jar
```

## Run options

### Basic mode

Use double quotes to

```bash
java -jar ./target/slug_generator.jar "SDSDqw12rwfefe&$%#&^*$dsf dsf sdfew"
```
