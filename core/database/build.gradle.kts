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
                implementation(libs.room.runtime)
                implementation(libs.sqlite.bundled)
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
    namespace = "core.database"
}
room {
    schemaDirectory("$projectDir/schemas")
}

