plugins {
    alias(libs.plugins.convention.kotlinMultiplatform)
}
kotlin {

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.features.administration.domain)
                api(projects.features.administration.data)

            }
        }
    }

}
android {
    namespace = "administration.di"

}
