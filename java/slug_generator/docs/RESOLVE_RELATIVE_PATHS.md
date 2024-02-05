
```shell
JAR_FILE="$HOME/standalone_jar/framework-0.0.1.jar"

#WORKING_DIR=$(pwd)

java -jar "$JAR_FILE" "$@"
```

```shell
JAR_FILE="$HOME/standalone_jar/framework-0.0.1.jar"

#WORKING_DIR=$(pwd)

echo $(pwd)
java -jar "$JAR_FILE" "$WORKING_DIR" "$@"
```
