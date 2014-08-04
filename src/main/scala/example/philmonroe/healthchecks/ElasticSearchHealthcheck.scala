package example.philmonroe.healthchecks

import com.codahale.metrics.health.HealthCheck
import com.codahale.metrics.health.HealthCheck.Result
import example.philmonroe.core.tweet_stream.ManagedTweetStream

class ElasticSearchHealthcheck(tweetStream: ManagedTweetStream) extends HealthCheck {
  override def check(): Result = {
    if (tweetStream.hosebird.isDone)
      Result.unhealthy("Tweet Stream has finished!")
    else
      Result.healthy()
  }
}
