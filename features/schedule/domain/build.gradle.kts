plugins {
    alias(libs.plugins.convention.domainModulePlugin)
}
kotlin {
    sourceSets{
        val commonMain by getting{
            dependencies {
                implementation(projects.core.commonDocs)//for common docs comment
                api(projects.core.customException)
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
    namespace = "schedule.domain"
}