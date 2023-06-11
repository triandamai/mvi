@file:Suppress("UnstableApiUsage")

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.org.jetbrains.kotlin.kapt)
    alias(libs.plugins.com.google.dagger.hilt.android)
    alias(libs.plugins.app.cash.sqldelight)
    alias(libs.plugins.io.gitlab.arthubosch.detekt)
    id("kotlin-parcelize")
}


android {
    namespace = libs.versions.namespace.get()
    compileSdk = 33
    defaultConfig {
        applicationId =libs.versions.application.id.get()
        minSdk = 29
        targetSdk = 33
        versionCode = 49
        versionName = "2.0.202305241408"
        multiDexEnabled = true
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.0"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    lint {
        baseline = file("lint-baseline.xml")
        abortOnError = false
    }


    signingConfigs {
        create("release") {
          //  setupKeystore()
        }
    }
    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs.getByName("release")
            isShrinkResources = true
            isMinifyEnabled = true
            isDebuggable = false
        }
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs = listOf(
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
            "-opt-in=androidx.compose.material.ExperimentalMaterialApi",
            "-opt-in=androidx.compose.ui.ExperimentalComposeUiApi",
            "-opt-in=androidx.compose.foundation.ExperimentalFoundationApi",
            "-opt-in=androidx.compose.animation.ExperimentalAnimationApi"
        )
    }

}

dependencies {
    api(project(":core-ui"))
    api(project(":core-component"))
    api(project(":feature-authentication"))
    api(project(":feature-dashboard"))
    api(project(":feature-recipe"))
    coreLibraryDesugaring(libs.desugar.jdk.lib)

    implementation(libs.android.material)
    implementation(libs.compose.markdown)

    implementation(libs.mp.android.chart)

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.compose.icon.extended)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.compose.material)
    implementation(libs.compose.calendar)
    implementation(libs.wheel.picker.compose)
    implementation(libs.coil.compose)
    implementation(libs.navigation.compose)
    implementation(libs.multidex)

    implementation(libs.reorder.list)

    with(libs.accompanist) {
        implementation(pager)
        implementation(pager.indicator)
        implementation(flow.layout)
        implementation(shimmer)
    }
    with(libs.hilt) {
        implementation(navigation.compose)
        implementation(android)
        implementation(work)
        androidTestImplementation(android.test)
        kapt(android.compiler)
        kaptTest(android.compiler)
        kapt(compiler)
    }


    implementation(libs.sqldelight.android.driver)


    implementation(libs.kotlinx.coroutine.play.services)
    testImplementation(libs.kotlinx.coroutine.test)


    implementation(libs.work.runtime)
    implementation(libs.kotlinx.serialization)
    with(libs.kotlinx.coroutine) {
        implementation(android)
        implementation(core)
        implementation(play.services)
        testImplementation(test)
    }

    with(libs.composeIcons) {
        implementation(feather)
    }

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    testImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
    testImplementation(libs.robolectric)

    debugImplementation(libs.leak.canary)

}
kapt {
    correctErrorTypes = true
}
sqldelight {
    databases {
        create("Database") {
            packageName.set("${libs.versions.namespace.get()}.sqldelight")
        }
    }
}

tasks.create<Copy>("installGitHook") {
    var suffix = "macos"
    if (org.apache.tools.ant.taskdefs.condition.Os.isFamily(org.apache.tools.ant.taskdefs.condition.Os.FAMILY_WINDOWS)) {
        suffix = "windows"
    }

    copy {
        from(File(rootProject.rootDir, "scripts/pre-push-$suffix"))
        into { File(rootProject.rootDir, ".git/hooks") }
        rename("pre-push-$suffix", "pre-push")
    }
    copy {
        from(File(rootProject.rootDir, "scripts/pre-commit-$suffix"))
        into { File(rootProject.rootDir, ".git/hooks") }
        rename("pre-commit-$suffix", "pre-commit")
    }
    fileMode = "775".toInt(8)
}
