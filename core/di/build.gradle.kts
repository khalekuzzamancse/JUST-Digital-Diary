plugins {
    kotlin("multiplatform")
    id("com.android.library")
}
kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }
    jvm("desktop"){
        jvmToolchain(17)
    }
    sourceSets{
        val commonMain by getting{
            dependencies {
                implementation(libs.kotlinx.coroutines.core)
                implementation(project(localModules.versions.core.database.realm.get()))
                implementation(project(localModules.versions.data.login.get()))
                implementation(project(localModules.versions.data.register.get()))
            }
        }
        val androidMain by getting{
            dependencies {


            }
        }
        val desktopMain by getting{
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-swing")
            }
        }
    }
    //to use expect and actual keywords
    kotlin {
        compilerOptions {
            // Common compiler options applied to all Kotlin source sets
            freeCompilerArgs.add("-Xmulti-platform")
        }
    }


}
android {
    namespace = "com.just.cse.digitaldiary.twozerotwothree.core.di"
    compileSdk = 34
    defaultConfig {
        minSdk = 27
    }

}
