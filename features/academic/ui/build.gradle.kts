plugins {
    alias(libs.plugins.convention.composeMultiplatfrom)
}
kotlin {

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.core.commonUi)
                implementation(projects.features.academic.domain)
                implementation(projects.features.academic.di)
                implementation(libs.common.viewmodel)
                implementation(libs.common.navigation)
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-swing:1.8.1")
            }
        }

    }


}
android {
    namespace = "academic.ui"
}