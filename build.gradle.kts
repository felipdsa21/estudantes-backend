import net.ltgt.gradle.errorprone.errorprone

plugins {
    java
    idea
    id("org.springframework.boot") version "3.4.4"
    id("io.spring.dependency-management") version "1.1.7"
    id("net.ltgt.errorprone") version "4.1.0"
}

group = "edge.academy"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(23)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    errorprone("com.google.errorprone:error_prone_core:2.37.0")
    implementation("org.mapstruct:mapstruct:1.6.3")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.6")
    compileOnly("org.projectlombok:lombok")
    runtimeOnly("org.postgresql:postgresql")
    annotationProcessor("org.projectlombok:lombok")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")
}

tasks.withType<JavaCompile> {
    options.compilerArgs.addAll(
        arrayOf("-Xlint", "-Amapstruct.defaultComponentModel=spring", "-Amapstruct.unmappedTargetPolicy=ERROR")
    )
    options.errorprone.excludedPaths = ".*/build/.*MapperImpl\\.java"
}
