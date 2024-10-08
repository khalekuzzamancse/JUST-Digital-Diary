plugins {
    kotlin("jvm")
    application
    alias(libs.plugins.kotlinxSerialization)
}


dependencies {
    implementation(libs.bundles.ktor.server)
}

application {
    mainClass.set("backend.Application")
}
