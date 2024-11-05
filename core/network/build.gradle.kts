plugins {
    alias(libs.plugins.convention.dataModulePlugin)
}
kotlin {

    sourceSets{

        val commonMain by getting {
            dependencies {
                api(projects.core.customException)

            }
        }
        val commonTest by getting {
            dependencies{
                implementation(libs.bundles.test)
            }


        }
    }


}
android {
    namespace = "core.network"

}