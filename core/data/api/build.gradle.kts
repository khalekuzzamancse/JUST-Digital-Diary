plugins {
    alias(libs.plugins.convention.dataModulePlugin)
    alias(libs.plugins.kotlinxSerialization)
}
kotlin {

    sourceSets{
        val commonMain by getting {
            dependencies{
                //TODO:Right Now mongo causes when build apk/exe so disconnect it
                //implementation(projects.core.data.source.mongodb)
                implementation(projects.core.data.source.room)
                implementation(projects.core.data.source.server)
                api(projects.core.data.entity)
                implementation(projects.core.data.domain)
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