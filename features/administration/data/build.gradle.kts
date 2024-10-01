plugins {
    alias(libs.plugins.convention.dataModulePlugin)

}
kotlin {

    sourceSets{
        val commonMain by getting{
            dependencies {
                implementation(projects.core.network)
                implementation(projects.features.administration.domain)
                implementation(libs.common.viewmodel)
                implementation(libs.common.navigation)
            }
        }
        val commonTest by getting {
            dependencies{
                implementation(libs.kotlin.test)
                implementation(libs.kotlin.test.junit)
            }


        }

    }


}
android {
    namespace = "administration.data"
}