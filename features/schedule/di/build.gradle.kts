plugins {
    alias(libs.plugins.convention.kotlinMultiplatform)
}
kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.features.schedule.domain)
                api(projects.features.schedule.data) //transitive dependency for inject implementation,
            }
        }
        val commonTest by getting {
            dependencies{
                implementation(libs.bundles.test)
                implementation(libs.kotlinx.coroutines.core)
            }
        }
    }

}
android {
    namespace = "schedule.di"
}