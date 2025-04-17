plugins {
    alias(libs.plugins.android.application)
    id("com.google.gms.google-services")
    id("org.jetbrains.kotlin.android")
    //alias(libs.plugins.kotlin.kapt)  // Add kapt in a similar format
    id("kotlin-kapt") // ✅ Apply kapt directly



}

android {
    namespace = "edu.psu.sweng888.booknest"
    compileSdk = 35

    defaultConfig {
        applicationId = "edu.psu.sweng888.booknest"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11  // Ensure Java uses 11
            targetCompatibility = JavaVersion.VERSION_11
        }

        kotlinOptions {
            jvmTarget = "11"  // Ensure Kotlin uses 11 too
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(platform("com.google.firebase:firebase-bom:33.10.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation(libs.firebase.auth)  // Firebase Authentication
    implementation(libs.firebase.core)  // Firebase Core
    implementation(libs.firebase.bom)  // Firebase Authentication
    implementation(libs.firebase.analytics)  // Firebase Core
    implementation(libs.firebase.firestore)  // Firebase Authentication
    implementation(libs.glide)  // Glide Image Loading
    kapt(libs.glide.compiler)   // Glide Annotation Processor
    implementation(libs.recyclerView)


    implementation(libs.appcompat)  // For AppCompat
    implementation(libs.material)  // For Material Design
    implementation(libs.constraintlayout)  // For ConstraintLayout
    implementation(libs.navigation.fragment)  // For Navigation Fragment
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}