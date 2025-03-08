import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    id("java")
    kotlin("jvm")
    id("org.jetbrains.kotlin.plugin.compose")
    id("org.jetbrains.compose")
    kotlin("plugin.serialization") version "2.0.21"
}

group = "com.andreasmlbngaol"
version = "1.0-SNAPSHOT"

repositories {
    google()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    mavenCentral()
}

kotlin {
    jvmToolchain(22)
}

sourceSets {
    main {
        java.srcDirs("src/main/java")  // Menjadikan Java & Kotlin satu folder
        kotlin.srcDirs("src/main/java") // Kotlin juga di folder yang sama
    }
}


dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    // JDBC PostgreSQL
    implementation("org.postgresql:postgresql:42.7.5")

    // BCrypt for Password
    implementation("org.mindrot:jbcrypt:0.4")

    // ORM Hibernate
    implementation("org.hibernate.orm:hibernate-core:7.0.0.Beta4")
    implementation("jakarta.persistence:jakarta.persistence-api:3.2.0")

    // Compose for Desktop
    implementation(compose.desktop.currentOs)
    val composeVersion = "1.7.3"
    implementation("org.jetbrains.compose.ui:ui:$composeVersion")
    implementation("org.jetbrains.compose.foundation:foundation:$composeVersion")
//    implementation("org.jetbrains.compose.material:material-desktop:$composeVersion")

    // Desktop-specific dependencies
    implementation("org.jetbrains.compose.desktop:desktop:$composeVersion")

    // Icons dan Material3
    implementation("org.jetbrains.compose.material3:material3:$composeVersion")
    implementation("org.jetbrains.compose.material:material-icons-core:$composeVersion")
    implementation("org.jetbrains.compose.material:material-icons-extended:$composeVersion")

    // NavHost
    implementation("org.jetbrains.androidx.navigation:navigation-compose:2.8.0-alpha12")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.0")

    // ViewModel
    implementation("org.jetbrains.androidx.lifecycle:lifecycle-viewmodel-compose:2.9.0-alpha03")

    // ViewModelScope
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-swing:1.10.1")

    // Koin
    api("io.insert-koin:koin-core:4.1.0-Beta5")
    implementation("io.insert-koin:koin-compose:4.1.0-Beta5")
    implementation("io.insert-koin:koin-compose-viewmodel:4.1.0-Beta5")


//    implementation("org.jetbrains.skiko:skiko-awt-runtime-windows-x64:0.9.2") // Untuk runtime JVM


    // Integrasi dengan Java
    implementation("org.jetbrains.kotlin:kotlin-stdlib")

}

compose.desktop {
    application {
        mainClass = "com.andreasmlbngaol.MainAppKt" // Sesuaikan dengan main class kamu
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb) // Installer untuk macOS, Windows, Linux
            packageName = "LoginJavaCompose"
            packageVersion = "1.0.0"
        }
    }
}

//application {
//    mainClass.set("com.andreasmlbngaol.MainAppKt")
//}

tasks.test {
    useJUnitPlatform()
}

