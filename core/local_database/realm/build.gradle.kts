plugins {
    kotlin("multiplatform")
    id("com.android.library")
    alias(libs.plugins.realm)

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
                implementation(libs.database.realm.base)
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
    namespace = "com.just.cse.digitaldiary.twozerotwothree.core.local_database.realm"
    compileSdk = 34
    defaultConfig {
        minSdk = 27
    }

}
