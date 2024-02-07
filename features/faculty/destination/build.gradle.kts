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
                implementation(project(localModules.versions.feature.faculty.components.get()))
                implementation(project(localModules.versions.core.di.get()))



            }
        }
        val androidMain by getting{
            dependencies {
                implementation("androidx.navigation:navigation-compose:2.7.6")
            }
        }
        val desktopMain by getting{
            dependencies {
                implementation(libs.voyager.navigator)
                implementation(libs.voyager.navigator)
            }
        }
    }


}
android {
    namespace = "com.just.cse.digital_diary.features.faculty.destination"
    compileSdk = 34
    defaultConfig {
        minSdk = 27
    }

}