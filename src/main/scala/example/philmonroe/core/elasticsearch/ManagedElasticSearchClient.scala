package example.philmonroe.core.elasticsearch

import io.dropwizard.lifecycle.Managed
import example.philmonroe.setup.Logging
import org.elasticsearch.client.transport.TransportClient
import org.elasticsearch.common.transport.InetSocketTransportAddress
import org.elasticsearch.action.search.SearchType
import org.elasticsearch.index.query.{QueryBuilder, QueryBuilders}
import org.elasticsearch.search.sort.SortOrder


/**
 * Created by phil on 7/3/14.
 */
class ManagedElasticSearchClient extends Managed with Logging {
  val client = new TransportClient()
  client.addTransportAddress(new InetSocketTransportAddress("localhost", 9300))


  override def start(): Unit = {
  }

  override def stop(): Unit = {
    client.close()
  }

  def index(index: String, typ: String, id: String, doc: String) = {
    val resp = client.prepareIndex(index, typ)
      .setId(id)
      .setSource(doc)
      .execute()
      .actionGet()
  }

  def search(index: String, typ: String, query: QueryBuilder) = {
    client.prepareSearch(index)
      .setTypes(typ)
      .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
      .setQuery(query)
      .addSort("time", SortOrder.DESC)
      .setFrom(0)
      .setSize(60)
      .setExplain(true)
      .execute()
      .actionGet()
  }
}
