pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }

    versionCatalogs {
        create("localModules") {
            from(files("gradle/localModules.versions.toml"))
        }
    }
}


val dataLayerModules = listOf(
    ":layers:data",
    ":layers:data:login",
    ":layers:data:register",
    ":layers:data:admin_officers",
    ":layers:data:admin_offices",
    ":layers:data:admin_sub_offices",
    ":layers:data:other_info",
    ":layers:data:notes",
    ":layers:data:employees",

)
val domainLayerModules  = dataLayerModules
    .map { it.replace(":data:", ":domain:") }
val domains= listOf(  ":layers:domain:notes",":layers:domain:employees")

val uiLayerModules = dataLayerModules
    .map { it.replace(":data:", ":ui:") }
val featureModules = listOf(
    ":features",
    ":features:authentication", ":features:authentication:data",  ":features:authentication:domain",":features:authentication:ui",":features:authentication:di",
    ":features:faculty_info", ":features:faculty_info:domain",":features:faculty_info:data",":features:faculty_info:ui",":features:faculty_info:di",
    ":features:auth",":features:auth:functionalities",":features:auth:navgraph",
    ":features:navigation",
    ":features:others",":features:others:functionalities",":features:others:navgraph",
    ":features:notes",  ":features:notes:functionalities",  ":features:notes:navgraph",
    ":features:search", ":features:search:functionalities", ":features:search:navgraph",
    ":features:admin_office",  ":features:admin_office:functionalities",  ":features:admin_office:navgraph",
)
val coreModules = listOf(
    ":core",
    ":core:network",
    ":core:di",
    ":core:database",
    ":core:database:realm"
)
val applications= listOf(
    ":applications",  ":applications:android", ":applications:desktop", ":applications:web",
)

rootProject.name = "JUST Digital Diary"
include(applications)
include(":common")
include(":layers")
include(":layers:ui:common_ui")
include(featureModules)
include(coreModules)
include(dataLayerModules)
include(domainLayerModules)
include(domains)
include(uiLayerModules)