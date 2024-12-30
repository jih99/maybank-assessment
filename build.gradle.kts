import org.openapitools.generator.gradle.plugin.tasks.GenerateTask

plugins {
	java
	id("io.freefair.lombok") version "8.11"
	id("org.springframework.boot") version "3.4.1"
	id("io.spring.dependency-management") version "1.1.7"
	id("org.openapi.generator") version "7.9.0"
}

group = "com.maybank"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenLocal()
	mavenCentral()
}

dependencies {
	implementation ("org.slf4j:slf4j-api:2.0.9")
	implementation("ch.qos.logback:logback-classic:1.4.11")

	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.mapstruct:mapstruct:1.5.5.Final")
	annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")
	compileOnly("org.projectlombok:lombok:1.18.30")
	annotationProcessor("org.projectlombok:lombok:1.18.30")
	implementation("org.flywaydb:flyway-core")
	implementation("org.flywaydb:flyway-database-postgresql")
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}
sourceSets {
	main {
		java {
			srcDir("src/main/java")
			srcDir("${layout.buildDirectory.get()}/generated/sources/openapi/assessment/java")
		}
	}
}

tasks {
	val generateOpenApiDocs = "generateOpenApiDocs"

	register<GenerateTask>(generateOpenApiDocs){
		group = "openapi"
		description = "Generate OpenAPI documentation"
		generatorName = "spring"
		packageName = "com.maybank.assessment"

		inputSpec = "${projectDir}/src/main/resources/openapi.yaml"
		outputDir = "${layout.buildDirectory.get()}/generated/sources/openapi/assessment"
		apiPackage = "${packageName.get()}.api.generated"
		modelPackage = "${packageName.get()}.model.generated"
		apiNameSuffix.set("Api")
		typeMappings.put("string-date", "Instant")
		importMappings.put("Instant", "java.time.Instant")

		globalProperties.put("apis", "")
		globalProperties.put("models", "")
		globalProperties.put("supportingFiles", "ApiUtil.java")

		configOptions.put("basePackage", packageName.get())
		configOptions.put("annotationLibrary", "none")
		configOptions.put("delegatePattern", "false")
		configOptions.put("documentationProvider", "none")
		configOptions.put("useSpringBoot3", "true")
		configOptions.put("sourceFolder", "java")
		configOptions.put("useTags", "true")
		configOptions.put("useBeanValidation", "true")
		configOptions.put("useSwaggerUI", "false")
		configOptions.put("modelMutable", "false")
		configOptions.put("modelNameSuffix", "DTO")
		configOptions.put("interfaceOnly", "true")
		configOptions.put("openApiNullable", "false")



	}

	compileJava{
		dependsOn (generateOpenApiDocs)
	}


}

tasks.withType<Test> {
	useJUnitPlatform()
}
