
name := "dw-example"

version := "1.0"

scalaVersion := "2.10.4"

mainClass in(Compile, run) := Some("example.philmonroe.GitHubStatusService")

libraryDependencies ++= Seq(
  "io.dropwizard" % "dropwizard-core" % "0.7.0",
  "io.dropwizard" % "dropwizard-assets" % "0.7.0",
  "io.dropwizard" % "dropwizard-client" % "0.7.0",
  "com.massrelevance" %% "dropwizard-scala" % "0.7.0",
  "com.wordnik" %% "swagger-core" % "1.3.4" ,
  "com.wordnik" %% "swagger-jaxrs" % "1.3.4" exclude("javax.ws.rs", "jsr311-api")
)


assemblySettings

net.virtualvoid.sbt.graph.Plugin.graphSettings