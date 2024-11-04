plugins {
    alias(libs.plugins.convention.composeMultiplatfrom)
}
kotlin {
    sourceSets{
        val commonMain by getting{
            dependencies {
                implementation(projects.features.academicCalender.domain)
                implementation(projects.features.academicCalender.di)
                implementation(projects.core.commonUi)
                implementation(projects.core.network)
            }
        }
        val commonTest by getting {
            dependencies{
                implementation(libs.bundles.test)
            }
        }
    }

}
android {
    namespace = "calendar.ui"
}