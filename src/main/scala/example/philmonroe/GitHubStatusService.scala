package example.philmonroe

import com.massrelevance.dropwizard.ScalaApplication
import io.dropwizard.setup.{Environment, Bootstrap}
import example.philmonroe.resources.{GitHubStatusResource, HelloWorldResource}
import com.wordnik.swagger.config.ScannerFactory
import com.wordnik.swagger.jaxrs.reader.DefaultJaxrsApiReader
import com.wordnik.swagger.reader.ClassReaders
import com.wordnik.swagger.jaxrs.config.DefaultJaxrsScanner
import com.massrelevance.dropwizard.bundles.ScalaBundle
import com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON
import io.dropwizard.assets.AssetsBundle
import io.dropwizard.client.JerseyClientBuilder
import example.philmonroe.clients.GithubAPI
import example.philmonroe.healthchecks.GitHubHealthCheck


object GitHubStatusService extends ScalaApplication[GitHubStatusConfig] {
  override def getName = "GitHub Status"

  override def initialize(bootstrap: Bootstrap[GitHubStatusConfig]): Unit = {
    // Easy Scala JSON Serialization
    bootstrap.addBundle(new ScalaBundle)

    // Swagger
    ScannerFactory.setScanner(new DefaultJaxrsScanner())
    ClassReaders.setReader(new DefaultJaxrsApiReader())

    // Swagger UI
    bootstrap.addBundle(new AssetsBundle("/swagger-ui", "/docs/"))
  }

  override def run(config: GitHubStatusConfig, env: Environment): Unit = {
    // HTTP Client
    val httpClient = new JerseyClientBuilder(env).using(config.httpClientConfig).build("http-client")


    // GitHub
    val gitHub = new GithubAPI(httpClient, config.gitHubToken)


    // Resources
    val resources = Seq(
      new HelloWorldResource,
      new GitHubStatusResource(gitHub),
      new ApiListingResourceJSON
    )
    resources.map(env.jersey().register)


    // Health Checks
    env.healthChecks().register("GitHub", new GitHubHealthCheck(gitHub))
  }
}