plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)

    id("kotlin-kapt")
    alias(libs.plugins.dagger.hilt)
    kotlin("plugin.serialization") version "2.0.20"
}

android {

    // Network config
    tasks.register<DefaultTask>("generateNetworkSecurityConfig"){

        doLast{

            val ip = project.properties["IP"]
            val file = file("./src/main/res/xml/network_security_config.xml")


            file.writeText(
                    """
                <network-security-config>
                    <domain-config cleartextTrafficPermitted="true">
                        <domain includeSubdomains="true">${ip}</domain>
                    </domain-config>
                </network-security-config>
            """.trimIndent()
                )

        }

    }


    tasks.named("preBuild").configure{
        dependsOn(tasks.named("generateNetworkSecurityConfig"))
    }




    buildFeatures {
        buildConfig = true
    }

    buildTypes{


        debug {
            // Add the base URL for debug builds
            buildConfigField("String", "BASE_URL", "\"${project.properties["BASE_URL_DEBUG"]}\"")
        }
        release {
            // Add the base URL for release builds
            buildConfigField("String", "BASE_URL", "\"${project.properties["BASE_URL_RELEASE"]}\"")
        }



    }


    namespace = "com.socialmediascrollapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.socialmediascrollapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {


    // Swipe to refresh
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.2.0-alpha01")

    // Paging
    implementation ("androidx.paging:paging-runtime-ktx:3.1.1")
    implementation ("androidx.paging:paging-compose:1.0.0-alpha18")

    // Room
    val room_version  = "2.6.1"
    implementation ("androidx.room:room-ktx:${room_version}")
    kapt ("androidx.room:room-compiler:${room_version}")
    implementation ("androidx.room:room-paging:${room_version}")




    // Coil
    implementation("io.coil-kt:coil-compose:2.4.0")

    // Dagger Hilt
    implementation(libs.dagger.hilt)
    kapt(libs.dagger.hilt.compiler)
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    // Navigation
    implementation(libs.androidx.navigation.compose)
    implementation("androidx.navigation:navigation-compose:2.7.6")

    // Material
    implementation("androidx.compose.material:material:1.7.0")

    // Arrow
    implementation("io.arrow-kt:arrow-core:1.2.4")
    implementation("io.arrow-kt:arrow-fx-coroutines:1.2.4")

    // Retrofit
    val retrofit_version = "2.11.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation("com.squareup.okhttp3:okhttp:4.11.0")

    // Serialization
    val kotlin_serialization = "1.6.1"
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlin_serialization")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")
    implementation("com.squareup.retrofit2:converter-kotlinx-serialization:2.11.0")

    // AndroidX
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
