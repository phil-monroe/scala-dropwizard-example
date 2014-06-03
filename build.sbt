name := "dw-example"

version := "1.0"

scalaVersion := "2.10.4"

mainClass in (Compile,run) := Some("example.philmonroe.App")

libraryDependencies ++= Seq(
  "io.dropwizard" % "dropwizard-core" % "0.7.0",
  "com.massrelevance" %% "dropwizard-scala" % "0.7.0"
)

