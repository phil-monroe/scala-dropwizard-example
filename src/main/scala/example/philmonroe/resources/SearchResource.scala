package example.philmonroe.resources

import javax.ws.rs.{GET, Produces, Path}
import javax.ws.rs.core.MediaType
import com.wordnik.swagger.annotations.{ApiOperation, Api}
import example.philmonroe.core.elasticsearch.ManagedElasticSearchClient
import scala.collection.JavaConversions._
import example.philmonroe.api.twitter.Tweet
import com.fasterxml.jackson.databind.ObjectMapper
import org.elasticsearch.index.query.QueryBuilders
import org.elasticsearch.search.sort.SortOrder

case class SearchResponse(tweet: Tweet, score: Double)

@Path("/search")
@Api(value = "/search", description = "Searches through tweets.")
@Produces(Array(MediaType.APPLICATION_JSON))
class SearchResource(elasticsearch: ManagedElasticSearchClient, mapper: ObjectMapper) {

  @GET
  @ApiOperation(value = "Searches throgh tweets", notes = "", produces = MediaType.APPLICATION_JSON)
  def search = {
    val query = QueryBuilders.termQuery("text", "salsa")

    val res = elasticsearch.search("twitter", "tweets", query, _.addSort("time", SortOrder.DESC))

    res.getHits.map { hit =>
      SearchResponse(hit.getSourceAsString, hit.score)
    }
  }

  implicit def json2Tweet(json: String): Tweet = mapper.readValue(json, classOf[Tweet])
}
