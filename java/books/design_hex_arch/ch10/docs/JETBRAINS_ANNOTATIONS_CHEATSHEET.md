
```xml
<dependencies>
    <dependency>
        <groupId>org.jetbrains</groupId>
        <artifactId>annotations</artifactId>
        <version>RELEASE</version>
        <scope>compile</scope>
    </dependency>
</dependencies>
```

```java
import org.jetbrains.annotations.NotNull;

```

`module-info.java`

```java
module domain {
    requires static lombok;
    requires org.jetbrains.annotations;
}
```