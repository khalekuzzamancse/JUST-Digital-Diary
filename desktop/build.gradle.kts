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
                implementation(project(":shared"))
                implementation(project(":features:auth"))
                implementation(compose.desktop.common)
                implementation(compose.desktop.currentOs)
                val voyagerVersion = "1.0.0"

                // Multiplatform
                // Navigator
                implementation("cafe.adriel.voyager:voyager-navigator:$voyagerVersion")

                // Screen Model
                implementation("cafe.adriel.voyager:voyager-screenmodel:$voyagerVersion")

                // BottomSheetNavigator
                implementation("cafe.adriel.voyager:voyager-bottom-sheet-navigator:$voyagerVersion")

                // TabNavigator
                implementation("cafe.adriel.voyager:voyager-tab-navigator:$voyagerVersion")

                // Transitions
                implementation("cafe.adriel.voyager:voyager-transitions:$voyagerVersion")
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