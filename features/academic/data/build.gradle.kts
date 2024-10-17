plugins {
    alias(libs.plugins.convention.dataModulePlugin)

}
kotlin {

    sourceSets {

        val commonMain by getting {
            dependencies {
                implementation(projects.core.database.api)
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