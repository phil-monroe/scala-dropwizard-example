package example.philmonroe.setup.bundles

import io.dropwizard.ConfiguredBundle
import io.dropwizard.setup.{Environment, Bootstrap}
import example.philmonroe.GitHubStatusConfig
import example.philmonroe.core.tweet_stream.{TweetProcessor, ManagedTweetProcessingPool, ManagedTweetStream}
import example.philmonroe.core.elasticsearch.ManagedElasticSearchClient

/**
 * Created by phil on 6/4/14.
 */
class TweetStreamBundle(esBundle: ElasticSearchBundle) extends ConfiguredBundle[GitHubStatusConfig] {

  override def initialize(bootstrap: Bootstrap[_]): Unit = {

  }

  override def run(config: GitHubStatusConfig, env: Environment): Unit = {
    val tweetStream = new ManagedTweetStream(config.twitter)
    val processor = new TweetProcessor(tweetStream.queue, env.getObjectMapper, esBundle.elasticsearch)
    val processorPool = new ManagedTweetProcessingPool(processor)

    env.lifecycle().manage(processorPool)
    env.lifecycle().manage(tweetStream)
  }
}
