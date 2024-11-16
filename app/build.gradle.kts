plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.imedb"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.imedb"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    // CardView dependency
    implementation("androidx.cardview:cardview:1.0.0")

    // Glide for image loading
    implementation("com.github.bumptech.glide:glide:4.15.1")
    // Removed annotationProcessor for Glide if you're using the newer version

    // Other UI libraries
    implementation(libs.appcompat)  // Ensure `libs` is configured correctly
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    // Test libraries
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Retrofit dependencies for networking
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")

    // RecyclerView
    implementation("androidx.recyclerview:recyclerview:1.2.1")
}
