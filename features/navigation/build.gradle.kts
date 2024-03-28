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
                implementation(compose.foundation)
                implementation(compose.runtime)
                implementation(libs.windowSize)
                implementation(compose.components.resources)
                implementation(project(localModules.versions.common.ui.get()))
                implementation(libs.kotlinx.coroutines.core)
                //
                implementation(project(localModules.versions.feature.academic.ui.get()))
                implementation(project(localModules.versions.feature.administration.ui.get()))
                implementation(project(localModules.versions.feature.authentication.ui.get()))
                implementation(project(localModules.versions.feature.miscellaneous.ui.get()))
                implementation(project(localModules.versions.feature.notebook.ui.get()))
                implementation(project(localModules.versions.feature.queryservice.ui.get()))
                //
                implementation(project(localModules.versions.feature.authentication.di.get()))


            }
        }
        val androidMain by getting{
            dependencies {
                implementation(libs.kotlinx.coroutines.android)
                implementation("androidx.navigation:navigation-compose:2.7.6")


            }
        }
        val desktopMain by getting{
            dependencies {
                implementation(libs.voyager.navigator)
                implementation(libs.voyager.transitions)
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-swing")

            }
        }
    }


}
android {
    namespace = "navigation"
    compileSdk = 34
    defaultConfig {
        minSdk = 27
    }

}