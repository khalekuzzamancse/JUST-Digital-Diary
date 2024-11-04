plugins {
    alias(libs.plugins.convention.composeMultiplatfrom)
}
kotlin {

    sourceSets{
        val commonMain by getting{
            dependencies {

                implementation(projects.core.commonUi)
                implementation(projects.features.administration.domain)
                implementation(projects.features.administration.di)
                implementation(libs.common.viewmodel)
                implementation(libs.common.navigation)
            }
        }
        val androidMain by getting{
            dependencies {
//navigation graph define
                implementation(libs.androidx.navigation.compose)
            }
        }

    }


}
android {
    namespace = "administration.ui"


}