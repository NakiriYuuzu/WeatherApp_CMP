buildscript {
    dependencies {
        // fix the build failed issue in new kmm plugin version.
        classpath(Deps.Sqldelight.gradlePlugin)
        classpath(Deps.Moko.Resources.classPath_Resources)
        classpath("org.jetbrains.kotlinx:atomicfu-gradle-plugin:0.17.3")
    }
}

plugins {
    //trick: for the same plugin versions in all sub-modules
//    id("com.android.application").version("8.0.2").apply(false)
//    id("com.android.library").version("8.0.2").apply(false)
//    kotlin("android").version("1.8.21").apply(false)
//    kotlin("multiplatform").version("1.8.21").apply(false)

    kotlin("multiplatform").apply(false)
    id("com.android.application").apply(false)
    id("com.android.library").apply(false)
    id("org.jetbrains.compose").apply(false)
}

// Fix the build failed issue in new kmm plugin version.
allprojects {
    apply(plugin = "kotlinx-atomicfu")
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
