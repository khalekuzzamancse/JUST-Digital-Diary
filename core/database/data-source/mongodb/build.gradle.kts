import com.android.build.api.dsl.Packaging

plugins {
    alias(libs.plugins.convention.dataModulePlugin)
    alias(libs.plugins.kotlinxSerialization)
}
kotlin {

    sourceSets{
        val commonMain by getting{
            dependencies{
                implementation(projects.core.database.domain)


                //noinspection UseTomlInstead
                implementation("org.mongodb:mongodb-driver-kotlin-coroutine:5.2.0")
                //noinspection UseTomlInstead
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
    namespace = "core.database.datasource.monggodb"



}