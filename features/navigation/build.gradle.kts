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
                implementation(compose.ui)
                implementation(compose.material3)
                implementation(compose.animation)
                implementation(compose.animationGraphics)
                implementation(compose.materialIconsExtended)
                implementation(compose.preview)
                implementation("dev.chrisbanes.material3:material3-window-size-class-multiplatform:0.3.1")
                implementation(project(modules.versions.common.ui.get()))
                implementation(project(":features:faculty"))
//                implementation(project(":features:sharing_document"))
//                implementation(project(":features:department_info"))
                implementation(project(":features:auth"))
//                implementation(project(":features:employee_list"))
//                implementation(project(":features:admin_office"))
                implementation(project(modules.versions.domain.otherInfo.get()))
                implementation(project(modules.versions.ui.otherInfo.get()))
                implementation(project(modules.versions.data.otherInfo.get()))


//                implementation(project(modules.versions.domain.adminOfficers.get()))
//                implementation(project(modules.versions.ui.adminOfficers.get()))
//                implementation(project(modules.versions.data.adminOfficers.get()))
//                //
//                implementation(project(modules.versions.domain.adminOffices.get()))
//                implementation(project(modules.versions.ui.adminOffices.get()))
//                implementation(project(modules.versions.data.adminOffices.get()))


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