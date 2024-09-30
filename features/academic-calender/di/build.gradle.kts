plugins {
    alias(libs.plugins.convention.kotlinMultiplatform)
}
kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.features.academicCalender.domain)
                api(projects.features.academicCalender.data) //transitive dependency for inject implementation,
            }
        }
    }

}
android {
    namespace = "di"
}