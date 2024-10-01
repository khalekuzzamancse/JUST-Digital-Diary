plugins {
    alias(libs.plugins.convention.composeMultiplatfrom)
}
kotlin {

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.core.commonUi)
                implementation(projects.features.profile.domain)
                implementation(projects.features.profile.di)
                implementation(libs.common.viewmodel)
                implementation(libs.common.navigation)
            }
        }

    }


}
android {
    namespace = "profile"
}