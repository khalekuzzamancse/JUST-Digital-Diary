import org.jetbrains.compose.desktop.application.dsl.TargetFormat
plugins {
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.kotlinMultiplatform)
}
kotlin {
    jvm {
   jvmToolchain(17)
        withJava()
    }
    sourceSets {

        val jvmMain by getting{
            dependencies {
                implementation(project(":features:navigation"))
                implementation(project(localModules.versions.feature.authentication.ui.get()))
                implementation(project(localModules.versions.feature.academic.ui.get()))
                implementation(project(localModules.versions.feature.administration.ui.get()))
                implementation(projects.core.commonUi)
//                implementation(project(localModules.versions.feature.notebook.ui.get()))
                implementation(project(localModules.versions.feature.miscellaneous.ui.get()))
                implementation(projects.features.academicCalender.ui)

                implementation(compose.desktop.common)
                implementation(compose.desktop.currentOs)


            }
        }
    }


}
compose.desktop {
    application {
        mainClass = "DesktopMainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Exe)
            packageName = "justdiarydesktop"
            version = "1.0.0"
        }
    }
}