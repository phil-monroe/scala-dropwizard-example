package example.philmonroe.core.tweet_stream

import example.philmonroe.setup.Logging
import com.fasterxml.jackson.databind.ObjectMapper
import java.util.concurrent.{TimeUnit, LinkedBlockingQueue}
import example.philmonroe.api.twitter.Tweet

/**
 * Created by phil on 7/3/14.
 */
class TweetProcessor(queue: LinkedBlockingQueue[String], objectMapper: ObjectMapper) extends Runnable with Logging {
   var should_process = true

   override def run(): Unit = {

     while(should_process){
       try{
         Option(queue.poll(1, TimeUnit.SECONDS)).map { msg =>
           val tweet = objectMapper.readValue(msg.trim, classOf[Tweet])
           LOG.info(tweet.toString)
         }
       } catch {
         case e: Throwable => LOG.error("Failed to handle tweet", e)
       }

     }
   }
 }
