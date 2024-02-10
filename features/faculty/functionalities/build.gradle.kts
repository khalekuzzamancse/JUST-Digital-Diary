plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
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

                implementation(project(localModules.versions.common.ui.get()))
                api(project(localModules.versions.ui.teachers.get()))
                api(project(localModules.versions.ui.faculties.get()))
                api(project(localModules.versions.ui.departments.get()))
                api(project(localModules.versions.domain.teachers.get()))
                api(project(localModules.versions.domain.faculties.get()))
                api(project(localModules.versions.domain.departments.get()))

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
    namespace = "com.just.cse.digital_diary.features.faculty.components"
    compileSdk = 34
    defaultConfig {
        minSdk = 27
    }

}