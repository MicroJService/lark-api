import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val kotlinVersion: String by System.getProperties()
    val micronautPluginVersion: String by System.getProperties()
    val dokkaVersion: String by System.getProperties()

    id("io.micronaut.library") version micronautPluginVersion

    kotlin("jvm") version kotlinVersion
    kotlin("kapt") version kotlinVersion
    kotlin("plugin.allopen") version kotlinVersion
    id("org.jetbrains.dokka") version dokkaVersion

    id("groovy")
    id("maven-publish")

}

version = "0.1.3"
group = "org.microjservice.lark"

val jdkVersion: String by project
val micronautVersion: String by project
val micronautTestVersion: String by project
val jakartaAnnotationVersion: String by project
val jupiterEngineVersion: String by project
val mockkVersion: String by project
val kotestVersion: String by project

val dokkaVersion: String by System.getProperties()

micronaut {
    version(micronautVersion)

    runtime("jetty")
//    runtime("netty")

}



repositories {
    mavenCentral()
}



dependencies {
    kapt(platform("io.micronaut:micronaut-bom:$micronautVersion"))
    kapt("io.micronaut:micronaut-inject-java")
    kaptTest("io.micronaut:micronaut-inject-java")
    testAnnotationProcessor("io.micronaut", "micronaut-inject-java")
    dokkaHtmlPlugin("org.jetbrains.dokka", "kotlin-as-java-plugin", dokkaVersion)

    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    runtimeOnly("io.micronaut:micronaut-http-server-netty")
    implementation("io.micronaut:micronaut-http-server")

    implementation("io.micronaut", "micronaut-http-client")
    implementation("jakarta.inject", "jakarta.inject-api", jakartaAnnotationVersion)

    runtimeOnly("ch.qos.logback:logback-classic")
    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

//    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.12.+")
    implementation("io.github.microutils:kotlin-logging-jvm:2.0.6")

    testImplementation("io.micronaut.test:micronaut-test-spock")
    testImplementation("org.spockframework:spock-core") {
        exclude("org.codehaus.groovy", "groovy-all")
    }

    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("io.micronaut.test", "micronaut-test-junit5", micronautTestVersion)
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine", jupiterEngineVersion)

    testImplementation("io.micronaut.test", "micronaut-test-kotest")
    testImplementation("io.mockk", "mockk", mockkVersion)
    testImplementation("io.kotest", "kotest-runner-junit5-jvm", kotestVersion)
//    implementation(kotlin("stdlib-jdk8"))
}

tasks.withType<Test> {
    useJUnitPlatform()
}

java {
    sourceCompatibility = JavaVersion.toVersion(jdkVersion)
    withJavadocJar()
    withSourcesJar()
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = jdkVersion
        }
    }
    compileTestKotlin {
        kotlinOptions {
            jvmTarget = jdkVersion
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
    repositories {
        mavenLocal()
    }
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}