plugins {

    alias(libs.plugins.convention.composeMultiplatfrom)
}
kotlin {

    sourceSets{
        val commonMain by getting{
            dependencies {
                implementation(libs.common.navigation)
                implementation(libs.common.viewmodel)
                implementation(projects.common.ui)
                implementation(projects.features.academic.ui)
                implementation(projects.features.administration.ui)
                implementation(projects.features.auth.ui)
                implementation(projects.features.miscellaneous.ui)
                implementation(projects.features.notebook.ui)
                implementation(projects.features.queryservice.ui)
                implementation(projects.features.auth.di)
            }
        }
        val androidMain by getting{
            dependencies {
                implementation(libs.kotlinx.coroutines.android)
            }
        }

    }


}
android {
    namespace = "navigation"

}