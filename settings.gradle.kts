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
            version("data-login", ":architecture_layers:data:login")
            version("ui-login", ":architecture_layers:ui:login")

            version("domain-register", ":architecture_layers:domain:register")
            version("data-register", ":architecture_layers:data:register")
            version("ui-register", ":architecture_layers:ui:register")
            //
            version("domain-faculties", ":architecture_layers:domain:faculties")
            version("data-faculties", ":architecture_layers:data:faculties")
            version("ui-faculties", ":architecture_layers:ui:faculties")

            //
            version("domain-departments", ":architecture_layers:domain:departments")
            version("data-departments", ":architecture_layers:data:departments")
            version("ui-departments", ":architecture_layers:ui:departments")
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
            //
            version("domain-adminOffices", ":architecture_layers:domain:admin_offices")
            version("data-adminOffices", ":architecture_layers:data:admin_offices")

            version("domain-adminSubOffices", ":architecture_layers:domain:admin_sub_offices")
            version("data-adminSubOffices", ":architecture_layers:data:admin_sub_offices")
            version("domain-adminOfficers", ":architecture_layers:domain:admin_officers")
            version("data-adminOfficers", ":architecture_layers:data:admin_officers")

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
    ":features:auth",
    ":features:root_home",
    ":features:faculty",
    ":features:department_info",
    ":features:departments",
    ":features:sharing_document",
    ":features:employee_list",
    ":features:admin_office",
)
val coreModules = listOf(
    ":core",
    ":core:di",
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