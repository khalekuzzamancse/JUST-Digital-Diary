plugins {
    alias(libs.plugins.convention.domainModulePlugin)
}
kotlin {

    sourceSets{
        val commonMain by getting{
            dependencies {
                api(projects.core.commonDocs)//for common docs comment
                api(projects.core.customException)
            }
        }
      
    }



}
android {
    namespace = "domain"


}
