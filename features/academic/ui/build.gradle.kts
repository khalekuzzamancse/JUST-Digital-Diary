plugins {
    alias(libs.plugins.convention.composeMultiplatfrom)
}
kotlin {

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.common.ui)
                implementation(projects.features.academic.domain)
                implementation(projects.features.academic.di)
                implementation(libs.common.viewmodel)
                implementation(libs.common.navigation)
            }
        }

    }


}
android {
    namespace = "academic.ui"
}