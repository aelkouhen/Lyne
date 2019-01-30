name := "lyne"
scalaVersion := "2.12.8"
sbtVersion   := "1.2.8"

lazy val springVersion = "1.5.3.RELEASE"
lazy val thymeleafVersion = "2.1.4.RELEASE"

libraryDependencies ++= Seq (
  "org.springframework.boot" % "spring-boot-starter-web" % springVersion,
  "org.springframework.boot" % "spring-boot-starter-data-jpa" % springVersion,
  "org.springframework.boot" % "spring-boot-starter-actuator" % springVersion,
  "org.springframework.boot" % "spring-boot-starter-security" % springVersion,
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.0",
  "com.google.guava" % "guava" % "27.0.1-jre",
"org.thymeleaf" % "thymeleaf-spring4" % thymeleafVersion,
  "nz.net.ultraq.thymeleaf" % "thymeleaf-layout-dialect" % "1.4.0",
  "com.h2database" % "h2" % "1.4.195",
  "org.webjars" % "bootstrap" % "3.1.1",
  "com.fasterxml.jackson.datatype" % "jackson-datatype-jsr310" % "2.8.8",
  "mysql" % "mysql-connector-java" % "8.0.13"
)
