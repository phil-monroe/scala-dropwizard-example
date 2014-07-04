package example.philmonroe.setup.bundles

import io.dropwizard.ConfiguredBundle
import io.dropwizard.setup.{Environment, Bootstrap}
import example.philmonroe.GitHubStatusConfig
import example.philmonroe.core.tweet_stream.ManagedTweetStream
import example.philmonroe.core.elasticsearch.ManagedElasticSearchClient

/**
 * Created by phil on 6/4/14.
 */
class ElasticSearchBundle extends ConfiguredBundle[GitHubStatusConfig] {
  val elasticsearch = new ManagedElasticSearchClient

  override def initialize(bootstrap: Bootstrap[_]): Unit = {

  }

  override def run(config: GitHubStatusConfig, env: Environment): Unit = {
    env.lifecycle().manage(elasticsearch)
  }
}
