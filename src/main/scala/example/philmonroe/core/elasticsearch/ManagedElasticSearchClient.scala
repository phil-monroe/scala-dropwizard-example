package example.philmonroe.core.elasticsearch

import io.dropwizard.lifecycle.Managed
import example.philmonroe.setup.Logging
import io.searchbox.client.config.HttpClientConfig
import io.searchbox.client.JestClientFactory
import io.searchbox.core.{Search, Index}
import io.searchbox.core.search.sort.Sort


class ManagedElasticSearchClient(url: String) extends Managed with Logging {
  val clientConfig = new HttpClientConfig.Builder(url).multiThreaded(true).build()

  val factory = new JestClientFactory()
  factory.setHttpClientConfig(clientConfig)
  val client = factory.getObject


  override def start(): Unit = {
  }

  override def stop(): Unit = {
    client.shutdownClient()
  }

  def index(index: String, typ: String, id: Any, doc: Any) = {
    val idx = new Index.Builder(doc)
      .`type`(typ)
      .id(id.toString)
      .index(index)

    client.execute(idx.build())
  }

  def search(index: String, typ: String, query: String, sort: Option[Sort] = None) = {
    val search = new Search.Builder(query)
      .addIndex(index)
      .addType(typ)
      .addSort(new Sort("time", Sort.Sorting.DESC))

    sort.map(search.addSort)

    client.execute(search.build())
  }
}
