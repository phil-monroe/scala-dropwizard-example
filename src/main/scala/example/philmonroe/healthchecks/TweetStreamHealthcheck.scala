package example.philmonroe.healthchecks

import com.codahale.metrics.health.HealthCheck
import com.codahale.metrics.health.HealthCheck.Result
import example.philmonroe.core.tweet_stream.TweetStream

class TweetStreamHealthcheck(tweetStream: TweetStream) extends HealthCheck {
  override def check(): Result = {
    if (tweetStream.hosebird.isDone)
      Result.unhealthy("Tweet Stream has finished!")
    else
      Result.healthy()
  }
}
