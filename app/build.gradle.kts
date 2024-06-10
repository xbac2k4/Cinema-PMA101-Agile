plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.example.cinemamobilefe"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.cinemamobilefe"
        minSdk = 26
        targetSdk = 34
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
    buildFeatures {
        dataBinding = true
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    //
    implementation ("com.google.android.material:material:1.4.0")
    // slider
    implementation ("com.github.denzcoskun:ImageSlideshow:0.1.2")
    implementation ("com.makeramen:roundedimageview:2.3.0")
    // call api
    implementation("com.google.code.gson:gson:2.10.1")
    implementation ("com.squareup.retrofit2:retrofit:2.1.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.1.0")
    // image
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.14.2")
    // Other dependencies...
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.1")
    // animation
    implementation("com.airbnb.android:lottie:5.2.0")


}