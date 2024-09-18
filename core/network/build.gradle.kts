plugins {
    kotlin("multiplatform")
    id("com.android.library")
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
                //network IO
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.okhttp)
                implementation(libs.ktor.client.content.negotiation)
                implementation(libs.ktor.serialization.kotlinx.json)
                implementation(libs.kotlinx.coroutines.core)


            }
        }
        val commonTest by getting {
            dependencies{
                implementation(libs.kotlin.test)
                implementation(libs.kotlin.test.junit)
            }


        }
        val androidMain by getting{
            dependencies {
                implementation(libs.ktor.client.okhttp)

            }
        }
        val desktopMain by getting{
            dependencies {
                implementation(libs.ktor.client.okhttp)
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-swing")
            }
        }
    }


}
android {
    namespace = "core.network"
    compileSdk = 34
    defaultConfig {
        minSdk = 27
    }

}