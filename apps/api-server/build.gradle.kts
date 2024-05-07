tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}

dependencies {
    implementation(project(":modules:clients"))
    implementation(project(":modules:domain"))
    implementation(project(":modules:support:logging"))
    implementation(project(":modules:support:util"))
    implementation(project(":modules:jpa"))
    implementation(project(":modules:redis"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")
}