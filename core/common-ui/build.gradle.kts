plugins {
    alias(libs.plugins.convention.composeMultiplatfrom)
    alias(libs.plugins.kotlinxSerialization)
}
kotlin {
    sourceSets{
        val commonMain by getting{
            dependencies {
                implementation(libs.bundles.coil)
                implementation(libs.bundles.ktorClient)
                implementation(libs.ktor.serialization.kotlinx.json)
                implementation(compose.components.resources)
                implementation(libs.common.navigation)//For defining animation for navigation


            }

        }

    }


}
android {
    namespace = "common.ui"
}

