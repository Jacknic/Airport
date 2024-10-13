plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.jacknic.android.airport"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.jacknic.android.airport"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            signingConfig = signingConfigs["debug"]
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
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(androidx.core.coreKtx)
    implementation(androidx.lifecycle.lifecycleRuntimeKtx)
    implementation(androidx.activity.activityCompose)

    implementation(androidx.composeUi.ui)
    implementation(androidx.composeUi.uiToolingPreview)
    implementation(androidx.composeMaterial3.material3)
    implementation(androidx.navigation.navigationCompose)
    implementation(androidx.composeMaterial3Adaptive.adaptiveNavigation)

    testImplementation(libs.junit)
    androidTestImplementation(androidx.testExt.junit)
    androidTestImplementation(androidx.testEspresso.espressoCore)
    androidTestImplementation(platform(androidx.compose.composeBom))
    androidTestImplementation(androidx.composeUi.uiTestJunit4)
    debugImplementation(androidx.composeUi.uiTooling)
    debugImplementation(androidx.composeUi.uiTestManifest)
}