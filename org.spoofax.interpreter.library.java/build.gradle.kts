plugins {
  id("org.metaborg.gradle.config.java-library")
}

sourceSets {
  test {
    java {
      setSrcDirs(listOf<Any>()) // Disable tests, they cannot be executed.
    }
  }
}

fun compositeBuild(name: String) = "$group:$name:$version"
val spoofax2Version: String by ext
dependencies {
  api(platform("org.metaborg:parent:$spoofax2Version"))

  api(compositeBuild("org.spoofax.terms"))
  api(project(":org.spoofax.interpreter.core"))
}
