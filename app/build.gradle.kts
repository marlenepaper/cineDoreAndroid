plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.binaryBuddies.cinedore"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.binaryBuddies.cinedore"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.3")
    implementation (libs.room.runtime)
    annotationProcessor (libs.room.compiler)
    androidTestImplementation (libs.room.testing)
    implementation (libs.lifecycle.common.java8)
    implementation (libs.retrofit)
    implementation (libs.retrofit.gson)
    implementation(libs.glide)
    annotationProcessor(libs.glide.compiler)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.legacy.support.v4)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}