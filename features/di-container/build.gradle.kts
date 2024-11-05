plugins {
    alias(libs.plugins.convention.kotlinMultiplatform)
}
kotlin {
    sourceSets{
        val commonMain by getting{
            dependencies {
                implementation(projects.core.data.source.room)
            }
        }

    }

}
android {
    namespace = "global.dicontainer"
}
