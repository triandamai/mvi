plugins {
    id("com.android.application")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
    id("io.gitlab.arturbosch.detekt")
    kotlin("android")
    kotlin("kapt")
}

android {
    namespace = AppConfig.nameSpace
    compileSdk = 33

    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true

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
        kotlinCompilerExtensionVersion = GlobalVersion.composeVersion
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(Deps.AndroidX.Core.coreKtx)
    implementation(Deps.AndroidX.Lifecycle.runtimeLifecycleKtx)
    implementation(Deps.AndroidX.Activity.activityCompose)
    implementation(Deps.AndroidX.Multidex.multidex)
    with(Deps.AndroidX.Compose) {
        implementation(platform(composeBom))
        androidTestImplementation(platform(composeBom))
        implementation(material3)
        implementation(ui)
        implementation(uiToolingPreview)
        debugImplementation(uiTooling)
        androidTestImplementation(uiTestJunit4)
        debugImplementation(uiTestManifest)
        implementation(materialIconExtended)
        implementation(materialWindowSizeClass)
    }
    with(Deps.Com.Google.Accompanist) {
        implementation(pager)
        implementation(pagerIndicator)
    }
    implementation(Deps.AndroidX.Compose.composeRuntimeLiveData)
    with(Deps.Com.Google.Dagger) {
        implementation(hiltAndroid)
        kapt(hiltAndroidCompiler)
    }
    implementation(Deps.AndroidX.Hilt.hiltNavigationCompose)
    implementation(Deps.AndroidX.Navigation.navigationCompose)
    implementation(Deps.AndroidX.Work.workRuntime)
    implementation(Deps.AndroidX.Hilt.hiltWork)
    kapt(Deps.AndroidX.Hilt.hiltCompiler)

    with(Deps.Com.Google.Firebase) {
        implementation(platform(firebaseBom))
        implementation(firebaseAnalytics)
        implementation(firebaseAuth)
        implementation(firebaseFirestore)
        implementation(firebaseStorage)
        implementation(firebaseInstallation)
    }
    implementation(Deps.Org.Jetbrains.Kotlinx.kotlinxCoroutineAndroid)
    with(Deps.AndroidX.Room) {
        implementation(roomRuntime)
        kapt(roomCompiler)
        implementation(roomKtx)
        implementation(roomPaging)
        testImplementation(roomTesting)
    }


    testImplementation(Deps.Junit.jUnit)
    androidTestImplementation(Deps.AndroidX.Test.extJunit)
    androidTestImplementation(Deps.AndroidX.Test.espressoCore)
}
// Allow references to generated code
kapt {
    correctErrorTypes = true
}

//https://dev.to/akdevcraft/git-pre-hook-setup-pre-push-hook-for-gradle-project-example-1nn6
//https://emmanuelkehinde.io/setting-up-git-pre-commit-pre-push-hook-for-ktlint-check/
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

    //make file executable
    fileMode = "775".toInt(8)
}