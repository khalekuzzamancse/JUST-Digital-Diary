plugins {
    kotlin("multiplatform")
    id("com.android.library")

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
    }


}
android {
    namespace =  "com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_sub_offices"
    compileSdk = 34
    defaultConfig {
        minSdk = 27
    }

}