plugins {
    alias(libs.plugins.convention.domainModulePlugin)
}
kotlin {

    sourceSets{
        val commonMain by getting{
            dependencies {
                implementation(projects.core.commonDocs)//for common docs comment
                api(projects.core.customException)//for common custom exception
            }
        }
      
    }



}
android {
    namespace = "faculty.domain"


}
