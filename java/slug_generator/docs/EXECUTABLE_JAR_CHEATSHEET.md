# Create a directory

```bash
mkdir $HOME/standalone_jar/
```

# Recompile the FAT JAR after changes

```bash
cd ../
mvn clean package -e
```

# Copy FAT JAR

```bash
cp ../framework/target/framework-0.0.1-jar-with-dependencies.jar ~/standalone_jar/framework-0.0.1.jar
```

# Or Compile & Copy & Run at once

```bash
cd ../
mvn clean package -e
cp framework/target/framework-0.0.1-jar-with-dependencies.jar ~/standalone_jar/framework-0.0.1.jar
cd $HOME/standalone_jar/
slug_generator.sh 'Hello beautiful world325234521452340on12)(@!!3124 14132@#)(!3mArch310    --- 2024.pdf'
```

# Only Run

**Be aware double quotes causes error: `bash: !3: event not found`**

```shell
slug_generator.sh "Hello beautiful world325234521452340on12)(@!!3124 14132@#)(!3mArch310    --- 2024.pdf"
```

**So prefer single quotes**

```shell
slug_generator.sh 'Hello beautiful world325234521452340on12)(@!!3124 14132@#)(3mArch310    --- 2024.pdf'
```

# Create a Shell script with the following contents

```bash
# Create the new .sh file with the specified contents
cat << EOF > $HOME/standalone_jar/slug_generator.sh
USER_INPUT="Hello beautiful world325234521452340on12)(@!!-3124 14132@#)(!3mArch310    --- 2024.pdf"
WORK_DIR="\$HOME/standalone_jar/"

java -jar "\$WORK_DIR"framework-0.0.1.jar "\$USER_INPUT"
EOF
```
# Make the Shell script executable

```bash
WORK_DIR="$HOME/standalone_jar/"

chmod +x ${WORK_DIR}slug_generator.sh 
```

# Add directory with FAT JAR's to PATH variable

Run: 

```bash
nano $HOME/.bashrc
```

Add append the line:

```bash
export PATH=$PATH:$HOME/standalone_jar
```

# Try out

Now you can run the script from any directory with:

```shell
slug_generator.sh
```
