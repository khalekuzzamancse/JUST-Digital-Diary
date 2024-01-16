pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")

    }
}
dependencyResolutionManagement {
//    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

rootProject.name = "JUST Digital Diary"
include(":android_app")
include(":common")
include(":desktop")
include(":shared")
include(":web")
include(":features")
include(":data_layer")
include(":features:auth")
include("common_ui")
include(":features:root_home")
include(":features:faculty")
include(":data_layer:repository")
include(":features:department_info",":features:departments")
include(":features:sharing_document")
