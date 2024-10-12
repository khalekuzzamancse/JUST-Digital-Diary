plugins {
    alias(libs.plugins.convention.dataModulePlugin)
    alias(libs.plugins.kotlinxSerialization)
}
kotlin {

    sourceSets{
        val commonMain by getting {
            dependencies{
                implementation(projects.core.database.mongodb)
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
    namespace = "core.database.api"

}