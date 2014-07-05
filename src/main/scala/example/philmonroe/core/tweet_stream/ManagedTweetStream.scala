package example.philmonroe.core.tweet_stream

import io.dropwizard.lifecycle.Managed
import java.util.concurrent.LinkedBlockingQueue
import com.twitter.hbc.core.{Constants, HttpHosts}
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint
import com.twitter.hbc.httpclient.auth.OAuth1
import scala.collection.JavaConversions._
import com.twitter.hbc.ClientBuilder
import com.twitter.hbc.core.processor.StringDelimitedProcessor
import example.philmonroe.setup.config.TwitterConfig

class ManagedTweetStream(config: TwitterConfig) extends Managed {
  val queue = new LinkedBlockingQueue[String](100000);

  val hosebirdEndpoint = new StatusesFilterEndpoint()
  hosebirdEndpoint.trackTerms(config.topics)

  val hosebird = new ClientBuilder()
    .name("hosebird-01")
    .hosts(new HttpHosts(Constants.STREAM_HOST))
    .authentication(new OAuth1(config.apiKey, config.apiSecret, config.oauthToken, config.oauthSecret))
    .endpoint(hosebirdEndpoint)
    .processor(new StringDelimitedProcessor(queue))
    .build()


  def start(): Unit = {
    hosebird.connect()
  }

  def stop(): Unit = {
    hosebird.stop()
  }
}








