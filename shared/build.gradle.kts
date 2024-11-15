import org.jetbrains.kotlin.gradle.dsl.JvmTarget

import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.mavenPublish)
    alias(libs.plugins.signing)
}

val javadocJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
}

group = "io.github.tyczj"
version = "0.0.1"

kotlin {
    androidTarget {
        publishLibraryVariants("release")
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_17)
                }
            }
        }
    }
    
    val xcf = XCFramework()
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "bsky-kotlin"
            xcf.add(this)
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            //put your multiplatform dependencies here
            implementation(libs.ktor.core)
            implementation(libs.ktor.serialization)
            implementation(libs.ktor.json)
            implementation(libs.ktor.content.negotiation)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.serialization)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        androidMain.dependencies {
            implementation(libs.ktor.okhttp)
        }
        iosMain.dependencies {
            implementation(libs.ktor.darwin)
        }
    }
}

android {
    namespace = "com.tyczj.bsky"
    compileSdk = 35
    defaultConfig {
        minSdk = 28
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    dependencies {
        implementation(libs.kotlinx.coroutines.android)
    }
}

afterEvaluate {

//    publishing {
//        repositories {
//            maven {
//                name = "sonatype"
//                url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
//                credentials {
//                    val ossrhUsername = System.getenv("OSSRH_USERNAME") ?: rootProject.ext["ossrhUsername"]?.toString()
//                    val ossrhPassword = System.getenv("OSSRH_PASSWORD") ?: rootProject.ext["ossrhPassword"]?.toString()
//                    username = ossrhUsername
//                    password = ossrhPassword
//                }
//            }
//        }
//
//        publications.withType<MavenPublication> {
//
//            artifact(javadocJar.get())
//
//            pom{
//                name.set("bsky-kotlin")
//                description.set("")
//                url.set("https://github.com/tyczj/bsky-kotlin")
//                licenses {
//                    license {
//                        name.set("MIT")
//                        url.set("https://opensource.org/licenses/MIT")
//                    }
//                }
//                developers {
//                    developer {
//                        id.set("tyczj")
//                        name.set("Jeff Tycz")
//                        email.set("tyczj359@gmail.com")
//                    }
//                }
//                scm {
//                    url.set("https://github.com/tyczj/Tweedle")
//                }
//            }
//        }
//    }
}

//signing {
//    val signingKeyId = System.getenv("SIGNING_KEYID") ?: rootProject.ext["signing.keyId"]?.toString()
//    val signingPassword = System.getenv("SIGNING_PASSWORD") ?: rootProject.ext["signing.password"]?.toString()
//    val gpgPrivateKey = System.getenv("GPG_PRIVATE_KEY")
//    if(gpgPrivateKey != null){
//        val signingKey: String? by project
//        val signingPassword: String? by project
//        useInMemoryPgpKeys(signingKey,signingPassword)
//    }else{
//        ext["signing.keyId"] = signingKeyId
//        ext["signing.password"] = signingPassword
//        ext["signing.secretKeyRingFile"] = rootProject.ext["signing.secretKeyRingFile"]?.toString()
//    }
//
//    sign(publishing.publications)
//}

