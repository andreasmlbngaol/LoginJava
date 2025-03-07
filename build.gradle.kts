plugins {
    id("java")
}

group = "com.andreasmlbngaol"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
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


}

tasks.test {
    useJUnitPlatform()
}