plugins {
  id("org.metaborg.gradle.config.java-library")
  id("org.metaborg.gradle.config.junit-testing")
}

dependencies {
  api(platform("org.metaborg:parent:$version"))

  api("org.apache.commons:commons-vfs2")
  api("commons-io:commons-io")
  api("io.reactivex.rxjava3:rxjava")
  api("com.google.guava:guava")
  api("com.google.inject:guice")
  api("org.slf4j:slf4j-api")

  compileOnly("com.google.code.findbugs:jsr305")

  testCompileOnly("junit:junit")
  testCompileOnly("com.google.code.findbugs:jsr305")
  testRuntimeOnly("org.junit.vintage:junit-vintage-engine:5.1.0")
}

// Copy test resources into classes directory, to make them accessible as classloader resources at runtime.
val copyTestResourcesTask = tasks.create<Copy>("copyTestResources") {
  from("$projectDir/src/test/resources")
  into("$buildDir/classes/java/test")
}
tasks.getByName("processTestResources").dependsOn(copyTestResourcesTask)
