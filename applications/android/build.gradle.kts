//Android app module
plugins {
    alias(libs.plugins.androidApplication)
    kotlin("android")
    id("org.jetbrains.compose")
}

android {
    namespace = "com.just_diary"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.just_diary"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

        }
    }
    compileOptions {
        sourceCompatibility =  JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.8"
    }
    packaging {

        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
          //  merges+="META-INF/native-image/org.mongodb/bson/native-image.properties"
            merges+="/META-INF/native-image/native-image.properties"
            merges+="/META-INF/native-image/reflect-config.json"


        }
    }
}

dependencies {
    implementation(libs.androidx.activity.compose)
    implementation(compose.ui)
    implementation(compose.material3)
    implementation(compose.materialIconsExtended)
    implementation(libs.kotlinx.coroutines.android)
    implementation (libs.androidx.datastore.preferences)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.common.viewmodel)
    implementation(project(":features:navigation"))
    implementation(projects.core.data.api)


}