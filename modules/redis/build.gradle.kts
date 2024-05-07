dependencies {
    api("org.redisson:redisson-spring-boot-starter:3.29.0")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework:spring-context")
    implementation(project(":modules:support:util"))
}