tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}

dependencies {
    implementation(project(":modules:clients"))
    implementation(project(":modules:domain"))
    implementation(project(":modules:redis"))
    implementation(project(":modules:kafka"))
    implementation(project(":modules:support:logging"))
    implementation(project(":modules:support:util"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")
}