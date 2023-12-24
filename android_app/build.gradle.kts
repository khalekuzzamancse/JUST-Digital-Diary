//Android app module
plugins {
    alias(libs.plugins.androidApplication)
    kotlin("android")
    id("org.jetbrains.compose")
}

android {
    namespace = "com.just.cse.digitaldiaryremake.justdigitaldiary.twozeotwothree"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.just.cse.digitaldiaryremake.justdigitaldiary.twozeotwothree"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.6"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.activity.compose)
    implementation(compose.ui)
    implementation(compose.material3)
    implementation(compose.preview)
    implementation(compose.materialIconsExtended)
    val voyagerVersion = "1.0.0"

    // Multiplatform
    // Navigator
    implementation("cafe.adriel.voyager:voyager-navigator:$voyagerVersion")

    // Screen Model
    implementation("cafe.adriel.voyager:voyager-screenmodel:$voyagerVersion")

    // BottomSheetNavigator
    implementation("cafe.adriel.voyager:voyager-bottom-sheet-navigator:$voyagerVersion")

    // TabNavigator
    implementation("cafe.adriel.voyager:voyager-tab-navigator:$voyagerVersion")

    // Transitions
    implementation("cafe.adriel.voyager:voyager-transitions:$voyagerVersion")

    implementation(project(":shared"))
    implementation(project(":features:auth"))
}