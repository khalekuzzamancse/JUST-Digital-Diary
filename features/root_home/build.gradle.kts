plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
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
                implementation(compose.ui)
                implementation(compose.material3)
                implementation(compose.animation)
                implementation(compose.animationGraphics)
                implementation(compose.materialIconsExtended)
                implementation(compose.preview)
                implementation("dev.chrisbanes.material3:material3-window-size-class-multiplatform:0.3.1")
                implementation(libs.voyager.navigator)
                implementation(libs.voyager.transitions)
                implementation(libs.ktor.serialization.kotlinx.json)


                implementation(project(":common_ui"))
                implementation(project(":data_layer:repository"))
                implementation(project(":features:faculty"))
                implementation(project(":features:sharing_document"))
                implementation(project(":features:department_info"))
                implementation(project(":features:auth"))
                implementation(project(":features:employee_list"))
                implementation(project(":features:event_gallery"))
            }
        }
//        val androidMain by getting{
//            dependencies {
//
//            }
//        }
        val desktopMain by getting{
            dependencies {

            }
        }
    }


}
android {
    namespace = "com.just.cse.digital_diary.two_zero_two_three.root_home"
    compileSdk = 34
    defaultConfig {
        minSdk = 27
    }

}