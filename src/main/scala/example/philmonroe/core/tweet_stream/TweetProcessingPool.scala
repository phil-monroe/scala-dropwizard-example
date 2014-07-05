package example.philmonroe.core.tweet_stream

import io.dropwizard.lifecycle.Managed
import java.util.concurrent.{TimeUnit, Executors}
import example.philmonroe.setup.Logging

class TweetProcessingPool(processors: Seq[TweetProcessor]) extends Managed with Logging {
  private val pool = Executors.newFixedThreadPool(processors.size)

  def start() = {
    processors.foreach(pool.submit)
  }

  def stop() = {
    LOG.info("Shutting down TweetProcessor Pool")
    pool.shutdown()
    pool.awaitTermination(10, TimeUnit.SECONDS)
    LOG.info("TweetProcessor Pool Shut Down")
  }
}
