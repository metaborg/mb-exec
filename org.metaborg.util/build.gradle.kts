plugins {
  id("org.metaborg.gradle.config.java-library")
  id("org.metaborg.gradle.config.junit-testing")
}

val spoofax2Version: String by ext
dependencies {
  api(platform("org.metaborg:parent:$spoofax2Version"))

  api("org.apache.commons:commons-vfs2")
  api("commons-io:commons-io")
  api("io.reactivex.rxjava3:rxjava")
  api("io.usethesource:capsule")
  api("com.google.guava:guava")
  // Required for Guava >= 27.0:
  api("com.google.guava:failureaccess") // api("com.google.guava:failureaccess:1.0.1") // TODO: Remove version

  api("com.google.inject:guice")
  api("org.slf4j:slf4j-api")

  compileOnly("com.google.code.findbugs:jsr305")

  testCompileOnly("junit:junit")
  testCompileOnly("com.google.code.findbugs:jsr305")
  testRuntimeOnly("org.junit.vintage:junit-vintage-engine")
}

// Copy test resources into classes directory, to make them accessible as classloader resources at runtime.
val copyTestResourcesTask = tasks.create<Copy>("copyTestResources") {
  from("$projectDir/src/test/resources")
  into("$buildDir/classes/java/test")
}
tasks.getByName("processTestResources").dependsOn(copyTestResourcesTask)
