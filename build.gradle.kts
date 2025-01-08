plugins {
    kotlin("jvm") version "1.7.21"
}

group = "kr.cosine.mythicmobsdrop"
version = "1.2.0"

repositories {
    maven("https://maven.hqservice.kr/repository/maven-public")
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://mvn.lumine.io/repository/maven-public/")
}

dependencies {
    compileOnly("org.spigotmc", "spigot-api", "1.17.1-R0.1-SNAPSHOT")
    compileOnly("io.lumine", "Mythic-Dist", "5.2.0")

    compileOnly("kr.hqservice", "hqframework-bukkit-core", "1.0.2-SNAPSHOT") { exclude("org.spigotmc") }
    compileOnly("kr.hqservice", "hqframework-bukkit-command", "1.0.2-SNAPSHOT") { exclude("org.spigotmc") }
    compileOnly("kr.hqservice", "hqframework-bukkit-inventory", "1.0.2-SNAPSHOT") { exclude("org.spigotmc") }
    compileOnly("kr.hqservice", "hqframework-bukkit-nms", "1.0.2-SNAPSHOT") { exclude("org.spigotmc") }

    testImplementation(kotlin("test"))
    testImplementation(kotlin("reflect"))
}

tasks {
    test {
        useJUnitPlatform()
    }
    jar {
        archiveFileName.set("${rootProject.name}-${rootProject.version}.jar")
        destinationDirectory.set(file("D:\\서버\\1.20.1 - 개발\\plugins"))
    }
}