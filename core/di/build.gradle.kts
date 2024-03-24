plugins {
    kotlin("multiplatform")
    id("com.android.library")
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
                implementation(libs.kotlinx.coroutines.core)
                implementation(project(localModules.versions.core.database.realm.get()))
                api(project(localModules.versions.data.login.get()))
                api(project(localModules.versions.data.register.get()))
                api(project(localModules.versions.data.otherInfo.get()))

                //Feature:Admin office
                api(project(localModules.versions.data.admin.offices.get()))
                api(project(localModules.versions.data.admin.subOffices.get()))
                api(project(localModules.versions.data.admin.officers.get()))
                //Feature notes
                api(project(localModules.versions.data.notes.get()))
                //Feature employees
                api(project(localModules.versions.data.employees.get()))

            }
        }
        val androidMain by getting{
            dependencies {


            }
        }
        val desktopMain by getting{
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-swing")
            }
        }
    }
    //to use expect and actual keywords
    kotlin {
        compilerOptions {
            // Common compiler options applied to all Kotlin source sets
            freeCompilerArgs.add("-Xmulti-platform")
        }
    }


}
android {
    namespace = "com.just.cse.digitaldiary.twozerotwothree.core.di"
    compileSdk = 34
    defaultConfig {
        minSdk = 27
    }

}
