plugins {
    `java-library`
    id("org.metaborg.convention.java")
    id("org.metaborg.convention.maven-publish")
}

sourceSets {
    test {
        java {
            setSrcDirs(listOf<Any>()) // Disable tests, they cannot be executed.
        }
    }
}

dependencies {
    api(platform(libs.metaborg.platform)) { version { require("latest.integration") } }

    api(libs.spoofax.terms)
    api(project(":org.spoofax.interpreter.core"))
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
}
