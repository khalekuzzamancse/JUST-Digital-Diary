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
        create("localModules") {
            from(files("gradle/localModules.versions.toml"))
        }
        create("modules") {
            version("common-ui", ":architecture_layers:ui:common_ui")
            version("domain-login", ":architecture_layers:domain:login")
            version("data-login", ":architecture_layers:data:login")
            version("ui-login", ":architecture_layers:ui:login")
            //

            version("domain-departmentInfo", ":architecture_layers:domain:department_info")
            version("data-departmentInfo", ":architecture_layers:data:department_info")
            //
            version("domain-teachers", ":architecture_layers:domain:teachers")
            version("data-teachers", ":architecture_layers:data:teachers")
            version("ui-teachers", ":architecture_layers:ui:teachers")
            //
            version("domain-otherInfo", ":architecture_layers:domain:other_info")
            version("data-otherInfo", ":architecture_layers:data:other_info")
            version("ui-otherInfo", ":architecture_layers:ui:other_info")


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
    ":architecture_layers:data:admin_officers",
    ":architecture_layers:data:admin_offices",
    ":architecture_layers:data:admin_sub_offices",
    ":architecture_layers:data:other_info",


)
val domainLayerModules  = dataLayerModules
    .map { it.replace(":data:", ":domain:") }

val uiLayerModules = dataLayerModules
    .map { it.replace(":data:", ":ui:") }
val featureModules = listOf(
    ":features",
    ":features:auth",":features:auth:components",":features:auth:destination",
    ":features:navigation",
    ":features:faculty",":features:faculty:navgraph",":features:faculty:functionalities",
    ":features:others",":features:others:components",":features:others:destination",
    ":features:sharing_document",
    ":features:employee_list",
    ":features:admin_office",  ":features:admin_office:functionalities",  ":features:admin_office:navgraph"
)
val coreModules = listOf(
    ":core",
    ":core:network",
    ":core:di",
    ":core:local_database",
    ":core:local_database:realm"
)

rootProject.name = "JUST Digital Diary"
include(":android_app")
include(":common")
include(":desktop")
include(":shared")
include(":web")
include(":architecture_layers")
include(":architecture_layers:ui:common_ui")
include(featureModules)
include(coreModules)
include(dataLayerModules)
include(domainLayerModules)
include(uiLayerModules)