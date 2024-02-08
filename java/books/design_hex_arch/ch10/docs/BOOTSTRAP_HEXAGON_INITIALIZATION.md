# Create `bootstrap` module

In the Maven root project of the topology and inventory system,
you can execute the following Maven command to create this bootstrap module:

```shell
mvn archetype:generate \
-DarchetypeGroupId=de.rieckpil.archetypes  \
-DarchetypeArtifactId=testing-toolkit \
-DarchetypeVersion=1.0.0 \
-DgroupId=hex.arch \
-DartifactId=bootstrap \
-Dversion=0.0.1 \
-Dpackage=hex.arch.topologyinventory.bootstrap \
-DinteractiveMode=false
```

# 2. Set up project level dependencies

Set up Quarkus dependencies in the project’s root pom.xml file, as follows:

```xml
<dependencyManagement>
  <dependencies>
    <dependency>
      <groupId>io.quarkus</groupId>
      <artifactId>quarkus-universe-bom</artifactId>
      <version>${quarkus.platform.version}</version>
      <type>pom</type>
      <scope>import</scope>
    </dependency>
  </dependencies>
</dependencyManagement>
```

# 3. Configure `jandex-maven-plugin`

Because we’re working with a multi-module application,
we need to configure Quarkus to discover CDI beans in different modules.

We need to configure `jandex-maven-plugin` 
in the Maven project’s root `pom.xml` file, as follows:

```xml
<plugin>
  <groupId>org.jboss.jandex</groupId>
  <artifactId>jandex-maven-plugin</artifactId>
  <version>${jandex.version}</version>
  <executions>
    <execution>
      <id>make-index</id>
      <goals>
        <goal>jandex</goal>
      </goals>
    </execution>
  </executions>
</plugin>
```

# 4.  Configure `quarkus-maven-plugin` on `bootstrap`

To make the bootstrap module the one that will start the Quarkus engine,
 we need to `configure quarkus-maven-plugin` in that module properly.
Here is how we should configure quarkus-maven-plugin on `bootstrap/pom.xml`:

```xml
<build>
  <plugins>
    <plugin>
      <groupId>io.quarkus</groupId>
      <artifactId>quarkus-maven-plugin</artifactId>
      <version>${quarkus-plugin.version}</version>
      <extensions>true</extensions>
      <executions>
        <execution>
          <goals>
            <goal>build</goal>
            <goal>generate-code</goal>
            <goal>generate-code-tests</goal>
          </goals>
        </execution>
      </executions>
    </plugin>
  </plugins>
</build>
```

5. Add dependencies for other hexagons into bootstrap

6. Create a `module-info.java` for bootstrap module

create a `module-info.java` Java module descriptor
with the `requires` directives for Quarkus

```java
module bootstrap {
    requires quarkus.core;
    requires domain;
    requires application;
    requires framework;
}
```

7. Configure Quarkus to generate an uber JAR

To aggregate the three hexagon modules into one deployment unit,
we’ll configure Quarkus to generate an uber `.jar` file.

This kind of JAR groups up all dependencies required
to run an application in one single JAR. To accomplish that,
we need to set the following configuration in the project’s root `pom.xml` file:

```xml
<quarkus.package.type>uber-jar</quarkus.package.type>
```

