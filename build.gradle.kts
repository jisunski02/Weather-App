// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication)  version ("8.1.4") apply false
    alias(libs.plugins.androidLibrary) version ("8.1.4") apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) version ("1.9.23") apply false
    alias(libs.plugins.daggerHilt) version ("2.48.1") apply false
    alias(libs.plugins.kotlinksp) version ("1.9.23-1.0.19") apply false
    alias(libs.plugins.kotlinkapt) apply false
}