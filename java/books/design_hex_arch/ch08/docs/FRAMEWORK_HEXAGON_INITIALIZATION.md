# Create a sub module for framework hexagon

```shell
cd topology-inventory

mvn archetype:generate \
    -DarchetypeGroupId=de.rieckpil.archetypes  \
    -DarchetypeArtifactId=testing-toolkit \
    -DarchetypeVersion=1.0.0 \
    -DgroupId=hex.arch \
    -DartifactId=framework \
    -Dversion=0.0.1 \
    -Dpackage=dev.davivieira.topologyinventory.framework \
    -DinteractiveMode=false
```

## Create `module` descriptor file

`framework/src/java/module-info.java`

```java

```

