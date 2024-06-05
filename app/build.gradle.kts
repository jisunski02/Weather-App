import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.daggerHilt)
    alias(libs.plugins.kotlinksp)
    alias(libs.plugins.kotlinkapt)
}

android {
    namespace = "dev.jayson.weatherapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "dev.jayson.weatherapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // Load properties from local.properties file
        val properties = Properties()
        val localPropertiesFile = rootProject.file("local.properties")

        if (localPropertiesFile.exists()) {
            properties.load(FileInputStream(localPropertiesFile))
            val apiKey = properties.getProperty("apiKey")

            if (apiKey != null) {
                buildConfigField("String", "API_KEY", "\"$apiKey\"")
            } else {
                throw GradleException("API Key not found in local.properties!")
            }
        } else {
            throw GradleException("local.properties file not found!")
        }

    }

    buildFeatures{
        buildConfig = true
    }

    viewBinding{
        enable = true
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
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation (libs.kotlinx.core)
    implementation (libs.kotlinx.android)
    implementation (libs.google.gson)
    implementation (libs.squareup.retrofit)
    implementation (libs.squareup.gson.converter)
    implementation (libs.squareup.okhttp3)
    implementation (libs.androidx.viewmodel)
    implementation (libs.androidx.livedata)
    implementation (libs.androidx.fragment)
    implementation (libs.androidx.ui)
    implementation (libs.bumptech.glide)
    implementation (libs.androidx.room.runtime)
    implementation (libs.androidx.room.ktx)
    implementation (libs.google.dagger.hilt)
            //implementation libs.google.dagger
//    implementation libs.google.dagger.android.support

    implementation(platform(libs.org.jetbrains.kotlin))

    testImplementation (libs.google.truth)
    testImplementation (libs.squareup.mockwebserver)
    testImplementation (libs.junit)
    testImplementation (libs.junit.jupiter)
    testImplementation (libs.io.mockk)
    testImplementation (libs.androidx.core.ktx)
    testImplementation (libs.androidx.arch.core)
    testImplementation (libs.kotlinx.test)


    ksp(libs.androidx.compiler)
    ksp(libs.androidx.room.compiler)
    //ksp(libs.google.dagger.compiler)
    ksp(libs.google.dagger.hilt.compiler)
//    ksp(libs.google.dagger.android.processor)
    ksp(libs.bumptech.compiler)
}