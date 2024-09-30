plugins {
    alias(libs.plugins.convention.dataModulePlugin)
}
kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.core.network)
                implementation(projects.features.auth.domain)

            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.bundles.test)
            }

        }

    }


}
android {
    namespace = "auth.data"

}