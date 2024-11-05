plugins {
    alias(libs.plugins.convention.dataModulePlugin)

}
kotlin {

    sourceSets {

        val commonMain by getting {
            dependencies {
                implementation(projects.core.data.api)
                implementation(projects.core.network)
                implementation(projects.features.academic.domain)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
                implementation(libs.kotlin.test.junit)
            }


        }

    }


}
android {
    namespace = "faculty.data"
}