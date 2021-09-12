import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("plugin.jpa") version "1.4.32"
	id("org.springframework.boot") version "2.5.4"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	id("org.unbroken-dome.test-sets") version "4.0.0"
	kotlin("jvm") version "1.5.21"
	kotlin("plugin.spring") version "1.5.21"
}

group = "com.service"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
	maven {
		name = "jitpack"
		url = uri("https://www.jitpack.io")
	}
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-mustache")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("com.github.kittinunf.fuel:fuel:2.3.1")
	implementation("org.slf4j:slf4j-simple:1.7.29")
	implementation("io.github.microutils:kotlin-logging:1.12.5")
	implementation("io.insert-koin:koin-core:3.1.2")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	implementation("org.ktorm:ktorm-core:3.4.1")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.8")
	runtimeOnly("com.h2database:h2")
	runtimeOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
	testImplementation("io.insert-koin:koin-test:3.1.2")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}
/*
testSets {
	integrationTest { dirName = "integration" }
}

// Integration tests must run after unit tests
integrationTest.mustRunAfter test

integrationTest {
	useJUnitPlatform {
		includeTags = "integrationTest"
	}
}

test {
	useJUnitPlatform {
		includeTags = "unitTest"
	}
}
*/
tasks.withType<Test> {
	useJUnitPlatform()
}
