plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.hzbhd.constant"
    compileSdk = 35

    defaultConfig {
        minSdk = 28

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField("String", "CLIENT", "\"kc1\"")
        buildConfigField("String", "JAR_PATH", "\"jar_gp\"")
        buildConfigField("String", "UI_TYPE", "\"pj5-kc1\"")
        buildConfigField("String[]", "UI_ARRAYS", "{\"fs\"}")
    }
    buildFeatures {
        buildConfig = true // Habilita la generación de BuildConfig
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
    implementation(project(":app:nfore"))
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}