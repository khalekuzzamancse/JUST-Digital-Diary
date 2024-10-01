plugins {
    alias(libs.plugins.convention.kotlinMultiplatform)
}
kotlin {
    sourceSets{
        val commonMain by getting{
            dependencies {
                implementation(projects.features.auth.domain)
                api(projects.features.auth.data)

            }
        }

    }

}
android {
    namespace = "auth.di"
}
