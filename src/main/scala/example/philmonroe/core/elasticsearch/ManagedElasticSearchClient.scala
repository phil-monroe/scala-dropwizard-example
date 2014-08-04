package example.philmonroe.core.elasticsearch

import io.dropwizard.lifecycle.Managed
import example.philmonroe.setup.Logging
import org.elasticsearch.client.transport.TransportClient
import org.elasticsearch.common.transport.InetSocketTransportAddress
import org.elasticsearch.action.search.{SearchRequestBuilder, SearchType}
import org.elasticsearch.index.query.QueryBuilder
import com.fasterxml.jackson.databind.ObjectMapper


class ManagedElasticSearchClient(mapper: ObjectMapper) extends Managed with Logging {
  val client = new TransportClient()
  client.addTransportAddress(new InetSocketTransportAddress("localhost", 9300))


  override def start(): Unit = {
  }

  override def stop(): Unit = {
    client.close()
  }

  def index(index: String, typ: String, id: Any, doc: Any) = {
    client.prepareIndex(index, typ)
      .setId(id.toString)
      .setSource(mapper.writeValueAsString(doc))
      .execute()
      .actionGet()
  }

  def search(index: String, typ: String, query: QueryBuilder, options: (SearchRequestBuilder) => Unit = identity) = {
    val search = client.prepareSearch(index)
      .setTypes(typ)
      .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
      .setQuery(query)
      .setFrom(0)
      .setSize(60)

    options(search)

    search
      .execute()
      .actionGet()
  }
}
