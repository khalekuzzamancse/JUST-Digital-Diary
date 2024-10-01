plugins {
    alias(libs.plugins.convention.kotlinMultiplatform)
}
kotlin {

    sourceSets{
        val commonMain by getting{
            dependencies {
//                implementation(project(localModules.versions.core.database.realm.get()))
//                implementation(project(localModules.versions.common.di.get()))
                implementation(projects.features.profile.domain)
                api(projects.features.profile.data)

            }
        }

    }


}
android {
    namespace = "di"

}
