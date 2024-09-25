plugins {
    alias(libs.plugins.convention.kotlinMultiplatform)
}
kotlin {

    sourceSets{
        val commonMain by getting{
            dependencies {
//                implementation(project(localModules.versions.core.database.realm.get()))
//                implementation(project(localModules.versions.common.di.get()))
                implementation(projects.features.academic.domain)
                api(projects.features.academic.data)

            }
        }

    }


}
android {
    namespace = "faculty.di"

}
