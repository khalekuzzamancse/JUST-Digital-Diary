import org.jetbrains.compose.ExperimentalComposeLibrary

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
                implementation(project(localModules.versions.feature.search.functionalities.get()))

            }
        }
        val androidMain by getting{
            dependencies {
                implementation("androidx.navigation:navigation-compose:2.7.6")
            }
        }

        val desktopMain by getting {
            dependencies {
                implementation(libs.voyager.navigator)
                implementation(libs.voyager.transitions)
            }
        }
        //to use expect and actual keywords
        kotlin {
            compilerOptions {
                // Common compiler options applied to all Kotlin source sets
                freeCompilerArgs.add("-Xmulti-platform")
            }
        }
    }


}
android {
    namespace = "com.just.cse.digital_diary.two_zero_two_three.search.navgraph"
    compileSdk = 34
    defaultConfig {
        minSdk = 27
    }

}
