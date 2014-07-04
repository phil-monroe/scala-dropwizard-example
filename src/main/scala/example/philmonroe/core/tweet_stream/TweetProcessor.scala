package example.philmonroe.core.tweet_stream

import example.philmonroe.setup.Logging
import com.fasterxml.jackson.databind.ObjectMapper
import java.util.concurrent.{TimeUnit, LinkedBlockingQueue}
import example.philmonroe.api.twitter.Tweet
import example.philmonroe.core.elasticsearch.ManagedElasticSearchClient
import java.text.SimpleDateFormat


/**
 * Created by phil on 7/3/14.
 */
class TweetProcessor(queue: LinkedBlockingQueue[String], objectMapper: ObjectMapper, esClient: ManagedElasticSearchClient) extends Runnable with Logging {
  val dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZZZ yyyy")

  var should_process = true

  override def run(): Unit = {

    while (should_process) {
      try {
        Option(queue.poll(1, TimeUnit.SECONDS)).map { msg =>
          var tweet = objectMapper.readValue(msg.trim, classOf[Tweet])
          tweet = tweet.copy(time = dateFormat.parse(tweet.created_at).getTime)

          esClient.index("twitter", "tweets", tweet.id.toString, objectMapper.writeValueAsString(tweet))

          LOG.debug(tweet.toString)
        }
      } catch {
        case e: Throwable => LOG.error("Failed to handle tweet", e)
      }

    }
  }

  def logMap(map: Map[String, Any]) = println(map)
}
