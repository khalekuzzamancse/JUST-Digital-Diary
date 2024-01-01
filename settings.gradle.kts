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
include(":features:auth")
include(":features:common_ui")
include(":features:root_home")
include(":features:faculty")
include(":features:repository")
include(":features:department")
