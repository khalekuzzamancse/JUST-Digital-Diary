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



val applications= listOf(
    ":applications",  ":applications:android", ":applications:desktop", ":applications:web",
)
val common= listOf(":common",":common:data",":common:ui",":common:domain","common:di")
val coreModules = listOf(
    ":core",
    ":core:network",
    ":core:database",
    ":core:database:realm"
)

val featureModules = listOf(
    ":features",
    ":features:auth", ":features:auth:data",  ":features:auth:domain",":features:auth:ui",":features:auth:di",
    ":features:academic", ":features:academic:domain",":features:academic:data",":features:academic:ui",":features:academic:di",
    ":features:administration",":features:administration:data", ":features:administration:domain", ":features:administration:ui", ":features:administration:di",
    ":features:navigation",
    ":features:miscellaneous", ":features:miscellaneous:domain",":features:miscellaneous:data",":features:miscellaneous:ui",":features:miscellaneous:di",
    ":features:notebook",":features:notebook:domain",":features:notebook:data",":features:notebook:ui",":features:notebook:di",
    ":features:queryservice",  ":features:queryservice:domain",":features:queryservice:data",":features:queryservice:ui",":features:queryservice:di",
)


//rootProject.name = "JUSTDigitalDiary"//do not use whitespace,it may causes build error or DEX
include(applications+coreModules+common+featureModules)