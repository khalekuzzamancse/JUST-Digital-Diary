plugins {
    alias(libs.plugins.convention.dataModulePlugin)
}
kotlin {

    sourceSets{

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