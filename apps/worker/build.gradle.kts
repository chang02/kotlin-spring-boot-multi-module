tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}

dependencies {
    implementation(project(":modules:domain"))
    implementation(project(":modules:kafka"))
    implementation(project(":modules:support:logging"))
    implementation(project(":modules:support:util"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.kafka:spring-kafka")
}