plugins {
    alias(libs.plugins.convention.dataModulePlugin)
}
kotlin {

    sourceSets{
        val commonMain by getting{
            dependencies {
                //network IO
//                implementation(libs.ktor.client.core)
//                implementation(libs.ktor.client.okhttp)
//                implementation(libs.ktor.client.content.negotiation)
//                implementation(libs.ktor.serialization.kotlinx.json)
//                implementation(libs.kotlinx.coroutines.core)


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
    namespace = "core.network"

}