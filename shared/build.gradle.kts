plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")

    kotlin("plugin.serialization") version Deps.Version.kotlin
    id("dev.icerock.mobile.multiplatform-resources")
    id("org.jetbrains.compose")
    id("com.squareup.sqldelight")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
        }
        extraSpecAttributes["resources"] = "['src/commonMain/resources/**', 'src/iosMain/resources/**']"
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                //put your multiplatform dependencies here
                // Compose
                implementation(compose.animation)
                implementation(compose.animationGraphics)
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)

                // Compose View
                implementation("com.moriatsushi.insetsx:insetsx:0.1.0-alpha10") // safe area protected insets
                with(Deps.UI) {
                    api(composeView)
                }

                // Resources
                api(Deps.Moko.Resources.commonMain_Resources)
                api(Deps.Moko.Resources.commonMain_ResourcesCompose)

                // SQLDelight
                implementation(Deps.Sqldelight.runtime)
                implementation(Deps.Sqldelight.coroutinesExtensions)
                implementation(Deps.Kotlinx.DateTime.dateTime)

                // Logging
                implementation(Deps.Logging.napier)

                // Koin
                with(Deps.Koin) {
                    api(core)
                }

                // KTOR
                with(Deps.Ktor) {
                    api(core)
                    api(logging)
                    api(negotiation)
                    api(serialization)
                }

                // Precompose
                with(Deps.Precompose) {
                    api(precompose)
                    api(precomposeViewModel)
                }

                // Preferences
                with(Deps.SharedPreferences) {
                    api(multiplatform)
                    api(multiplatform_coroutine)
                }
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                //put your android dependencies here
                // Compose
                implementation(Deps.Android.appcompat)
                implementation(Deps.Android.activityCompose)

                // SQLDelight
                implementation(Deps.Sqldelight.androidDriver)

                // KTOR
                implementation(Deps.Ktor.ktorAndroid)

                // koin
                implementation(Deps.Koin.android)
            }
        }
        val iosMain by getting {
            dependencies {
                //put your ios dependencies here

                // SQLDelight
                implementation(Deps.Sqldelight.iosDriver)

                // KTOR
                implementation(Deps.Ktor.ktorIOS)
            }
        }
    }
}

android {
    namespace = "net.yuuzu.weatherapp"
    compileSdk = Configurations.compileSdk
    defaultConfig {
        minSdk = Configurations.minSdk
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }
}

multiplatformResources {
    multiplatformResourcesPackage = Configurations.applicationId
    multiplatformResourcesClassName = "SharedRes"
}