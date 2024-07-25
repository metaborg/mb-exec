rootProject.name = "mb-exec-project"

dependencyResolutionManagement {
    repositories {
        maven("https://artifacts.metaborg.org/content/groups/public/")
        mavenCentral()
    }
}

pluginManagement {
    repositories {
        maven("https://artifacts.metaborg.org/content/groups/public/")
        gradlePluginPortal()
    }
}

plugins {
    id("org.metaborg.convention.settings") version "latest.integration"
}

include("org.metaborg.util")
include("org.metaborg.interpreter.core")
include("org.metaborg.interpreter.library.java")
include("org.metaborg.interpreter.library.xml")
include("util-vfs2")

