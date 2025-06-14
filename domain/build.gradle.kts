plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    id("java-test-fixtures")
    id("org.jetbrains.kotlinx.kover")
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}
dependencies {
    implementation(project(":core"))
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.coroutines.test)

}