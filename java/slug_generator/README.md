# Description

This is an educational project to get familiar with Java Servlets

# Run

To compile java sources and run

```bash
mvn clean compile
mvn exec:java -Dexec.mainClass=otr.slug.framework.Main
```

## Run CLI mode with args

```bash
mvn clean compile
mvn exec:java -Dexec.mainClass=otr.slug.framework.Main "SDSDqw12rwfefe&$%#&^*$dsf dsf sdfew"
```

## Build the fat JAR

To build a `fat jar`

```shell
mvn clean package
mv framework/target/framework-0.0.1-jar-with-dependencies.jar framework/target/framework.jar
$USER_INPUT="SDSDqw12rwfefe&$%#&^*$dsf dsf sdfew"
java -jar framework/target/framework.jar $USER_INPUT
```

## Run options

### Basic mode

Use double quotes to

```bash
java -jar framework/target/framework.jar "SDSDqw12rwfefe&$%#&^*$dsf dsf sdfew"
```
