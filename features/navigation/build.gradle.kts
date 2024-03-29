plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
}
kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }
    jvm("desktop"){
        jvmToolchain(17)
    }
    sourceSets{
        val commonMain by getting{
            dependencies {
                implementation(project(localModules.versions.common.ui.get()))
                implementation(project(localModules.versions.feature.auth.destination.get()))
                implementation(project(localModules.versions.feature.faculty.destination.get()))
                implementation(project(localModules.versions.feature.adminOffice.destination.get()))
                implementation(project(localModules.versions.feature.others.destination.get()))
                implementation(project(localModules.versions.core.di.get()))
                implementation(project(localModules.versions.feature.notes.navgraph.get()))
                implementation(project(localModules.versions.feature.search.navgraph.get()))
                implementation(libs.kotlinx.coroutines.core)

                //debugging
               // implementation(project(localModules.versions.feature.search.functionalities.get()))


            }
        }
        val androidMain by getting{
            dependencies {
                implementation(libs.kotlinx.coroutines.android)
                implementation("androidx.navigation:navigation-compose:2.7.6")


            }
        }
        val desktopMain by getting{
            dependencies {
                implementation(libs.voyager.navigator)
                implementation(libs.voyager.transitions)
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-swing")

            }
        }
    }


}
android {
    namespace = "com.just.cse.digital_diary.two_zero_two_three.root_home"
    compileSdk = 34
    defaultConfig {
        minSdk = 27
    }

}