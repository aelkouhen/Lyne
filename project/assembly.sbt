resolvers ++= Seq(Resolver.sbtPluginRepo("releases"), Resolver.sbtPluginRepo("snapshots"))
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.5")
