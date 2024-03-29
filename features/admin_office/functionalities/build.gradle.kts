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

                api(project(localModules.versions.domain.admin.offices.get()))
                api(project(localModules.versions.domain.admin.subOffices.get()))
                api(project(localModules.versions.domain.admin.officers.get()))
                api(project(localModules.versions.ui.admin.offices.get()))
                api(project(localModules.versions.ui.admin.subOffices.get()))
                api(project(localModules.versions.ui.admin.officers.get()))

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
    namespace = "com.just.cse.digital_diary.features.admin_office.components"
    compileSdk = 34
    defaultConfig {
        minSdk = 27
    }

}