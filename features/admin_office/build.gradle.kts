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
                implementation(compose.ui)
                implementation(compose.material3)
                implementation(compose.animation)
                implementation(compose.animationGraphics)
                implementation(compose.materialIconsExtended)
                implementation(compose.preview)
                implementation("dev.chrisbanes.material3:material3-window-size-class-multiplatform:0.3.1")
                implementation(project(localModules.versions.common.ui.get()))

                implementation(project(localModules.versions.ui.admin.offices.get()))
                implementation(project(localModules.versions.data.admin.offices.get()))
                //
                implementation(project(localModules.versions.data.admin.subOffices.get()))
                implementation(project(localModules.versions.ui.admin.subOffices.get()))
                //
                implementation(project(localModules.versions.ui.admin.officers.get()))
                implementation(project(localModules.versions.data.admin.officers.get()))

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
    namespace = "com.just.cse.digital_diary.features.admin_office"
    compileSdk = 34
    defaultConfig {
        minSdk = 27
    }

}