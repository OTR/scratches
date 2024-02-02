mkdir -p target
find src -name "*.java" -print | xargs javac -d target
MAIN_CLASS=hexagonal.architecture.App
jar cfe ./target/chapter04.jar $MAIN_CLASS -C target . -C src/main/resources/ .
