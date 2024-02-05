
```shell
#!/bin/bash

# Store the path to the JAR file
JAR_FILE="slug_generator.jar"

# Store the working directory from where the script was executed
WORKING_DIR=$(pwd)

# Pass all script arguments directly to the Java command, 
# preserving quotes for arguments that might contain spaces
java -jar "$JAR_FILE" "$@"
```

