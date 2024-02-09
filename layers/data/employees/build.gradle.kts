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
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlinx.coroutines.core)
                implementation(project(localModules.versions.domain.employees.get()))
            }
        }
    }


}
android {
    namespace = "com.just.cse.digital_diary.two_zero_two_three.layers.data.employees"
    compileSdk = 34
    defaultConfig {
        minSdk = 27
    }

}
