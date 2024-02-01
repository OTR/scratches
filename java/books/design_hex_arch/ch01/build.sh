mkdir -p target
find src -name "*.java" -print | xargs javac -d target
MAIN_CLASS=design.hexagonal.architecture.App
jar cfe ./target/chapter01.jar $MAIN_CLASS -C target . -C src/main/resources/ .
