plugins {
  id("org.metaborg.gradle.config.java-library")
  id("org.metaborg.gradle.config.junit-testing")
}

fun compositeBuild(name: String) = "$group:$name:$version"
val spoofax2Version: String by ext
dependencies {
  api(platform("org.metaborg:parent:$spoofax2Version"))

  api(compositeBuild("org.spoofax.terms"))
  api(compositeBuild("org.spoofax.jsglr"))
  implementation("commons-io:commons-io")
  api("io.usethesource:capsule")

  compileOnly("jakarta.annotation:jakarta.annotation-api")

  testImplementation(compositeBuild("org.metaborg.util"))
  testCompileOnly("junit:junit")
  testCompileOnly("jakarta.annotation:jakarta.annotation-api")
  testRuntimeOnly("org.junit.vintage:junit-vintage-engine")
}

// Copy test resources into classes directory, to make them accessible as classloader resources at runtime.
val copyTestResourcesTask = tasks.create<Copy>("copyTestResources") {
  from("$projectDir/src/test/resources")
  into("$buildDir/classes/java/test")
}
tasks.getByName("processTestResources").dependsOn(copyTestResourcesTask)
