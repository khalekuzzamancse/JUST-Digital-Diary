plugins {
    alias(libs.plugins.convention.dataModulePlugin)
    alias(libs.plugins.kotlinxSerialization)
}
kotlin {

    sourceSets{
        val commonMain by getting{
            dependencies{
                implementation("org.mongodb:mongodb-driver-kotlin-coroutine:5.2.0")
                implementation("org.mongodb:bson-kotlinx:5.2.0")
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
    namespace = "core.database.monggodb"

}