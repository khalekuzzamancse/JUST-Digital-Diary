plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
}
//
kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }
    jvm("desktop") {
        jvmToolchain(17)
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.ui)
                implementation(compose.material3)
                implementation(compose.animation)
                implementation(compose.animationGraphics)
                implementation(compose.materialIconsExtended)
                implementation(project(modules.versions.common.ui.get()))
                implementation("dev.chrisbanes.material3:material3-window-size-class-multiplatform:0.3.1")
                implementation(project(modules.versions.domain.otherInfo.get()))
                implementation(project(modules.versions.ui.otherInfo.get()))
                implementation(project(modules.versions.data.otherInfo.get()))


            }
        }
//        val androidMain by getting{
//            dependencies {
//
//            }
//        }
        val desktopMain by getting {
            dependencies {
//coroutines
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-swing")
            }
        }
    }


}
android {
    namespace = "com.just.cse.digital_diary.two_zero_two_three.features.others"
    compileSdk = 34
    defaultConfig {
        minSdk = 27
    }

}