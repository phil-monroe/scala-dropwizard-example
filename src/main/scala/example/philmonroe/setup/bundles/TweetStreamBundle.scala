package example.philmonroe.setup.bundles

import io.dropwizard.ConfiguredBundle
import io.dropwizard.setup.{Environment, Bootstrap}
import example.philmonroe.GitHubStatusConfig
import example.philmonroe.core.tweet_stream.{TweetProcessor, ManagedTweetProcessingPool, ManagedTweetStream}

/**
 * Created by phil on 6/4/14.
 */
class TweetStreamBundle extends ConfiguredBundle[GitHubStatusConfig] {


  override def initialize(bootstrap: Bootstrap[_]): Unit = {

  }

  override def run(config: GitHubStatusConfig, env: Environment): Unit = {
    var tweetStream = new ManagedTweetStream(config.twitter)
    val processor = new TweetProcessor(tweetStream.queue, env.getObjectMapper)
    val processorPool = new ManagedTweetProcessingPool(processor)

    env.lifecycle().manage(processorPool)
    env.lifecycle().manage(tweetStream)
  }
}