// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
    alias(libs.plugins.android.library) apply false
    id("org.jetbrains.kotlin.plugin.serialization") version libs.versions.kotlin.get()
    id("org.jetbrains.kotlinx.kover") version "0.8.3"
}
