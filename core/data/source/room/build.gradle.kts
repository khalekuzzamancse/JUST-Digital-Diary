plugins {
    alias(libs.plugins.convention.dataModulePlugin)
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
    alias(libs.plugins.jetbrainsCompose)//for accessing context android
}
kotlin {

    sourceSets{
        val commonMain by getting{
            dependencies {
                implementation(projects.core.data.domain)
                implementation(libs.bundles.room)
                implementation(compose.ui) //for accessing context android
            }
        }

        val commonTest by getting {
            dependencies{
                implementation(libs.bundles.test)
            }


        }
        val androidMain by getting{
            dependencies{

            }
        }

    }


}
android {
    namespace = "core.roomdb"
}
room {
    schemaDirectory("$projectDir/schemas")
}
dependencies {
    ksp(libs.room.compiler)
}
