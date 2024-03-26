import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}
kotlin {
   jvm{
       jvmToolchain(17)
       withJava()
   }
    sourceSets{
        val jvmMain by getting{
            dependencies {
                implementation(project(":features:navigation"))
                implementation(project(localModules.versions.feature.authentication.ui.get()))
                implementation(project(localModules.versions.common.ui.get()))
                implementation(compose.desktop.common)
                implementation(compose.desktop.currentOs)


            }
        }
    }


}
compose.desktop{
    application{
        mainClass="DesktopMainKt"
        nativeDistributions{
            targetFormats(TargetFormat.Exe)
            packageName="justdiarydesktop"
            version="1.0.0"
        }
    }
}