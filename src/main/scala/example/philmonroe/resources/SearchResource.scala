package example.philmonroe.resources

import javax.ws.rs.{GET, Produces, Path}
import javax.ws.rs.core.MediaType
import com.wordnik.swagger.annotations.{ApiOperation, Api}
import example.philmonroe.core.elasticsearch.ManagedElasticSearchClient
import scala.collection.JavaConversions._
import example.philmonroe.api.twitter.Tweet
import com.fasterxml.jackson.databind.ObjectMapper
import org.elasticsearch.index.query.QueryBuilders

case class SearchResponse(tweet: Tweet)

@Path("/search")
@Api(value = "/search", description = "Searches through tweets.")
@Produces(Array(MediaType.APPLICATION_JSON))
class SearchResource(elasticsearch: ManagedElasticSearchClient, mapper: ObjectMapper) {

  @GET
  @ApiOperation(value = "Searches throgh tweets", notes = "", produces = MediaType.APPLICATION_JSON)
  def search = {
    val res = elasticsearch.search("twitter", "tweets", QueryBuilders.termQuery("text", "salsa"))
    res.getHits.map{ hit =>
      val tweet = mapper.readValue(hit.getSourceAsString, classOf[Tweet])
      SearchResponse(tweet)
    }
  }
}
