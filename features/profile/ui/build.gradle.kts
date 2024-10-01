plugins {
    alias(libs.plugins.convention.composeMultiplatfrom)
}
kotlin {

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.core.commonUi)
                implementation(projects.features.profile.domain)
                implementation(projects.features.profile.di)
                implementation(libs.common.viewmodel)
                implementation(libs.common.navigation)
                //
                implementation(projects.features.academic.ui)
                implementation(projects.features.schedule.ui)
                implementation(projects.features.academicCalender.ui)
            }
        }

        jvmMain {
            dependencies {
                implementation(libs.kotlinx.coroutines.swing)
            }

        }

    }


}
android {
    namespace = "profile"
}