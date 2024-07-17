plugins {
    `java-library`
    `maven-publish`
    id("org.metaborg.convention.java")
    id("org.metaborg.convention.maven-publish")
}

val spoofax2Version: String by ext
dependencies {
    api(platform("org.metaborg:parent:$spoofax2Version"))

    api("org.apache.commons:commons-vfs2")
    api("commons-io:commons-io")
    api("io.reactivex.rxjava3:rxjava")
    api("io.usethesource:capsule")
    api("com.google.inject:guice")
    api("org.slf4j:slf4j-api")

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
