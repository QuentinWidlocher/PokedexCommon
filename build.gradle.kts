plugins {
    kotlin("jvm") version "1.4.21"
    id("maven-publish")
}

group = "me.1855639"
version = "1.0-SNAPSHOT"
val exposedVersion = "0.28.1"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation("org.slf4j:slf4j-simple:1.7.5")
    implementation("org.xerial:sqlite-jdbc:3.34.0")
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
}

publishing {
    repositories {
        
    }
}