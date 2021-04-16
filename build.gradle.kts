plugins {
    val pluginVersion by System.getProperties()

    id("io.micronaut.library") version "1.1.0"
    id("org.jetbrains.dokka") version "1.4.20"
    id("org.jetbrains.kotlin.jvm") version "$pluginVersion"
    id("org.jetbrains.kotlin.kapt") version "$pluginVersion"
    id("org.jetbrains.kotlin.plugin.allopen") version "$pluginVersion"
    id("groovy")
    id("maven")

}

version = "0.1.0"
group = "org.microjservice.lark"

val kotlinVersion = project.properties["kotlinVersion"]
val jdkVersion: String by project
val micronautVersion: String by project

micronaut {
    version(micronautVersion)
}

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    kapt(platform("io.micronaut:micronaut-bom:$micronautVersion"))
    kapt("io.micronaut:micronaut-inject-java")
    kaptTest("io.micronaut:micronaut-inject-java")
    testAnnotationProcessor("io.micronaut:micronaut-inject-java")
    dokkaHtmlPlugin("org.jetbrains.dokka:kotlin-as-java-plugin:1.4.20")

    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("io.micronaut", "micronaut-http-client")

    runtimeOnly("ch.qos.logback:logback-classic")

    testImplementation ("io.micronaut.test:micronaut-test-spock")
    testImplementation("org.spockframework:spock-core") {
        exclude("org.codehaus.groovy",  "groovy-all")
    }

    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("io.micronaut.test:micronaut-test-junit5:2.3.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0")

//    testImplementation( "io.micronaut.test:micronaut-test-kotest")
//    testImplementation( "io.mockk:mockk")
//    testImplementation( "io.kotest:kotest-runner-junit5-jvm")

    testImplementation("io.micronaut.test:micronaut-test-kotest:2.3.2")
    testImplementation("io.mockk:mockk:1.10.4")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:4.3.0")

    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

java {
    sourceCompatibility = JavaVersion.toVersion(jdkVersion)
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