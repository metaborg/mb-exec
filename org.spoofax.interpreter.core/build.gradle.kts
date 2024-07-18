plugins {
    `java-library`
    `maven-publish`
    id("org.metaborg.convention.java")
    id("org.metaborg.convention.maven-publish")
}

fun compositeBuild(name: String) = "$group:$name:$version"
val spoofax2Version: String by ext
dependencies {
    api(platform(libs.metaborg.platform)) { version { require("latest.integration") } }

    api(compositeBuild("org.spoofax.terms"))
    api(compositeBuild("org.spoofax.jsglr"))
    implementation(compositeBuild("org.metaborg.util"))
    implementation("commons-io:commons-io")
    api("io.usethesource:capsule")
    implementation(compositeBuild("org.metaborg.util"))

    implementation("jakarta.annotation:jakarta.annotation-api")

    testCompileOnly("junit:junit")
    testRuntimeOnly("org.junit.vintage:junit-vintage-engine")
}

// Copy test resources into classes directory, to make them accessible as classloader resources at runtime.
val copyTestResourcesTask = tasks.create<Copy>("copyTestResources") {
    from("$projectDir/src/test/resources")
    into("$buildDir/classes/java/test")
}
tasks.getByName("processTestResources").dependsOn(copyTestResourcesTask)
