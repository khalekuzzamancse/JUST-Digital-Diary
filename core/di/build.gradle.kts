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
    jvm("desktop") {
        jvmToolchain(17)
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlinx.coroutines.core)
                implementation(project(modules.versions.domain.login.get()))
                implementation(project(modules.versions.data.login.get()))
                implementation(project(modules.versions.domain.register.get()))
                implementation(project(modules.versions.data.register.get()))
                implementation(project(modules.versions.domain.faculties.get()))
                implementation(project(modules.versions.data.faculties.get()))
                implementation(project(modules.versions.domain.departments.get()))
                implementation(project(modules.versions.data.departments.get()))
                implementation(project(modules.versions.domain.teachers.get()))
                implementation(project(modules.versions.data.teachers.get()))
                implementation(project(modules.versions.data.adminOffices.get()))
                implementation(project(modules.versions.domain.adminOffices.get()))
                implementation(project(modules.versions.data.adminSubOffices.get()))
                implementation(project(modules.versions.domain.adminSubOffices.get()))
                implementation(project(modules.versions.data.adminOfficers.get()))
                implementation(project(modules.versions.domain.adminOfficers.get()))

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