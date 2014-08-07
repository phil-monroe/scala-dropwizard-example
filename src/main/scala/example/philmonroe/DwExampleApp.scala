package example.philmonroe

import io.dropwizard.setup.{Environment, Bootstrap}
import example.philmonroe.resources.{TimeResource, SearchResource, HelloWorldResource}
import com.massrelevance.dropwizard.bundles.ScalaBundle
import io.dropwizard.Application
import example.philmonroe.setup.Logging
import example.philmonroe.setup.bundles._


class DwExampleApp extends Application[DwExampleConfig] with Logging {
  override def getName = "Dropwizard Example"

  val scalaBundle = new ScalaBundle
  val swaggerBundle = new SwaggerBundle
  val exceptionMapperBundle = new ExceptionMapperBundle
  val elasticSearchBundle = new ElasticSearchBundle
  val tweetStreamBundle = new TweetStreamBundle(elasticSearchBundle)
  val healthcheckBundle = new HealthcheckBundle()

  override def initialize(bootstrap: Bootstrap[DwExampleConfig]): Unit = {
    bootstrap.addBundle(scalaBundle)
    bootstrap.addBundle(swaggerBundle)
    bootstrap.addBundle(exceptionMapperBundle)
    bootstrap.addBundle(healthcheckBundle)
    bootstrap.addBundle(elasticSearchBundle)
    bootstrap.addBundle(tweetStreamBundle)
  }

  override def run(config: DwExampleConfig, env: Environment): Unit = {
    LOG.info("Starting with sha: " + BuildInfo.GitSha)

    // Resources
    env.jersey().register(new HelloWorldResource)
    env.jersey().register(new TimeResource)
    env.jersey().register(new SearchResource(elasticSearchBundle.elasticsearch))
  }
}

object DwExampleApp {
  final def main(args: Array[String]) = {
    (new DwExampleApp).run(args)
  }
}