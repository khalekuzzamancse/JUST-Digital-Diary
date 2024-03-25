plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)
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
                implementation(compose.foundation)
                implementation(compose.runtime)
                implementation(libs.windowSize)
//                implementation(compose.components.resources)
                //
                implementation(project(localModules.versions.common.ui.get()))
                implementation(project(localModules.versions.feature.notebook.domain.get()))
                implementation(project(localModules.versions.feature.notebook.di.get()))
                //view-model
                implementation(libs.kotlinx.coroutines.core)

            }
        }
        val androidMain by getting{
            dependencies {
//navigation graph define
                implementation(libs.androidx.navigation.compose)
            }
        }
        val desktopMain by getting{
            dependencies {
                //dependency to support android coroutine on desktop
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-swing")
            }
        }
    }


}
android {
    namespace = "notebook.ui"
    compileSdk = 34
    defaultConfig {
        minSdk = 27
    }

}