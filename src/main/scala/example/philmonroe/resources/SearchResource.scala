package example.philmonroe.resources

import javax.ws.rs.{QueryParam, GET, Produces, Path}
import javax.ws.rs.core.MediaType
import com.wordnik.swagger.annotations.{ApiOperation, Api}
import example.philmonroe.core.elasticsearch.ManagedElasticSearchClient
import example.philmonroe.api.twitter.Tweet
import io.searchbox.core.search.sort.Sort
import org.elasticsearch.index.query.QueryBuilders
import org.elasticsearch.search.builder.SearchSourceBuilder
import scala.collection.JavaConversions._

@Path("/search")
@Api(value = "/search", description = "Searches through tweets.")
@Produces(Array(MediaType.APPLICATION_JSON))
class SearchResource(elasticsearch: ManagedElasticSearchClient) {

  @GET
  @ApiOperation(value = "Searches throgh tweets", notes = "", produces = MediaType.APPLICATION_JSON)
  def search(@QueryParam("q") queryStr: String) = {


    val bool = QueryBuilders.boolQuery()
    Option(queryStr).getOrElse("scala").split(",").foreach(q => bool.should(QueryBuilders.matchPhraseQuery("text", q)))

    val queryBuilder = new SearchSourceBuilder
    queryBuilder.query(bool)

    val res = elasticsearch.search("twitter", "tweets", queryBuilder.toString, Some(new Sort("time", Sort.Sorting.DESC)))

    res.getHits(classOf[Tweet]).map(_.source)
  }
}
