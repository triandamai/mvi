@file:Suppress("UnstableApiUsage")

import org.jetbrains.kotlin.asJava.classes.lazyPub

/*
 * Copyright © 2023 trian.app.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    //should place at the bottom plugins to avoid warning dagger not recognized by any processor
    `maven-publish`
}
android{
    namespace="app.trian.mvi.processor"
    compileSdk = 33
}

group = "app.trian.core.mvi"
version = "1.0"
dependencies {
    implementation("com.google.devtools.ksp:symbol-processing-api:1.8.0-1.0.9")
    implementation("com.squareup:kotlinpoet-ksp:1.14.2")
}

afterEvaluate {
    publishing{
        publications {
                register("release",MavenPublication::class){
                    from(components["release"])
                    groupId = "app.trian.mvi.processor"
                    version = "1.0"
                    artifactId = "annotation"
                }
        }
    }
}

