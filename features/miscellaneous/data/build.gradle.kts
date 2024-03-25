plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
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
                implementation(libs.kotlinx.coroutines.core)
                implementation(project(localModules.versions.core.netowork.get()))
                implementation(libs.ktor.serialization.kotlinx.json)
                implementation(project(localModules.versions.feature.miscellaneous.domain.get()))

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
    namespace = "miscellaneous.data"
    compileSdk = 34
    defaultConfig {
        minSdk = 27
    }

}