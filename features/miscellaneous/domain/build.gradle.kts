plugins {
    alias(libs.plugins.convention.domainModulePlugin)
}
kotlin {

    sourceSets{
        val commonMain by getting{
            dependencies {
                api(projects.core.customException)
            }
        }

    }


}
android {
    namespace = "miscellaneous.domain"

}