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
                implementation(compose.ui)
                implementation(compose.material3)
                implementation(compose.animation)
                implementation(compose.animationGraphics)
                implementation(compose.materialIconsExtended)
                //for custom fonts
                @OptIn(ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
                //
                api(libs.coil3)
                api(libs.coil3.network)
                //
                implementation(project(":architecture_layers:ui:common_ui"))
                implementation("dev.chrisbanes.material3:material3-window-size-class-multiplatform:0.3.1")
                implementation(libs.voyager.navigator)
                implementation(libs.voyager.transitions)
                implementation(project(":core:data_layer:repository"))
               // implementation(project(":architecture_layers:ui:common_ui"))
                implementation(project(modules.versions.common.ui.get()))



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
    namespace = "com.just.cse.digital_diary.two_zero_two_three.employee_list"
    compileSdk = 34
    defaultConfig {
        minSdk = 27
    }

}
