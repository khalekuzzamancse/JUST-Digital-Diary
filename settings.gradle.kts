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
            version("common-ui", ":layers:ui:common_ui")
            version("domain-login", ":layers:domain:login")
            version("data-login", ":layers:data:login")
            version("ui-login", ":layers:ui:login")
            //

            version("domain-departmentInfo", ":layers:domain:department_info")
            version("data-departmentInfo", ":layers:data:department_info")
            //
            version("domain-teachers", ":layers:domain:teachers")
            version("data-teachers", ":layers:data:teachers")
            version("ui-teachers", ":layers:ui:teachers")
            //
            version("domain-otherInfo", ":layers:domain:other_info")
            version("data-otherInfo", ":layers:data:other_info")
            version("ui-otherInfo", ":layers:ui:other_info")


        }
    }
}


val dataLayerModules = listOf(
    ":layers:data",
    ":layers:data:login",
    ":layers:data:register",
    ":layers:data:faculties",
    ":layers:data:departments",
    ":layers:data:department_info",
    ":layers:data:teachers",
    ":layers:data:admin_officers",
    ":layers:data:admin_offices",
    ":layers:data:admin_sub_offices",
    ":layers:data:other_info",


)
val domainLayerModules  = dataLayerModules
    .map { it.replace(":data:", ":domain:") }

val uiLayerModules = dataLayerModules
    .map { it.replace(":data:", ":ui:") }
val featureModules = listOf(
    ":features",
    ":features:auth",":features:auth:functionalities",":features:auth:navgraph",
    ":features:navigation",
    ":features:faculty",":features:faculty:navgraph",":features:faculty:functionalities",
    ":features:others",":features:others:functionalities",":features:others:navgraph",
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
val applications= listOf(
    ":applications",  ":applications:android_app", ":applications:desktop", ":applications:web",
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
include(uiLayerModules)