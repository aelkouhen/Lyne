name := "lyne"
scalaVersion := "2.12.8"
sbtVersion   := "1.2.8"

lazy val springVersion = "1.5.3.RELEASE"
lazy val thymeleafVersion = "2.1.4.RELEASE"

libraryDependencies ++= Seq (
  "org.springframework.boot" % "spring-boot-starter-web" % springVersion,
  "org.springframework.boot" % "spring-boot-starter-data-jpa" % springVersion,
  "org.springframework.boot" % "spring-boot-starter-actuator" % springVersion,
  "org.thymeleaf" % "thymeleaf-spring4" % thymeleafVersion,
  "nz.net.ultraq.thymeleaf" % "thymeleaf-layout-dialect" % "1.4.0",
  "com.h2database" % "h2" % "1.4.195",
  "org.webjars" % "bootstrap" % "3.1.1",
  "com.fasterxml.jackson.datatype" % "jackson-datatype-jsr310" % "2.8.8",
  "mysql" % "mysql-connector-java" % "5.1.16"
)

assemblyMergeStrategy in assembly := {
  case PathList("javax", "servlet", xs @ _*) => MergeStrategy.last
  case PathList("javax", "activation", xs @ _*) => MergeStrategy.last
  case PathList("org", "hibernate", xs @ _*) => MergeStrategy.last
  case PathList("org", "springframework", xs @ _*) => MergeStrategy.last
  case PathList("org", "apache", xs @ _*) => MergeStrategy.last
  case PathList("com", "google", xs @ _*) => MergeStrategy.last
  case PathList("com", "codahale", xs @ _*) => MergeStrategy.last
  case PathList("org", "codehaus", xs @ _*) => MergeStrategy.last
  case "plugin.properties" => MergeStrategy.last
  case "log4j.properties" => MergeStrategy.last
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}