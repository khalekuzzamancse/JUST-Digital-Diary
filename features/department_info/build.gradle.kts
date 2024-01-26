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
                implementation(compose.preview)
                implementation(project(modules.versions.common.ui.get()))
                implementation(project(modules.versions.data.teachers.get()))
                implementation(project(modules.versions.domain.teachers.get()))
                implementation(project(modules.versions.ui.teachers.get()))
                implementation("dev.chrisbanes.material3:material3-window-size-class-multiplatform:0.3.1")

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
    }


}
android {
    namespace = "com.just.cse.digital_diary.two_zero_two_three.department"
    compileSdk = 34
    defaultConfig {
        minSdk = 27
    }

}