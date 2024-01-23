plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("app.cash.sqldelight") version "2.0.1" //for sql delight
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


            }
        }
        val androidMain by getting{
            dependencies {
                implementation("app.cash.sqldelight:android-driver:2.0.1")

            }
        }
        val desktopMain by getting{
            dependencies {
                implementation("app.cash.sqldelight:native-driver:2.0.1")
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
    namespace = "com.just.cse.digitaldiary.twozerotwothree.core.local_database.sql_delight"
    compileSdk = 34
    defaultConfig {
        minSdk = 27
    }

}
//DSL Block for SQLDelight
sqldelight {
    databases {
        create("Database") {
            //the class name will be "Database" that will be auto generated using annotation processor
            packageName.set("com.sql_delight")
        }
    }
}