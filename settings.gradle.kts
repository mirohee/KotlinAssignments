pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

rootProject.name = "KotlinTest"
include("Lab01StudyRegister")
include("Lab02FractionClass")
include("Lab03Lotto")
