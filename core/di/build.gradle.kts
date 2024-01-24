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
                implementation(project(modules.versions.domain.login.get()))
                implementation(project(modules.versions.data.login.get()))
                implementation(project(modules.versions.domain.register.get()))
                implementation(project(modules.versions.data.register.get()))
                implementation(project(modules.versions.domain.faculties.get()))
                implementation(project(modules.versions.data.faculties.get()))

            }
        }
//        val androidMain by getting{
//            dependencies {
//
//            }
//        }
//        val desktopMain by getting{
//            dependencies {
//
//            }
//        }
    }


}
android {
    namespace = "com.just.cse.digitaldiary.twozerotwothree.core.di"
    compileSdk = 34
    defaultConfig {
        minSdk = 27
    }

}