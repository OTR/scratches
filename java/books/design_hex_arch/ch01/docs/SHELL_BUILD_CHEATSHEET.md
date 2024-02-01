## How to create a shell build script

```shell
mkdir -p target
find src -name "*.java" -print | xargs javac -d target
MAIN_CLASS=dev.davivieira.App
jar cfe ./target/chapter01.jar $MAIN_CLASS -C target . -C src/main/resources/ .
```
