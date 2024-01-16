plugins {
    id("java")
}

group = "com.github.otr"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation ("dev.langchain4j:langchain4j:0.25.0")
    implementation ("dev.langchain4j:langchain4j-open-ai:0.25.0")

//    implementation ("org.tinylog:tinylog-impl:2.6.2")
//    implementation ("org.tinylog:slf4j-tinylog:2.6.2")
    implementation("javax.servlet:javax.servlet-api:4.0.1")
    implementation ("com.pkslow:google-bard:0.3.6")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}