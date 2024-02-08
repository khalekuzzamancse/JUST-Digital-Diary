plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
    alias(libs.plugins.kotlinxSerialization)
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
                //network IO
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.okhttp)
                implementation(libs.ktor.client.content.negotiation)
                implementation(libs.coil3.network)
                implementation(libs.coil3)
                implementation(libs.coil3.core)
                implementation(libs.ktor.serialization.kotlinx.json)


            }
        }
        val androidMain by getting{
            dependencies {
            }
        }
        val desktopMain by getting{
            dependencies {
                //dependency to support android coil on desktop
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-swing")
            }
        }
    }


}
android {
    namespace = "com.just.cse.digital_diary.two_zero_two_three.common_ui"
    compileSdk = 34
    defaultConfig {
        minSdk = 27
    }

}

