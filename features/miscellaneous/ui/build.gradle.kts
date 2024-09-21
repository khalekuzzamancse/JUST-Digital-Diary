plugins {
    alias(libs.plugins.convention.composeMultiplatfrom)
}
kotlin {

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.common.navigation)
                implementation(libs.common.viewmodel)
                implementation(projects.common.ui)
                implementation(projects.features.miscellaneous.domain)
                implementation(projects.features.miscellaneous.di)
                implementation(projects.features.academicCalender.ui)
            }
        }

    }


}
android {
    namespace = "miscellaneous.ui"

}
dependencies {
    implementation(project(":features:academic-calender:ui"))
}
