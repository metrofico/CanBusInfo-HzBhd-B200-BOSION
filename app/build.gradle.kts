plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.hzbhd"
    compileSdk = 35

    buildFeatures {
        aidl = true
        buildConfig = true // Habilita la generación de BuildConfig
    }
    defaultConfig {
        applicationId = "com.hzbhd"
        minSdk = 28
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        ndk {
            abiFilters.addAll(listOf("armeabi-v7a", "arm64-v8a", "x86", "x86_64"))
        }
    }
    sourceSets {
        getByName("main") {
            aidl.setSrcDirs(listOf("src/main/java"))
            jniLibs.srcDirs("src/main/jni")
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
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation("androidx.startup:startup-runtime:1.1.1")
    implementation("org.slf4j:slf4j-api:2.0.9")
    annotationProcessor("org.apache.logging.log4j:log4j-core:2.14.1")
    implementation("org.apache.logging.log4j:log4j-api:2.14.1")
    implementation("log4j:log4j:1.2.17")
    implementation("com.google.code.gson:gson:2.10.1") // O la última versión estable
    implementation(project(":app:util"))
    implementation(project(":app:nfore"))
}