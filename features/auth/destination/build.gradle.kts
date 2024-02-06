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
                //
                implementation("dev.chrisbanes.material3:material3-window-size-class-multiplatform:0.3.1")
                implementation(project(localModules.versions.common.ui.get()))
                implementation(project(localModules.versions.ui.login.get()))
                implementation(project(localModules.versions.ui.register.get()))
                implementation(project(localModules.versions.data.login.get()))
                implementation(project(localModules.versions.data.register.get()))
                implementation(project(localModules.versions.feature.auth.components.get()))
                implementation(project(localModules.versions.core.di.get()))

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
    namespace = "com.just.cse.digital_diary.two_zero_two_three.auth.destination"
    compileSdk = 34
    defaultConfig {
        minSdk = 27
    }

}

