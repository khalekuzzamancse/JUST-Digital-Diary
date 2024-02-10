plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
}
//
kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }
    jvm("desktop") {
        jvmToolchain(17)
    }
    sourceSets {
        val commonMain by getting {
            dependencies {

                implementation(project(localModules.versions.common.ui.get()))
                implementation(project(localModules.versions.ui.notes.get()))
                implementation(project(localModules.versions.domain.notes.get()))
                implementation(project(localModules.versions.core.di.get()))



            }
        }
//        val androidMain by getting{
//            dependencies {
//
//            }
//        }
        val desktopMain by getting {
            dependencies {
//coroutines
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-swing")
            }
        }
    }


}
android {
    namespace = "com.just.cse.digital_diary.two_zero_two_three.sharing_document"
    compileSdk = 34
    defaultConfig {
        minSdk = 27
    }

}