plugins {
    id(Config.Plugins.library)
    id(Config.Plugins.kotlin)
}

android {
    compileSdk = Config.Version.compileSdk

    defaultConfig {
        minSdk = Config.Version.minSdk
        targetSdk = Config.Version.targetSdk
        testInstrumentationRunner = Config.Android.Test.jUnitRunner
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    dependencies {
        implementation(Config.Kotlin.Coroutine.coroutines)
        testImplementation(Config.Kotlin.Coroutine.coroutineTest)
    }
}