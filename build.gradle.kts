plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2") // For JUnit 5
    testImplementation("io.qameta.allure:allure-junit5:2.21.0") // Allure JUnit5
    testImplementation("org.testng:testng:7.5")
    testImplementation("io.qameta.allure:allure-testng:2.21.0")
    testImplementation("org.seleniumhq.selenium:selenium-java:4.29.0")
}

tasks.test {
    useJUnitPlatform()
}