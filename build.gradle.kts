// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.0.0-alpha11" apply false
    id("com.android.library") version "8.0.0-alpha11" apply false
    id("org.jetbrains.kotlin.android") version "1.7.21" apply false
    id("com.google.protobuf") version "0.8.15" apply false
    id("org.jetbrains.kotlin.jvm") version "1.7.21" apply false
}

ext["grpcVersion"] = "1.47.0"
ext["grpcKotlinVersion"] = "1.3.0" // CURRENT_GRPC_KOTLIN_VERSION
ext["protobufVersion"] = "3.21.2"
ext["coroutinesVersion"] = "1.6.2"