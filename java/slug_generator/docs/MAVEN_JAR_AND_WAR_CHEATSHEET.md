# Example of a `pom.xml` file to build both `JAR` and `WAR` packages

```xml
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>otr.slug</groupId>
    <artifactId>slug_generator</artifactId>
    <packaging>war</packaging>
    <version>0.0.1</version>

    <name>slug_generator</name>
    <url>https://github.com/OTR/scratches/java/slug_generator</url>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
    </properties>

    <dependencies>

        <!-- Servlet API -->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>4.0.1</version>
            <scope>provided</scope>
        </dependency>

        <!-- JUnit 4 -->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.13.2</version>
            <scope>test</scope>
        </dependency>

        <!-- Apache Commons CLI -->
        <!-- A convenient way of parsing console arguments -->
        <dependency>
            <groupId>commons-cli</groupId>
            <artifactId>commons-cli</artifactId>
            <version>1.5.0</version>
        </dependency>

    </dependencies>

    <build>
        <pluginManagement>
            <plugins>

                <!-- clean lifecycle -->
                <!-- see https://maven.apache.org/ref/current/maven-core/lifecycles.html#clean_Lifecycle -->
                <plugin>
                    <artifactId>maven-clean-plugin</artifactId>
                    <version>3.1.0</version>
                </plugin>

                <!-- default lifecycle, jar packaging -->
                <!-- see https://maven.apache.org/ref/current/maven-core/default-bindings.html#Plugin_bindings_for_jar_packaging -->
                <plugin>
                    <artifactId>maven-resources-plugin</artifactId>
                    <version>3.0.2</version>
                </plugin>

                <plugin>
                    <artifactId>maven-compiler-plugin</artifactId>
                    <version>3.8.0</version>
                </plugin>

                <plugin>
                    <artifactId>maven-surefire-plugin</artifactId>
                    <version>2.22.1</version>
                </plugin>

                <!-- For creating WAR and JAR files at once -->
                <!--                <plugin>-->
                <!--                    <groupId>org.apache.maven.plugins</groupId>-->
                <!--                    <artifactId>maven-war-plugin</artifactId>-->
                <!--                    <version>3.3.2</version>-->
                <!--                    <configuration>-->
                <!--                        <attachClasses>true</attachClasses>-->
                <!--                    </configuration>-->
                <!--                </plugin>-->

                <!-- For creating JAR files -->
                <plugin>
                    <artifactId>maven-jar-plugin</artifactId>
                    <version>3.0.2</version>

                    <configuration>
                        <archive>
                            <manifest>
                                <mainClass>otr.slug.framework.Main</mainClass>
                            </manifest>
                        </archive>
                        <!--                        <classifier>best-version</classifier>-->
                    </configuration>

                    <!-- To be able to build JAR file as well as WAR file -->
                    <!--                    <executions>-->
                    <!--                        <execution>-->
                    <!--                            <phase>compile</phase>-->
                    <!--                            <goals>-->
                    <!--                                <goal>jar</goal>-->
                    <!--                            </goals>-->
                    <!--                        </execution>-->
                    <!--                    </executions>-->
                </plugin>

                <plugin>
                    <artifactId>maven-install-plugin</artifactId>
                    <version>2.5.2</version>
                </plugin>

                <plugin>
                    <artifactId>maven-deploy-plugin</artifactId>
                    <version>2.8.2</version>
                </plugin>

                <!-- site lifecycle -->
                <!-- see https://maven.apache.org/ref/current/maven-core/lifecycles.html#site_Lifecycle -->
                <plugin>
                    <artifactId>maven-site-plugin</artifactId>
                    <version>3.7.1</version>
                </plugin>

                <plugin>
                    <artifactId>maven-project-info-reports-plugin</artifactId>
                    <version>3.0.0</version>
                </plugin>

                <!-- Shade plugin -->
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-shade-plugin</artifactId>
                    <version>3.3.0</version>

                    <configuration>
                        <artifactSet>
                            <includes>
                                <include>commons-cli:commons-cli</include>
                            </includes>
                        </artifactSet>
                    </configuration>

                    <executions>
                        <execution>
                            <phase>package</phase>
                            <goals>
                                <goal>shade</goal>
                            </goals>
                            <configuration>
                                <transformers>
                                    <transformer
                                            implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                                        <mainClass>otr.slug.framework.Main</mainClass>
                                    </transformer>
                                </transformers>
                            </configuration>
                        </execution>
                    </executions>
                </plugin>

                <!-- dependency plugin -->
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-dependency-plugin</artifactId>
                    <version>3.2.0</version>
                    <executions>
                        <execution>
                            <id>analyze</id>
                            <phase>package</phase>
                            <goals>
                                <goal>analyze</goal>
                            </goals>
                        </execution>
                    </executions>
                </plugin>

                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-assembly-plugin</artifactId>
                    <version>3.3.0</version>

                    <configuration>
                        <descriptorRefs>
                            <descriptorRef>jar-with-dependencies</descriptorRef>
                        </descriptorRefs>
                    </configuration>

                    <executions>
                        <execution>
                            <id>create-fat-jar</id>
                            <phase>package</phase>
                            <goals>
                                <goal>single</goal>
                            </goals>
                            <configuration>
                                <archive>
                                    <manifest>
                                        <mainClass>otr.slug.framework.Main</mainClass>
                                    </manifest>
                                </archive>
                                <descriptorRefs>
                                    <descriptorRef>jar-with-dependencies</descriptorRef>
                                </descriptorRefs>
                            </configuration>
                        </execution>
                    </executions>

                </plugin>

                <!-- Tomcat server -->
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

            </plugins>
        </pluginManagement>
    </build>

</project>
```