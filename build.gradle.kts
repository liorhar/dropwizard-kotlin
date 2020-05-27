import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val dwVersion = "2.0.10"
val junitVersion = "5.6.0"

plugins {
    // Apply the Kotlin JVM plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm") version "1.3.70"

    // Apply the application plugin to add support for building a CLI application.
    application
    id("org.flywaydb.flyway") version "6.4.2"
    id("com.github.johnrengelman.shadow") version "5.2.0"

}

repositories {
    // Use jcenter for resolving dependencies.
    // You can declare any Maven/Ivy/file repository here.
    jcenter()
}

dependencies {
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("io.dropwizard:dropwizard-core:$dwVersion")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.6")

    implementation("com.google.inject:guice:4.2.0")

    implementation("ru.vyarus:dropwizard-guicey:5.0.1")

    // Use the Kotlin test library.
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    // Use the Kotlin JUnit integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")

    testImplementation("org.junit.jupiter:junit-jupiter:$junitVersion")
    testImplementation("com.nhaarman:mockito-kotlin:1.5.0")
    testImplementation("io.dropwizard:dropwizard-testing:$dwVersion")
    testImplementation("io.rest-assured:rest-assured:3.1.0")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}

application {
    // Define the main class for the application.
    mainClassName = "liorh.dwkotlin.AppKt"
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    mergeServiceFiles()
    archiveBaseName.set("app")
    archiveClassifier.set("")
    archiveVersion.set("")
    exclude("META-INF/*.DSA", "META-INF/*.RSA")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        kotlinOptions.allWarningsAsErrors = true
    }
}