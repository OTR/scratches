# How to create a brand new project with Maven from console

## Install Maven

  * Download the Maven binary archive for your operating system: https://maven.apache.org/download.cgi
  * Extract the archive to a suitable directory
  * Add the Maven bin directory to your system's PATH environment variable

## Generate the Project Sceleton

  * Navigate to the directory where you want to create your project.
  * Run the `mvn archetype:generate` command. This will prompt you for various options like archetype, group ID, artifact ID, version, etc.
  * Or run `mvn archetype:generate -DgroupId=com.github.otr -DartifactId=your_app -Dversion=0.0.1`
  * Choose an appropriate archetype or just type `Enter`

## Modify the `pom.xml` file

Change `<maven.comiler.source>1.7</...>` to appropriate Java version, e.g. `17` for Java 17

## Specify the Main class as the entry point of your application within your `pom.xml` file

  * Locate `<plugin>` section with artifact named `maven-jar-plugin` and add `<configuration>` section to it so that it would look like:

```xml
<?xml version="1.0" encoding="UTF-8"?>

<project>

  <!--...-->

  <build>
    <pluginManagement>
      <plugins>
          
        <!--...-->

        <plugin>
          <artifactId>maven-jar-plugin</artifactId>
          <version>3.0.2</version>
          <configuration>
            <archive>
              <manifest>
                <mainClass>com.github.otr.Main</mainClass>
              </manifest>
            </archive>
          </configuration>
        </plugin>

      <!--...-->
          
      </plugins>
    </pluginManagement>
  </build>
</project>
```

## Create the Repository on Github if you don't have one

  Visit web site and create a new repository with name `slug_generator`

## Initialize a Local Git Repository

`git init`

## Add files and commit changes

`git add .` then `git commit -m "[CHORE] Initial commit`

## Add the remote repository URL from GitHub to your local repository:

`git remote add origin https://github.com/OTR/slug_generator.git`

## Push changes

`git push -u origin main`

## Build the project with Maven

  * Run the `mvn clean compile` command to clean the build directory and compile your Java source code (there are actually two commands in one)
    This command will compile the project's code, creating the necessary .class files in the target directory
  * Run the project: To run the project as a standalone application, use the following command: `mvn exec:java`
    This Maven goal directly runs a specified class from your project: `mvn exec:java -Dexec.mainClass="com.github.otr.Main"`
  * Creating a JAR file. Package your code into a JAR file using mvn package: `mvn package`
    Then Execute the JAR using the java -jar command: `java -jar target/slug_generator-0.0.1.jar`
  * Additional goals like `mvn test` will execute unit tests.

## Or build the project without maven

### Add external libraries to CLASSPATH

The error "package org.junit does not exist" typically occurs when the JUnit library is not properly added to the classpath or when there is a version mismatch. Here are some steps to resolve this issue:

Check if JUnit is added to the classpath: Ensure that the JUnit library is added to the classpath of your project when compiling and running your code.

### Compile the Classes

Use the `javac` command to compile your Java source files into class files

To make `javac` recursively go through the `src` directory and compile all the Java source files, you can use the following command:

On Unix/Linux:

```bash
find src -name "*.java" > sources.txt
javac @sources.txt
```

On Windows:

```bash
dir /s /B src\*.java > sources.txt
javac @sources.txt
```

Or:

`javac -d target -sourcepath src $(find src -name "*.java")`

This command uses the find utility to recursively locate all the Java files within the src directory and its subdirectories and then passes the list of files to the javac command for compilation.

### Create a Manifest File (optional)

If you want to specify a main class or other metadata for the JAR, create a manifest file in `target` directory named `MANIFEST.MF` with contents:

```
Main-Class: com.github.otr.Main
```

### Create the JAR File:

Use the jar command to create the JAR file:

```bash
$ jar cvfm ./target/slug_generator.jar ./target/MANIFEST.MF -C target .
added manifest
adding: MANIFEST.MF(in = 31) (out= 33)(deflated -6%)
adding: com/(in = 0) (out= 0)(stored 0%)
adding: com/github/(in = 0) (out= 0)(stored 0%)
adding: com/github/otr/(in = 0) (out= 0)(stored 0%)
adding: com/github/otr/App.class(in = 427) (out= 298)(deflated 30%)
```

### Run JAR file

```bash
$ java -jar ./target/slug_generator.jar
Hello World!
```

## Troubleshooting

### How to change line endings for a specific file:

  * Check Current Line Endings: Inspect the current line endings using the file command: `file pom.xml`
    This will display information about the file, including its line ending format (e.g., "CRLF" for Windows, "LF" for Unix-like systems).
  * Convert Line Endings: Use the `dos2unix` or `unix2dos` command to convert the line endings, depending on your desired format:
    To convert to Unix-style (LF): `dos2unix pom.xml`

### Analogue for `touch` command on Windows

`echo . >> README.md`

### `jar` command not found

Install Java Development Kit (JDK):

If you only have the Java Runtime Environment (JRE), you'll need the full JDK, which includes the jar command.

Download and install the JDK from https://www.oracle.com/java/technologies/downloads/.

### Add Tomcat plugin to maven 

In your project's `pom.xml`, add the Tomcat plugin:

```xml
<plugin>
    <groupId>org.apache.tomcat.maven</groupId>
    <artifactId>tomcat7-maven-plugin</artifactId>
    <version>2.2</version>
    <configuration>
        <url>http://localhost:8080/</url>
        <server>TomcatServer</server>
        <path>/get-slug</path>
    </configuration>
</plugin>
```