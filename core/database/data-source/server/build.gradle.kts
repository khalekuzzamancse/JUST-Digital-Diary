plugins {
    alias(libs.plugins.convention.dataModulePlugin)
    alias(libs.plugins.kotlinxSerialization)
}
kotlin {

    sourceSets{
        val commonMain by getting {
            dependencies{
                implementation(projects.core.database.domain)
                implementation(projects.core.customException)
                implementation(projects.core.network)
                implementation(projects.core.commonDocs)
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
    namespace = "core.database.server"
}