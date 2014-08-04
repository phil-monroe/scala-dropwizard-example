import sbt._

object Dependencies {
  val DropwizardVerison = "0.7.0"
  val SwaggerVersion = "1.3.4"

  val dropwizardCore = "io.dropwizard" % "dropwizard-core" % DropwizardVerison
  val dropwizardAssets = "io.dropwizard" % "dropwizard-assets" % DropwizardVerison
  val dropwizardClient = "io.dropwizard" % "dropwizard-client" % DropwizardVerison
  val dropwizardScala = "com.massrelevance" %% "dropwizard-scala" % DropwizardVerison
  val dropwizardTesting = "io.dropwizard" % "dropwizard-testing" % DropwizardVerison

  val hbc = "com.twitter" % "hbc-core" % "2.0.2"
  val elasticSearch =  "org.elasticsearch" % "elasticsearch" % "1.2.1"
  val jest =  "io.searchbox" % "jest" % "0.1.2"

  val swaggerCore = "com.wordnik" %% "swagger-core" % SwaggerVersion
  val swaggerJaxRs = "com.wordnik" %% "swagger-jaxrs" % SwaggerVersion
  val scalaTest = "org.scalatest" %% "scalatest" % "2.1.7"

  val ProjectDependencies = Seq(
    // Dropwizard
    dropwizardCore,
    dropwizardScala,
    dropwizardClient,
    dropwizardAssets,

    hbc,
    elasticSearch,
    jest,

    swaggerCore,
    swaggerJaxRs exclude("javax.ws.rs", "jsr311-api")
  )

  val TestDependencies = Seq(
    dropwizardTesting % "test",
    scalaTest % "test"
  )

}