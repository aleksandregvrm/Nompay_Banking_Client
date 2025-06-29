plugins {
  java
  id("org.springframework.boot") version "3.3.0"
  id("io.spring.dependency-management") version "1.1.5"
  id("org.flywaydb.flyway") version "9.21.1"
}

group = "com.nompay.bank"
version = "0.0.1-SNAPSHOT"

java {
  toolchain {
    languageVersion.set(JavaLanguageVersion.of(21))
  }
}

repositories {
  mavenCentral()
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.springframework.boot:spring-boot-starter-graphql")
  implementation("org.flywaydb:flyway-core:9.21.1")
  implementation("org.flywaydb:flyway-mysql:9.21.1")
  implementation("jakarta.validation:jakarta.validation-api:3.0.2")
  implementation("org.hibernate.validator:hibernate-validator:8.0.1.Final")

  runtimeOnly("com.mysql:mysql-connector-j")

  testImplementation("org.springframework.boot:spring-boot-starter-test") {
    exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
  }
  testImplementation("org.springframework.security:spring-security-test")
  testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
  useJUnitPlatform()
}
