import AssemblyKeys._
import Dependencies._

name := "dw-example"

version := Git.version("1.0")

scalaVersion := "2.10.4"

mainClass in(Compile, run) := Some("example.philmonroe.DwExampleService")

libraryDependencies ++= ProjectDependencies ++ TestDependencies

assemblySettings

net.virtualvoid.sbt.graph.Plugin.graphSettings

test in assembly := {}

packageOptions in(Compile, packageBin) +=
  Package.ManifestAttributes("git-sha" -> Git.GitSha)


buildInfoSettings

sourceGenerators in Compile <+= buildInfo

buildInfoKeys := Seq[BuildInfoKey](
  name,
  version,
  scalaVersion,
  sbtVersion,
  "GitSha" -> Git.GitSha
)

buildInfoPackage := "example.philmonroe"

addCommandAlias("server", ";run server config.yml")

addCommandAlias("stage", ";assembly")