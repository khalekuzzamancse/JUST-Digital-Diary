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
    versionCatalogs {
        create("modules") {
            version("common-ui", ":architecture_layers:ui:common_ui")
            version("domain-login", ":architecture_layers:domain:login")
        }
    }
}


val dataLayerModules = listOf(
    ":architecture_layers:data",
    ":architecture_layers:data:login",
    ":architecture_layers:data:register",
    ":architecture_layers:data:faculties",
    ":architecture_layers:data:departments",
    ":architecture_layers:data:department_info",
    ":architecture_layers:data:teachers",
    ":architecture_layers:data:admin_officers"

)
val domainLayerModules  = dataLayerModules
    .map { it.replace(":data:", ":domain:") }

val uiLayerModules = dataLayerModules
    .map { it.replace(":data:", ":ui:") }
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
    ":core:local_database",
    ":core:local_database:sql_delight",


    )

rootProject.name = "JUST Digital Diary"
include(":android_app")
include(":common")
include(":desktop")
include(":shared")
include(":web")
//include(":data_layer")
//include(":ui_layer")
//include(":domain_layer")
//include(":ui_layer")
//include(":data_layer")
include(":architecture_layers")
include(":architecture_layers:ui:common_ui")
include(featureModules)
include(coreModules)
include(dataLayerModules)
include(domainLayerModules)
include(uiLayerModules)