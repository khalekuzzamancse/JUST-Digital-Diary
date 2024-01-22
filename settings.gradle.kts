pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
//        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")

    }
}
dependencyResolutionManagement {
//    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
//        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}
val featureModules = listOf(
    ":features",
    ":features:auth",
    ":features:root_home",
    ":features:faculty",
    ":features:department_info",
    ":features:departments",
    ":features:sharing_document",
    ":features:employee_list",
    ":features:event_gallery",
    ":features:admin_office",
)
val coreModules = listOf(
    ":core",
    ":core:data_layer",
    ":core:data_layer:repository",
    ":core:network",
    

)

rootProject.name = "JUST Digital Diary"
include(":android_app")
include(":common")
include(":desktop")
include(":shared")
include(":web")
include(":data_layer")
include("common_ui")
include(featureModules)
include(coreModules)