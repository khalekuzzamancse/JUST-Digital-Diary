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
                implementation(compose.desktop.common)
                implementation(compose.desktop.currentOs)
                implementation(libs.kotlinx.coroutines.swing)
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