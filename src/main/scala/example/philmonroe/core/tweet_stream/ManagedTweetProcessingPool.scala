package example.philmonroe.core.tweet_stream

import io.dropwizard.lifecycle.Managed
import java.util.concurrent.{TimeUnit, Executors}
import example.philmonroe.setup.Logging

/**
 * Created by phil on 7/3/14.
 */
class ManagedTweetProcessingPool(processor: TweetProcessor) extends Managed with Logging {
   val pool = Executors.newFixedThreadPool(5)

   def start() = {
     pool.submit(processor)
     pool.submit(processor)
     pool.submit(processor)
     pool.submit(processor)
     pool.submit(processor)
   }

   def stop() = {
     LOG.info("Shutting down TweetProcessor Pool")
     processor.should_process = false
     pool.shutdown()
     pool.awaitTermination(10, TimeUnit.SECONDS)
     LOG.info("TweetProcessor Pool Shut Down")
   }
 }
