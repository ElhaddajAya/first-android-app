plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.dev.android_tuto_4"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.dev.android_tuto_4"
        minSdk = 24
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
    implementation(libs.leanback)
    implementation(libs.glide)
    implementation("com.google.android.gms:play-services-maps:18.0.2")
    implementation("com.google.android.gms:play-services-location:21.0.1")
    implementation("androidx.room:room-runtime:2.5.0")
    annotationProcessor("androidx.room:room-compiler:2.5.0")
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    implementation("com.google.android.material:material:1.4.0")
    //androidTestImp8lementation(libs.espresso.core)


}