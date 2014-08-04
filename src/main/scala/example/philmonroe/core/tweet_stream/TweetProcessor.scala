package example.philmonroe.core.tweet_stream

import example.philmonroe.setup.Logging
import com.fasterxml.jackson.databind.ObjectMapper
import java.util.concurrent.{TimeUnit, LinkedBlockingQueue}
import example.philmonroe.api.twitter.Tweet
import example.philmonroe.core.elasticsearch.ManagedElasticSearchClient
import java.text.SimpleDateFormat
import io.dropwizard.lifecycle.Managed

class TweetProcessor(identifier: Int, queue: LinkedBlockingQueue[String], objectMapper: ObjectMapper, elasticSearch: ManagedElasticSearchClient) extends Managed with Runnable with Logging {
  private val dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZZZ yyyy")
  private var should_process = true

  override def run(): Unit = {

    while (should_process) {
      try {
        Option(queue.poll(1, TimeUnit.SECONDS)).map { msg =>
          var tweet = objectMapper.readValue(msg.trim, classOf[Tweet])
          tweet = tweet.copy(time = dateFormat.parse(tweet.created_at).getTime)

          elasticSearch.index("twitter", "tweets", tweet.id, tweet)

        // LOG.info(s"[$identifier] $tweet")
        }
      } catch {
        case e: Throwable => LOG.error("Failed to handle tweet", e)
      }
    }
  }

  def start(): Unit = {}

  def stop(): Unit = {
    should_process = false
  }
}
