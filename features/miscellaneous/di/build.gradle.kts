plugins {
    alias(libs.plugins.convention.kotlinMultiplatform)
}
kotlin {

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.features.miscellaneous.domain)
                api(projects.features.miscellaneous.data)

            }
        }


    }
}
android {
    namespace = "miscellaneous.di"

}
