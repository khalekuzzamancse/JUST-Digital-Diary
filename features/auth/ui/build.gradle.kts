plugins {
    alias(libs.plugins.convention.composeMultiplatfrom)
}
kotlin {

    sourceSets{
        val commonMain by getting{
            dependencies {
                implementation(projects.core.commonUi)
                implementation(projects.features.auth.domain)
                implementation(projects.features.auth.di)
                implementation(libs.common.viewmodel)
                implementation(libs.common.navigation)
                implementation(projects.core.commonDocs)
            }
        }

    }


}
android {
    namespace = "auth"

}