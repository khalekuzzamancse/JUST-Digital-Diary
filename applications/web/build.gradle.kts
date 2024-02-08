plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}
kotlin {
    js(IR){
        browser()
        binaries.executable()
    }
    sourceSets{
        val jsMain by getting{
            dependencies {
                //implementation(project(":common"))
                implementation(compose.material3)
                implementation(compose.ui)
            }
        }
    }

}
compose.experimental {
    web.application {}
}