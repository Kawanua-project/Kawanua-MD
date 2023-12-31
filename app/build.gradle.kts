plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-parcelize")
}

android {
    namespace = "com.jonathan.kawanuaapp"
    compileSdk = 34

    defaultConfig {
        buildConfigField("String", "API_URL", "\"https://kawanua-qxgwgqaooa-uc.a.run.app/\"")
        buildConfigField("String", "API_URL_NEWS", "\"https://newsapi.org/v2/\"")
        buildConfigField("String", "API_URL_DETECT", "\"https://kawanua-detection-qxgwgqaooa-uc.a.run.app/\"")
        buildConfigField("String", "API_KEY", "\"6c3f261ad3cb4746afa01b50fd3e092a\"")

        applicationId = "com.jonathan.kawanuaapp"
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
    kotlinOptions {
        jvmTarget = "1.8"
    }

    viewBinding {
        enable = true
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // Gmaps
    implementation("com.google.android.gms:play-services-maps:18.2.0")

    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.5")
    implementation("com.google.firebase:firebase-auth:22.3.0")
    implementation("androidx.preference:preference:1.2.1")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Navigation
    implementation("androidx.navigation:navigation-ui-ktx:2.7.5")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.5")

    // Datastore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // Glide
    implementation("com.github.bumptech.glide:glide:4.16.0")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")

    // Circle Image
    implementation("de.hdodenhof:circleimageview:3.1.0")

    // Lottie dependency
    implementation("com.airbnb.android:lottie:3.4.0")

    // Login Firebase
    implementation("com.google.firebase:firebase-auth-ktx:22.3.0")
    implementation("com.google.android.gms:play-services-auth:20.7.0")

    // Data store
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2")

    implementation("androidx.exifinterface:exifinterface:1.3.6")

    //Compressor
    implementation("id.zelory:compressor:3.0.1")

}