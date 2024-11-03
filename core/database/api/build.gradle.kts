plugins {
    alias(libs.plugins.convention.dataModulePlugin)
    alias(libs.plugins.kotlinxSerialization)
}
kotlin {

    sourceSets{
        val commonMain by getting {
            dependencies{
                implementation(projects.core.database.dataSource.mongodb)
                implementation(projects.core.database.dataSource.room)
                implementation(projects.core.database.domain)
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
    namespace = "core.database.api"

}