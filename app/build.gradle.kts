import java.util.Properties

val secretProperties = Properties().apply {
    load(project.rootProject.file("secrets.properties").reader())
}

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.recruiterhunter"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.recruiterhunter"
        minSdk = 27
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
        buildConfigField(
            "String",
            "API_KEY",
            "\"${secretProperties.getProperty("hhApiKey") ?: ""}\""
        )  // Уже ок, String
        buildConfigField(
            "String",
            "GOOGLE_SEARCH_KEY",
            "\"${secretProperties.getProperty("googleSearchKey") ?: ""}\""
        )  // Измени на String и добавь кавычки
        buildConfigField(
            "String",
            "CUSTOM_SEARCH_ENGINE",
            "\"${secretProperties.getProperty("customSearchEngineCx") ?: ""}\""
        )  // То же самое

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
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}