plugins {
    alias(libs.plugins.convention.dataModulePlugin)
    alias(libs.plugins.kotlinxSerialization)
}
kotlin {

    sourceSets{
        val commonMain by getting {
            dependencies{
                api(projects.core.database.entity)
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
    namespace = "domain"

}