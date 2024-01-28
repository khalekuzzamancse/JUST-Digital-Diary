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
                implementation(project(modules.versions.common.ui.get()))
                implementation(project(modules.versions.ui.adminOffices.get()))
                implementation(project(modules.versions.domain.adminOffices.get()))
                implementation(project(modules.versions.data.adminOffices.get()))
                //
                implementation(project(modules.versions.domain.adminSubOffices.get()))
                implementation(project(modules.versions.data.adminSubOffices.get()))
                implementation(project(modules.versions.ui.adminSubOffices.get()))
                //
                implementation(project(modules.versions.domain.adminOfficers.get()))
                implementation(project(modules.versions.ui.adminOfficers.get()))
                implementation(project(modules.versions.data.adminOfficers.get()))

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