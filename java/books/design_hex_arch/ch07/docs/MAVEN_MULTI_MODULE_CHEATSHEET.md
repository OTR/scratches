# Instructions on how to set up multi module project

## 1. Create a parent project

Create a parent project called topology-inventory by executing the following code:

```shell
mvn -o archetype:generate \
      -DarchetypeGroupId=org.codehaus.mojo.archetypes \
      -DarchetypeArtifactId=pom-root \
      -DarchetypeVersion=RELEASE \
      -DgroupId=hex.arch \
      -DartifactId=topology-inventory \
      -Dversion=0.0.1 \
      -DinteractiveMode=false
```

## 2. Create a module for the Domain hexagon

```shell
cd topology-inventory
      mvn archetype:generate \
        -DarchetypeGroupId=de.rieckpil.archetypes  \
        -DarchetypeArtifactId=testing-toolkit \
        -DarchetypeVersion=1.0.0 \
        -DgroupId=hex.arch \
        -DartifactId=domain \
        -Dversion=1.0-SNAPSHOT \
        -Dpackage=hex.arch.topologyinventory.domain \
        -DinteractiveMode=false 
```

## 3. Create a module descriptor file

Create a module descriptor file at `topology-inventory/domain/src/java/module-info.java`, as follows

```java
module domain {
} 
```

## 4. add the lombok library to the pom.xml project root, as follows:

```xml
<dependencies>
  <dependency>
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok</artifactId>
      <version>1.18.20</version>
      <scope>compile</scope>
  </dependency>
</dependencies>
```

## 5. configure the annotation processing paths for lombok; otherwise, there will be compilation failures. You can do this by running the following code:

```xml
<plugins>
    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.8.1</version>
        <configuration>
            <source>17</source>
            <target>17</target>
            <annotationProcessorPaths>
                <path>
                     <groupId>org.projectlombok</groupId>
                     <artifactId>lombok</artifactId>
                     <version>1.18.26</version>
                </path>
            </annotationProcessorPaths>
        </configuration>
    </plugin>
</plugins>
```

## 6. Update domain's `module-info`

Because we add the lombok dependency, we need to update the domain’s module-info.java file, like this:

```java
module domain {
          requires static lombok;
      } 
```

