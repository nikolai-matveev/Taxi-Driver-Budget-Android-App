plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.hilt)
}

android {
    namespace = "ru.claus42.taxidriverbudget"
    compileSdk = 35

    defaultConfig {
        applicationId = "ru.claus42.taxidriverbudget"
        minSdk = 26
        targetSdk = 35
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

            kotlinOptions {
                freeCompilerArgs = listOf("-Xdebug")
            }

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            //TODO: add release signing config
            signingConfig = signingConfigs.getByName("debug")
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
    implementation(libs.bundles.coreImplementation)
    implementation(platform(libs.androidx.compose.bom))

    implementation(libs.bundles.implementation)
    debugImplementation(libs.bundles.debugImplementation)
    testImplementation(libs.bundles.testImplemetation)
    androidTestImplementation(libs.bundles.androidTestImplemetation)
    annotationProcessor(libs.bundles.annotation.processor.libraries)
    kapt(libs.bundles.kapt.libraries)
}