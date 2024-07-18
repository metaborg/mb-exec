plugins {
    `java-library`
    `maven-publish`
    id("org.metaborg.convention.java")
    id("org.metaborg.convention.maven-publish")
}

dependencies {
    api(platform(libs.metaborg.platform)) { version { require("latest.integration") } }

    api(libs.spoofax.terms)
    api(libs.jsglr)
    implementation(libs.metaborg.util)
    implementation(libs.commons.io)
    api(libs.capsule)

    implementation(libs.jakarta.annotation)

    testImplementation(libs.junit)
    testCompileOnly(libs.junit4)
    testRuntimeOnly(libs.junit.vintage)
}

// Copy test resources into classes directory, to make them accessible as classloader resources at runtime.
val copyTestResourcesTask = tasks.create<Copy>("copyTestResources") {
    from("$projectDir/src/test/resources")
    into("$buildDir/classes/java/test")
}
tasks.getByName("processTestResources").dependsOn(copyTestResourcesTask)
