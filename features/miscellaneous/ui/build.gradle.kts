plugins {
    alias(libs.plugins.convention.composeMultiplatfrom)
}
kotlin {

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(localModules.versions.common.ui.get()))
                implementation(project(localModules.versions.feature.miscellaneous.domain.get()))
                implementation(project(localModules.versions.feature.miscellaneous.di.get()))
                implementation(projects.features.academicCalender.ui)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.androidx.navigation.compose)//navigation graph define
            }
        }
        val desktopMain by getting {
            dependencies {

            }
        }
    }


}
android {
    namespace = "miscellaneous.ui"

}