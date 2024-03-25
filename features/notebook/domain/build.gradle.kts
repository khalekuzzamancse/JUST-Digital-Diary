plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
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
    namespace = "notebook.domain"
    compileSdk = 34
    defaultConfig {
        minSdk = 27
    }

}