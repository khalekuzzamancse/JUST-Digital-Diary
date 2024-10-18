plugins {
    kotlin("jvm")
    application
    alias(libs.plugins.kotlinxSerialization)
}
dependencies {
    implementation(libs.bundles.ktor.server)
    implementation(projects.core.database.api)
}
application {
    mainClass.set("server.Application")
}