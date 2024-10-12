pluginManagement {
    includeBuild("build-logic")//build-logic as a Composite Build, for convention plugin
    repositories {
        //google()
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositories {
     //   google()
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }

}


/*
 * Type-Safe Project Accessors, a feature introduced in Gradle 7.0 that allows you to reference project dependencies
 * in a type-safe manner without relying on string-based project paths like project(":x") as implement(projects.x)
 */
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")


val applications= listOf(
    ":applications",  ":applications:android", ":applications:desktop",
    //":applications:web",
)
//val common= listOf(":common",":common:data",":common:ui",":common:docs","common:di")
val coreModules = listOf(
    ":core",
    ":core:network",
    ":core:database",":core:database:mongodb",":core:database:api",
    ":core:common-ui",
    ":core:common-docs",
)

val featureModules = listOf(
    ":features",
    ":features:auth", ":features:auth:data",  ":features:auth:domain",":features:auth:ui",":features:auth:di",
    ":features:academic", ":features:academic:domain",":features:academic:data",":features:academic:ui",":features:academic:di",
    ":features:administration",":features:administration:data", ":features:administration:domain", ":features:administration:ui", ":features:administration:di",
    ":features:navigation",
    ":features:miscellaneous", ":features:miscellaneous:domain",":features:miscellaneous:data",":features:miscellaneous:ui",":features:miscellaneous:di",
    //":features:notebook",":features:notebook:domain",":features:notebook:data",":features:notebook:ui",":features:notebook:di",
//    ":features:queryservice",  ":features:queryservice:domain",":features:queryservice:data",":features:queryservice:ui",":features:queryservice:di",
    ":features:academic-calender",":features:academic-calender:domain",":features:academic-calender:data",":features:academic-calender:ui",":features:academic-calender:di",
    ":features:schedule",":features:schedule:domain",":features:schedule:data",":features:schedule:ui",":features:schedule:di",
    ":features:profile",":features:profile:domain",":features:profile:data",":features:profile:ui",":features:profile:di",

)


rootProject.name = "JUSTDigitalDiary"//do not use whitespace,it may causes build error or DEX
include(applications+coreModules+featureModules)
include(":backend")