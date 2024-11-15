import java.net.URI

plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.kotlinMultiplatform).apply(false)
    alias(libs.plugins.kotlinSerialization).apply(false)
    alias(libs.plugins.nexus).apply(false)
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven{
            url = URI.create("https://oss.sonatype.org/content/repositories/snapshots/")
        }
    }
}
