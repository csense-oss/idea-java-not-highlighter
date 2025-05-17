import org.jetbrains.intellij.platform.gradle.*
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    //https://github.com/JetBrains/gradle-intellij-plugin
    id("org.jetbrains.intellij.platform") version "2.6.0"
    //https://github.com/JetBrains/kotlin
    kotlin("jvm") version "2.1.21"
    //https://github.com/Kotlin/kotlinx.serialization
    kotlin("plugin.serialization") version "2.1.21"
    //https://jeremylong.github.io/DependencyCheck/
    id("org.owasp.dependencycheck") version "12.1.0"
}

repositories {
    intellijPlatform {
        defaultRepositories()
    }
}

val javaVersion = "21"

group = "csense-idea"
version = "1.0.0"

repositories {
    mavenCentral()
    mavenLocal()
    maven {
        setUrl("https://pkgs.dev.azure.com/csense-oss/csense-oss/_packaging/csense-oss/maven/v1")
        name = "Csense oss"
    }
}

dependencies {
    //https://github.com/csense-oss/csense-kotlin
    implementation("csense.kotlin:csense-kotlin-jvm:0.0.60")

    //https://github.com/csense-oss/csense-kotlin-annotations
    implementation("csense.kotlin:csense-kotlin-annotations-jvm:0.0.63")

    //https://github.com/csense-oss/csense-kotlin-datastructures-algorithms
    implementation("csense.kotlin:csense-kotlin-datastructures-algorithms:0.0.41")

    //https://github.com/csense-oss/idea-kotlin-shared-base
    implementation("csense.idea.base:csense-idea-base:0.1.71")

    //https://github.com/Kotlin/kotlinx.serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.1")

    intellijPlatform {
        intellijIdeaCommunity("2024.3")
        bundledPlugin("com.intellij.java")
        pluginVerifier()
        zipSigner()
        testFramework(TestFrameworkType.Platform)
    }
}
intellijPlatform {
    pluginConfiguration {
        //language=html
        changeNotes = """
            Initial version based on the kotlin version
        """.trimIndent()
        ideaVersion {
            sinceBuild = "243"
            untilBuild = provider { null }
        }
    }

    pluginVerification {
        ides {
            recommended()
        }
    }
}

tasks.getByName("check").dependsOn("dependencyCheckAnalyze")

tasks {

    withType<JavaCompile> {
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }

    test {
        testLogging {
            showExceptions = true
            showStackTraces = true
            exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
        }
    }
}

kotlin {
    compilerOptions{
        jvmTarget.set(JvmTarget.fromTarget(javaVersion))
    }
}


sourceSets {
    test {
        resources {
            srcDir("testData")
        }
    }
}