
plugins {
    //trick: for the same plugin versions in all sub-modules
    kotlin("jvm") apply false
    kotlin("multiplatform") apply false
    kotlin("android") apply false
    id("com.android.application") apply false
    id("com.android.library") apply false
    id("org.jetbrains.compose") apply false
    id("com.squareup.sqldelight") apply false
   // id("app.cash.sqldelight") apply false


}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
     //   maven("https://mvnrepository.com/artifact/app.cash.sqldelight/runtime")
        mavenLocal()
    }


}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}


