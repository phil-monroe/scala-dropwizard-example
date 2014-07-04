package example.philmonroe

import io.dropwizard.setup.{Environment, Bootstrap}
import example.philmonroe.resources.{SearchResource, GitHubStatusResource, HelloWorldResource}
import com.massrelevance.dropwizard.bundles.ScalaBundle
import io.dropwizard.Application
import example.philmonroe.setup.Logging
import example.philmonroe.setup.bundles._


class GitHubStatusService extends Application[GitHubStatusConfig] with Logging {
  override def getName = "GitHub Status"

  val scalaBundle = new ScalaBundle
  val swaggerBundle = new SwaggerBundle
  val exceptionMapperBundle = new ExceptionMapperBundle
  val githubBundle = new GithubBundle
  val elasticSearchBundle = new ElasticSearchBundle
  val tweetStreamBundle = new TweetStreamBundle(elasticSearchBundle)
  val healthcheckBundle = new HealthcheckBundle(githubBundle)

  override def initialize(bootstrap: Bootstrap[GitHubStatusConfig]): Unit = {
    bootstrap.addBundle(scalaBundle)
    bootstrap.addBundle(swaggerBundle)
    bootstrap.addBundle(exceptionMapperBundle)
    bootstrap.addBundle(githubBundle)
    bootstrap.addBundle(healthcheckBundle)
    bootstrap.addBundle(elasticSearchBundle)
    bootstrap.addBundle(tweetStreamBundle)
  }

  override def run(config: GitHubStatusConfig, env: Environment): Unit = {
    LOG.info("Starting with sha: " + BuildInfo.GitSha)

    // Resources
    env.jersey().register(new HelloWorldResource)
    env.jersey().register(new GitHubStatusResource(githubBundle.githubClient))
    env.jersey().register(new SearchResource(elasticSearchBundle.elasticsearch, env.getObjectMapper))
  }
}

object GitHubStatusService {
  final def main(args: Array[String]) = {
    (new GitHubStatusService).run(args)
  }
}