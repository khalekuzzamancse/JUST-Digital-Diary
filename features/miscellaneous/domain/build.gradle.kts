plugins {
    alias(libs.plugins.convention.domainModulePlugin)
}
kotlin {

    sourceSets{
        val commonMain by getting{
            dependencies {
      

            }
        }

    }


}
android {
    namespace = "miscellaneous.domain"

}