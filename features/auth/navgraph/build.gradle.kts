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
                implementation(project(localModules.versions.feature.auth.components.get()))
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
                implementation(libs.voyager.transitions)
            }
        }

    }


}
android {
    namespace = "com.just.cse.digital_diary.two_zero_two_three.auth.destination"
    compileSdk = 34
    defaultConfig {
        minSdk = 27
    }

}

