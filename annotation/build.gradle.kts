@file:Suppress("UnstableApiUsage")
/*
 * Copyright © 2023 trian.app.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    //should place at the bottom plugins to avoid warning dagger not recognized by any processor
    kotlin("jvm")
}

dependencies {
    implementation("com.google.devtools.ksp:symbol-processing-api:1.8.0-1.0.9")
    implementation("com.squareup:kotlinpoet-ksp:1.14.2")
}