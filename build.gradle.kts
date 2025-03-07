plugins {
    id("java")
    kotlin("jvm")
    id("org.jetbrains.kotlin.plugin.compose")
    id("org.jetbrains.compose")
    kotlin("plugin.serialization") version "2.0.21"
    application
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
    implementation("org.jetbrains.compose.ui:ui-desktop:1.7.3")
    implementation("org.jetbrains.compose.foundation:foundation-desktop:1.7.3")
//    implementation("org.jetbrains.compose.material:material-desktop:1.7.3")

    // Desktop-specific dependencies
    implementation("org.jetbrains.compose.desktop:desktop:1.7.3")

    // Icons dan Material3
    implementation("org.jetbrains.compose.material3:material3-desktop:1.7.3")
    implementation("org.jetbrains.compose.material:material-icons-core-desktop:1.7.3")
    implementation("org.jetbrains.compose.material:material-icons-extended-desktop:1.7.3")

    // NavHost
    implementation("org.jetbrains.androidx.navigation:navigation-compose:2.8.0-alpha10")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")

    // Integrasi dengan Java
    implementation("org.jetbrains.kotlin:kotlin-stdlib")

}

application {
    mainClass.set("com.andreasmlbngaol.MainAppKt")
}

tasks.test {
    useJUnitPlatform()
}