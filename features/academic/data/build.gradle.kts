plugins {
    alias(libs.plugins.convention.dataModulePlugin)

}
kotlin {

    sourceSets{

        val commonMain by getting{
            dependencies {
                implementation(projects.core.database.realm)
                implementation(projects.core.network)
                implementation(projects.features.academic.domain)
                implementation(projects.common.di)//to retrieve token
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
    namespace = "faculty.data"
}