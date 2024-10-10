plugins {
    kotlin("jvm")
    application
    alias(libs.plugins.kotlinxSerialization)
}


dependencies {
    implementation(libs.bundles.ktor.server)
    implementation("org.mongodb:mongodb-driver-kotlin-coroutine:5.2.0")
    implementation("org.mongodb:bson-kotlinx:5.2.0")

}

application {
    mainClass.set("backend.Application")
}
