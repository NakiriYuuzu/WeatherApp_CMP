plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("org.jetbrains.compose")

    kotlin("plugin.serialization") version Deps.Version.kotlin
    id("dev.icerock.mobile.multiplatform-resources")
}

kotlin {
    androidTarget()
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
            export(Deps.Moko.Resources.commonMain_Resources)
        }
        extraSpecAttributes["resources"] = "['src/commonMain/resources/**', 'src/iosMain/resources/**']"
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                //put your multiplatform dependencies here
                // Compose
                implementation(compose.ui)
                implementation(compose.runtime)
                implementation(compose.animation)
                implementation(compose.animationGraphics)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)

                // Compose View
                implementation("com.moriatsushi.insetsx:insetsx:0.1.0-alpha10") // safe area protected insets

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
                dependsOn(commonMain)
                // Compose
                implementation(Deps.Android.appcompat)
                implementation(Deps.Android.activityCompose)
                // KTOR
                implementation(Deps.Ktor.ktorAndroid)
                // koin
                implementation(Deps.Koin.android)
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation(Deps.Ktor.ktorIOS)
            }
        }
    }
}

android {
    namespace = Configurations.applicationId
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