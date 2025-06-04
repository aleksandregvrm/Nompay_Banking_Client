plugins {
	java
	id("org.springframework.boot") version "3.3.0"
	id("io.spring.dependency-management") version "1.1.5"
	id("com.netflix.dgs.codegen") version "7.0.3"
}

group = "com.nompay.bank"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(23))
	}
}

repositories {
	mavenCentral()
	maven {
		url = uri("https://plugins.gradle.org/m2/")
	}
	maven {
		url = uri("https://dl.cloudsmith.io/public/netflix/dgs-framework/maven/")
	}
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
//	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-graphql")

	implementation("jakarta.validation:jakarta.validation-api:3.0.2")
	implementation("org.hibernate.validator:hibernate-validator:8.0.1.Final")

	runtimeOnly("com.mysql:mysql-connector-j")

	// ✅ Use version 9.1.3 which actually exists
	implementation("com.netflix.graphql.dgs:graphql-dgs-spring-boot-starter:9.1.3")

	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
	testImplementation("org.springframework.security:spring-security-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.named<com.netflix.graphql.dgs.codegen.gradle.GenerateJavaTask>("generateJava") {
	schemaPaths.add("${projectDir}/src/main/resources/graphql-client")
	packageName = "com.nompay.bank.solutions.clientService.codegen"
	generateClient = true
}

tasks.withType<Test> {
	useJUnitPlatform()
}
