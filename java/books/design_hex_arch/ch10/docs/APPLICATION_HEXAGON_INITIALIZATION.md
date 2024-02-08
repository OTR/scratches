# Create a sub module for application hexagon

```shell
cd topology-inventory

mvn archetype:generate \
  -DarchetypeGroupId=de.rieckpil.archetypes  \
  -DarchetypeArtifactId=testing-toolkit \
  -DarchetypeVersion=1.0.0 \
  -DgroupId=hex.arch \
  -DartifactId=application \
  -Dversion=0.0.1 \
  -Dpackage=hex.arch.topologyinventory.application \
  -DinteractiveMode=false
```

## Create `module` descriptor file

`application/src/java/module-info.java`

```java
module application {
    requires domain;
    requires static lombok;
}
```

