plugins {
    kotlin("multiplatform")
    id("com.android.library")
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
                implementation(project(":core:network"))
                implementation(libs.ktor.serialization.kotlinx.json)
                implementation(project(modules.versions.domain.adminOfficers.get()))
                implementation(project(modules.versions.domain.otherInfo.get()))
            }
        }
    }


}
android {
    namespace = "com.just.cse.digital_diary.two_zero_two_three.data_layer.other_info"
    compileSdk = 34
    defaultConfig {
        minSdk = 27
    }

}