plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    namespace = "com.example.ejercicio_tema_8"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.ejercicio_tema_8"
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

    viewBinding{
        enable = true
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")   //Para Material Design
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    //Para la librería Picasso
    implementation("com.squareup.picasso:picasso:2.71828")
    //Para la librería Glide
    implementation("com.github.bumptech.glide:glide:4.16.0")
    //Para el SplashScreen
    implementation("androidx.core:core-splashscreen:1.0.1")
    //Para GoogleMaps
    implementation("com.google.android.gms:play-services-maps:18.1.0")
    //Para Street Maps
    implementation("org.osmdroid:osmdroid-android:6.1.17")
    //Para CameraX
    implementation("androidx.camera:camera-camera2:1.3.0")
    implementation("androidx.camera:camera-lifecycle:1.3.0")
    implementation("androidx.camera:camera-view:1.3.0")
    //Para Coil (y para poder ver las imágenes hechas con la cámara)
    implementation("io.coil-kt:coil:2.5.0")
}