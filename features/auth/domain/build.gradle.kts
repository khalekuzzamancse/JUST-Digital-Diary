plugins {
    alias(libs.plugins.convention.domainModulePlugin)
}
kotlin {
    sourceSets{
        val commonMain by getting{
            dependencies {
                implementation(projects.common.docs)//for common docs comment
            }
        }
    }

}
android {
    namespace = "auth.domain"
}