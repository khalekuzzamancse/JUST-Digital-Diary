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
        val commonMain by getting {
            dependencies {

                implementation(project(localModules.versions.common.ui.get()))
                implementation(project(localModules.versions.ui.login.get()))
                implementation(project(localModules.versions.ui.register.get()))
                api(project(localModules.versions.domain.login.get()))
                api(project(localModules.versions.domain.register.get()))

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
    namespace = "com.just.cse.digital_diary.two_zero_two_three.auth"
    compileSdk = 34
    defaultConfig {
        minSdk = 27
    }

}


